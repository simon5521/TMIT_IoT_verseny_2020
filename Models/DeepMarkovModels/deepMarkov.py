
import argparse
import time
from os.path import exists

import numpy as np
import torch
import torch.nn as nn

import pyro
import pyro.distributions as dist
import pyro.poutine as poutine
from pyro.distributions.transforms import affine_autoregressive
from pyro.infer import SVI, JitTrace_ELBO, Trace_ELBO, TraceEnum_ELBO, TraceTMC_ELBO, config_enumerate
from pyro.optim import ClippedAdam

data = [ 0.385598354
,
0.433279884
,
0.37271063
,
0.375004129
,
0.374148976
,
0.414905539
,
0.449342833
,
0.417252785
,
0.375906291
,
0.376523907
,
0.384315855
,
0.422228566
,
0.393512498
,
0.487940845
,
0.43313064
,
0.455842658
,
0.435982453
,
0.38017915
,
0.370259989
,
0.375193262
,
0.38290653
,
0.37860512
,
0.371140274
,
0.374240253
,
0.378837725
,
0.363760596
,
0.37481114
,
0.36158728
,
0.430623713
,
0.497260872
,
0.365633055
,
0.380153094
,
0.360322586
,
0.350919709
,
0.347418106
,
0.356888565
,
0.371029653
,
0.420338379
,
0.426273484
,
0.398732321
,
0.482129626
,
0.468244635
,
0.364010845
,
0.375366183
,
0.382710137
,
0.383458758
,
0.376729561
,
0.358895385
,
0.371226728
,
0.371478026
,
0.411612734
,
0.400365241
,
0.439301671
,
0.391265562
,
0.375832821
,
0.401609055
,
0.384666349
,
0.389605398
,
0.375913848
,
0.376081044
,
0.375819936
,
0.385576585
,
0.377482597
,
0.451733649
,
0.451782042
,
0.46253263
,
0.39920852
,
0.394922806
,
0.398238955
,
0.399691089
,
0.397536095
,
0.392962602
,
0.403694205
,
0.416412967
,
0.40052556
,
0.405804048
,
0.470180261
,
0.473970118
,
0.624919671
,
0.454910676
,
0.391113556
,
0.441224508
,
0.427863176
,
0.403000396
,
0.401705085
,
0.405285496
,
0.408991468
,
0.393828758
,
0.418005334
,
0.45609891
,
0.41933651
,
0.403618483
,
0.405554735
,
0.391253077
,
0.384529525
,
0.415441636
,
0.454038651
,
0.392876632
,
0.365837239
,
0.36510953
,
0.368331376
,
0.373943911
,
0.380320387
,
0.362739191
,
0.382770722
,
0.37736349
,
0.370303811
,
0.37134394
,
0.369484911
,
0.384034484
,
0.3798936
,
0.43435589
,
0.369887759
,
0.36807706
,
0.382272614
,
0.381230766
,
0.382437164
,
0.380777291
,
0.376921343
,
0.380188731
,
0.410277585
,
0.426626843
,
0.411238167,

0.395350477,

0.374494207,

0.374091565,

0.381272638,

0.381225226,

0.381530078,

0.38170088,

0.379063134,

0.377809605,

0.405414362,

0.380107313,

0.427483978,

0.447298629,

0.397276134,

0.456332106,

0.419099722,

0.427935351,

0.418212689,

0.452266353,

0.385394187,

0.394680986,

0.407141895,

0.417351714,

0.410763469,

0.427124092,

0.377698404,

0.42723714,

0.394072201,

0.356867691,

0.350943927,

0.352670597,

0.364506774,

0.36891671,

0.365970239,

0.35392319,

0.362367163,

0.364973492,

0.395918951,

0.391875714,

0.386056215,

0.405662795,

0.393483885,

0.379904729,

0.375926034,

0.38029669,

0.39835831,

0.383498747,

0.388075451,

0.385921934,

0.453421934,

0.450431002,

0.413600008,

0.425869592,

0.433775391,

0.37856269,

0.376492896,

0.394494326,

0.393016178,

0.388074266,

0.382953963,

0.401378292,

0.401188441,

0.401308875,

0.403656543,

0.397672116,

0.397175015,

0.383891743,

0.374149103,

0.373358406,

0.374285036,

0.373967481,

0.373875972,

0.375169522,

0.373787445,

0.374896553,

0.384731383,

0.403840459
]
data=torch.tensor(data)
z_0=nn.Parameter(torch.zeros(1,1))
T_max=len(data)

class GatedTransition(nn.Module):
    """
    Parameterizes the gaussian latent transition probability `p(z_t | z_{t-1})`
    See section 5 in the reference for comparison.
    """

    def __init__(self, z_dim=1, transition_dim=1):
        super().__init__()
        # initialize the six linear transformations used in the neural network
        self.lin_gate_z_to_hidden = nn.Linear(z_dim, transition_dim)
        self.lin_gate_hidden_to_z = nn.Linear(transition_dim, z_dim)
        self.lin_proposed_mean_z_to_hidden = nn.Linear(z_dim, transition_dim)
        self.lin_proposed_mean_hidden_to_z = nn.Linear(transition_dim, z_dim)
        self.lin_sig = nn.Linear(z_dim, z_dim)
        self.lin_z_to_loc = nn.Linear(z_dim, z_dim)
        # modify the default initialization of lin_z_to_loc
        # so that it's starts out as the identity function
        self.lin_z_to_loc.weight.data = torch.eye(z_dim)
        self.lin_z_to_loc.bias.data = torch.zeros(z_dim)
        # initialize the three non-linearities used in the neural network
        self.relu = nn.ReLU()
        self.softplus = nn.Softplus()

    def forward(self, z_t_1):
        """
        Given the latent `z_{t-1}` corresponding to the time step t-1
        we return the mean and scale vectors that parameterize the
        (diagonal) gaussian distribution `p(z_t | z_{t-1})`
        """
        # compute the gating function
        _gate = self.relu(self.lin_gate_z_to_hidden(z_t_1))
        gate = torch.sigmoid(self.lin_gate_hidden_to_z(_gate))
        # compute the 'proposed mean'
        _proposed_mean = self.relu(self.lin_proposed_mean_z_to_hidden(z_t_1))
        proposed_mean = self.lin_proposed_mean_hidden_to_z(_proposed_mean)
        # assemble the actual mean used to sample z_t, which mixes a linear transformation
        # of z_{t-1} with the proposed mean modulated by the gating function
        loc = (1 - gate) * self.lin_z_to_loc(z_t_1) + gate * proposed_mean
        # compute the scale used to sample z_t, using the proposed mean from
        # above as input the softplus ensures that scale is positive
        scale = self.softplus(self.lin_sig(self.relu(proposed_mean)))
        # return loc, scale which can be fed into Normal
        return loc, scale




class DMM(nn.Module):
    """
    This PyTorch Module encapsulates the model as well as the
    variational distribution (the guide) for the Deep Markov Model
    """

    def __init__(self, ):
        super().__init__()
        self.trans = GatedTransition(z_dim=1, transition_dim=1)

    def model(self):

        pyro.module("dmm", self)
        z_prev = z_0

        # sample the latents z and observed x's one time step at a time
        for t in pyro.markov(range(1, T_max + 1)):
            print(t)
            # the next two lines of code sample z_t ~ p(z_t | z_{t-1}).
            # first compute the parameters of the diagonal gaussian
            # distribution p(z_t | z_{t-1})
            z_loc, z_scale = self.trans(z_prev)
            # then sample z_t according to dist.Normal(z_loc, z_scale)
            with poutine.scale(scale=1.0):
                z_t = pyro.sample("z_%d" % t, dist.Normal(torch.tensor(z_loc), torch.tensor(z_scale)),
                        obs=data[t - 1])
            z_prev = z_t

    def guide(self):

        pyro.module("dmm", self)
        return True

print("setup optimizer")
# setup optimizer
adam_params = {"lr": 0.0003, "betas": (0.96, 0.999),
               "clip_norm": 10.0, "lrd": 0.99996,
               "weight_decay": 2.0}
dmm=DMM()
adam = ClippedAdam(adam_params)
# setup inference algorithm
svi = SVI(dmm.model, dmm.guide, adam, Trace_ELBO())

def do_evaluation():
    # put the RNN into evaluation mode (i.e. turn off drop-out if applicable)
    #dmm.rnn.eval()

    # compute the validation and test loss
    val_nll = svi.step()

    # put the RNN back into training mode (i.e. turn on drop-out if applicable)
    #dmm.rnn.train()
    return val_nll



print("do evaluation")
for step in range(1000):
    val_nll = do_evaluation()
    if (step % 20) == 0 :
        print("Step: ",step," | ",val_nll)