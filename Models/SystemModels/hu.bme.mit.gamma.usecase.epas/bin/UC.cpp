/** Generated by YAKINDU Statechart Tools code generator. */

#include "UC.h"

/*! \file Implementation of the state machine 'UC'
*/




UC::UC()  :
stateConfVectorPosition(0),
ifaceHWFault(),
ifaceFault()
{
}

UC::~UC()
{
}


void UC::init()
{
	for (sc_ushort i = 0; i < maxOrthogonalStates; ++i)
		stateConfVector[i] = UC_last_state;
	
	stateConfVectorPosition = 0;

	clearInEvents();
	clearOutEvents();
	
}

void UC::enter()
{
	/* Default enter sequence for statechart UC */
	enseq_UC_default();
}

void UC::exit()
{
	/* Default exit sequence for statechart UC */
	exseq_UC();
}

sc_boolean UC::isActive() const
{
	return stateConfVector[0] != UC_last_state;
}

/* 
 * Always returns 'false' since this state machine can never become final.
 */
sc_boolean UC::isFinal() const
{
   return false;}

void UC::runCycle()
{
	
	clearOutEvents();
	for (stateConfVectorPosition = 0;
		stateConfVectorPosition < maxOrthogonalStates;
		stateConfVectorPosition++)
		{
			
		switch (stateConfVector[stateConfVectorPosition])
		{
		case UC_On :
		{
			UC_On_react(true);
			break;
		}
		case UC_Off :
		{
			UC_Off_react(true);
			break;
		}
		default:
			break;
		}
	}
	clearInEvents();
}

void UC::clearInEvents()
{
	ifaceHWFault.shutdown_raised = false;
}

void UC::clearOutEvents()
{
	ifaceFault.shutdown_raised = false;
}


sc_boolean UC::isStateActive(UCStates state) const
{
	switch (state)
	{
		case UC_On : 
			return (sc_boolean) (stateConfVector[SCVI_UC_ON] == UC_On
			);
		case UC_Off : 
			return (sc_boolean) (stateConfVector[SCVI_UC_OFF] == UC_Off
			);
		default: return false;
	}
}

UC::SCI_HWFault* UC::getSCI_HWFault()
{
	return &ifaceHWFault;
}
/* Functions for event shutdown in interface SCI_HWFault */
void UC::SCI_HWFault::raise_shutdown()
{
	shutdown_raised = true;
}
UC::SCI_Fault* UC::getSCI_Fault()
{
	return &ifaceFault;
}
/* Functions for event shutdown in interface SCI_Fault */
sc_boolean UC::SCI_Fault::isRaised_shutdown() const
{
	return shutdown_raised;
}

// implementations of all internal functions

/* Entry action for state 'Off'. */
void UC::enact_UC_Off()
{
	/* Entry action for state 'Off'. */
	ifaceFault.shutdown_raised = true;
}

/* 'default' enter sequence for state On */
void UC::enseq_UC_On_default()
{
	/* 'default' enter sequence for state On */
	stateConfVector[0] = UC_On;
	stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state Off */
void UC::enseq_UC_Off_default()
{
	/* 'default' enter sequence for state Off */
	enact_UC_Off();
	stateConfVector[0] = UC_Off;
	stateConfVectorPosition = 0;
}

/* 'default' enter sequence for region UC */
void UC::enseq_UC_default()
{
	/* 'default' enter sequence for region UC */
	react_UC__entry_Default();
}

/* Default exit sequence for state On */
void UC::exseq_UC_On()
{
	/* Default exit sequence for state On */
	stateConfVector[0] = UC_last_state;
	stateConfVectorPosition = 0;
}

/* Default exit sequence for state Off */
void UC::exseq_UC_Off()
{
	/* Default exit sequence for state Off */
	stateConfVector[0] = UC_last_state;
	stateConfVectorPosition = 0;
}

/* Default exit sequence for region UC */
void UC::exseq_UC()
{
	/* Default exit sequence for region UC */
	/* Handle exit of all possible states (of UC.UC) at position 0... */
	switch(stateConfVector[ 0 ])
	{
		case UC_On :
		{
			exseq_UC_On();
			break;
		}
		case UC_Off :
		{
			exseq_UC_Off();
			break;
		}
		default: break;
	}
}

/* Default react sequence for initial entry  */
void UC::react_UC__entry_Default()
{
	/* Default react sequence for initial entry  */
	enseq_UC_On_default();
}

sc_boolean UC::react() {
	/* State machine reactions. */
	return false;
}

sc_boolean UC::UC_On_react(const sc_boolean try_transition) {
	/* The reactions of state On. */
	sc_boolean did_transition = try_transition;
	if (try_transition)
	{ 
		if ((react()) == (false))
		{ 
			if (ifaceHWFault.shutdown_raised)
			{ 
				exseq_UC_On();
				enseq_UC_Off_default();
			}  else
			{
				did_transition = false;
			}
		} 
	} 
	return did_transition;
}

sc_boolean UC::UC_Off_react(const sc_boolean try_transition) {
	/* The reactions of state Off. */
	sc_boolean did_transition = try_transition;
	if (try_transition)
	{ 
		if ((react()) == (false))
		{ 
			did_transition = false;
		} 
	} 
	return did_transition;
}


