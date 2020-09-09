/** Generated by YAKINDU Statechart Tools code generator. */
package hu.bme.mit.gamma.usecase.epas.mainctrl;

import java.util.LinkedList;
import java.util.List;

public class MainctrlStatemachine implements IMainctrlStatemachine {
	protected class SCIMonitorImpl implements SCIMonitor {
	
		private List<SCIMonitorListener> listeners = new LinkedList<SCIMonitorListener>();
		
		public List<SCIMonitorListener> getListeners() {
			return listeners;
		}
		private boolean warning;
		
		
		public boolean isRaisedWarning() {
			return warning;
		}
		
		protected void raiseWarning() {
			warning = true;
			for (SCIMonitorListener listener : listeners) {
				listener.onWarningRaised();
			}
		}
		
		private boolean selfsteering;
		
		
		public boolean isRaisedSelfsteering() {
			return selfsteering;
		}
		
		protected void raiseSelfsteering() {
			selfsteering = true;
			for (SCIMonitorListener listener : listeners) {
				listener.onSelfsteeringRaised();
			}
		}
		
		private boolean loa;
		
		
		public boolean isRaisedLoa() {
			return loa;
		}
		
		protected void raiseLoa() {
			loa = true;
			for (SCIMonitorListener listener : listeners) {
				listener.onLoaRaised();
			}
		}
		
		protected void clearEvents() {
		}
		protected void clearOutEvents() {
		
		warning = false;
		selfsteering = false;
		loa = false;
		}
		
	}
	
	
	protected class SCIS1HWImpl implements SCIS1HW {
	
		private boolean det;
		
		
		public void raiseDet() {
			det = true;
		}
		
		private boolean latent;
		
		
		public void raiseLatent() {
			latent = true;
		}
		
		protected void clearEvents() {
			det = false;
			latent = false;
		}
	}
	
	
	protected class SCIS2HWImpl implements SCIS2HW {
	
		private boolean det;
		
		
		public void raiseDet() {
			det = true;
		}
		
		private boolean latent;
		
		
		public void raiseLatent() {
			latent = true;
		}
		
		protected void clearEvents() {
			det = false;
			latent = false;
		}
	}
	
	
	protected class SCIS3HWImpl implements SCIS3HW {
	
		private boolean det;
		
		
		public void raiseDet() {
			det = true;
		}
		
		private boolean latent;
		
		
		public void raiseLatent() {
			latent = true;
		}
		
		protected void clearEvents() {
			det = false;
			latent = false;
		}
	}
	
	
	protected class SCIUCHWImpl implements SCIUCHW {
	
		private boolean shutdown;
		
		
		public void raiseShutdown() {
			shutdown = true;
		}
		
		protected void clearEvents() {
			shutdown = false;
		}
	}
	
	
	protected SCIMonitorImpl sCIMonitor;
	
	protected SCIS1HWImpl sCIS1HW;
	
	protected SCIS2HWImpl sCIS2HW;
	
	protected SCIS3HWImpl sCIS3HW;
	
	protected SCIUCHWImpl sCIUCHW;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_main,
		main_region_main_operation_Off,
		main_region_main_operation_On,
		main_region_main_operation_On_r1_NormalOperation,
		main_region_main_operation_On_r1_Warning,
		main_region_main_evaluation_working,
		main_region_main_evaluation_ShutdownState,
		main_region_main_evaluation_SelfSteeringState,
		$NullState$
	};
	
	private final State[] stateVector = new State[2];
	
	private int nextStateIndex;
	
	private long onsensors;
	
	protected long getOnsensors() {
		return onsensors;
	}
	
	protected void setOnsensors(long value) {
		this.onsensors = value;
	}
	
	
	private long oksensors;
	
	protected long getOksensors() {
		return oksensors;
	}
	
	protected void setOksensors(long value) {
		this.oksensors = value;
	}
	
	
	private long offsensors;
	
	protected long getOffsensors() {
		return offsensors;
	}
	
	protected void setOffsensors(long value) {
		this.offsensors = value;
	}
	
	
	private long latentsensors;
	
	protected long getLatentsensors() {
		return latentsensors;
	}
	
	protected void setLatentsensors(long value) {
		this.latentsensors = value;
	}
	
	
	public MainctrlStatemachine() {
		sCIMonitor = new SCIMonitorImpl();
		sCIS1HW = new SCIS1HWImpl();
		sCIS2HW = new SCIS2HWImpl();
		sCIS3HW = new SCIS3HWImpl();
		sCIUCHW = new SCIUCHWImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 2; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		setOnsensors(0);
		
		setOksensors(0);
		
		setOffsensors(0);
		
		setLatentsensors(0);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
				"The state machine needs to be initialized first by calling the init() function."
			);
		}
		enterSequence_main_region_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_main_operation_Off:
				main_region_main_operation_Off_react(true);
				break;
			case main_region_main_operation_On_r1_NormalOperation:
				main_region_main_operation_On_r1_NormalOperation_react(true);
				break;
			case main_region_main_operation_On_r1_Warning:
				main_region_main_operation_On_r1_Warning_react(true);
				break;
			case main_region_main_evaluation_working:
				main_region_main_evaluation_working_react(true);
				break;
			case main_region_main_evaluation_ShutdownState:
				main_region_main_evaluation_ShutdownState_react(true);
				break;
			case main_region_main_evaluation_SelfSteeringState:
				main_region_main_evaluation_SelfSteeringState_react(true);
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
	public void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCIMonitor.clearEvents();
		sCIS1HW.clearEvents();
		sCIS2HW.clearEvents();
		sCIS3HW.clearEvents();
		sCIUCHW.clearEvents();
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCIMonitor.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_main:
			return stateVector[0].ordinal() >= State.
					main_region_main.ordinal()&& stateVector[0].ordinal() <= State.main_region_main_evaluation_SelfSteeringState.ordinal();
		case main_region_main_operation_Off:
			return stateVector[0] == State.main_region_main_operation_Off;
		case main_region_main_operation_On:
			return stateVector[0].ordinal() >= State.
					main_region_main_operation_On.ordinal()&& stateVector[0].ordinal() <= State.main_region_main_operation_On_r1_Warning.ordinal();
		case main_region_main_operation_On_r1_NormalOperation:
			return stateVector[0] == State.main_region_main_operation_On_r1_NormalOperation;
		case main_region_main_operation_On_r1_Warning:
			return stateVector[0] == State.main_region_main_operation_On_r1_Warning;
		case main_region_main_evaluation_working:
			return stateVector[1] == State.main_region_main_evaluation_working;
		case main_region_main_evaluation_ShutdownState:
			return stateVector[1] == State.main_region_main_evaluation_ShutdownState;
		case main_region_main_evaluation_SelfSteeringState:
			return stateVector[1] == State.main_region_main_evaluation_SelfSteeringState;
		default:
			return false;
		}
	}
	
	public SCIMonitor getSCIMonitor() {
		return sCIMonitor;
	}
	
	public SCIS1HW getSCIS1HW() {
		return sCIS1HW;
	}
	
	public SCIS2HW getSCIS2HW() {
		return sCIS2HW;
	}
	
	public SCIS3HW getSCIS3HW() {
		return sCIS3HW;
	}
	
	public SCIUCHW getSCIUCHW() {
		return sCIUCHW;
	}
	
	private boolean check_main_region_main_operation__choice_0_tr0_tr0() {
		return 0==getOnsensors();
	}
	
	private boolean check_main_region_main_evaluation__choice_0_tr0_tr0() {
		return getLatentsensors()>=getOksensors();
	}
	
	private void effect_main_region_main_operation__choice_0_tr0() {
		enterSequence_main_region_main_operation_Off_default();
	}
	
	private void effect_main_region_main_operation__choice_0_tr1() {
		enterSequence_main_region_main_operation_On_r1_Warning_default();
	}
	
	private void effect_main_region_main_evaluation__choice_0_tr0() {
		sCIMonitor.raiseSelfsteering();
		
		enterSequence_main_region_main_evaluation_SelfSteeringState_default();
	}
	
	private void effect_main_region_main_evaluation__choice_0_tr1() {
		enterSequence_main_region_main_evaluation_working_default();
	}
	
	/* Entry action for state 'Off'. */
	private void entryAction_main_region_main_operation_Off() {
		sCIMonitor.raiseLoa();
	}
	
	/* Entry action for state 'Warning'. */
	private void entryAction_main_region_main_operation_On_r1_Warning() {
		sCIMonitor.raiseWarning();
	}
	
	/* Entry action for state 'ShutdownState'. */
	private void entryAction_main_region_main_evaluation_ShutdownState() {
		sCIMonitor.raiseLoa();
	}
	
	/* Entry action for state 'SelfSteeringState'. */
	private void entryAction_main_region_main_evaluation_SelfSteeringState() {
		sCIMonitor.raiseSelfsteering();
	}
	
	/* 'default' enter sequence for state main */
	private void enterSequence_main_region_main_default() {
		enterSequence_main_region_main_operation_default();
		enterSequence_main_region_main_evaluation_default();
	}
	
	/* 'default' enter sequence for state Off */
	private void enterSequence_main_region_main_operation_Off_default() {
		entryAction_main_region_main_operation_Off();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_main_operation_Off;
	}
	
	/* 'default' enter sequence for state On */
	private void enterSequence_main_region_main_operation_On_default() {
		enterSequence_main_region_main_operation_On_r1_default();
	}
	
	/* 'default' enter sequence for state NormalOperation */
	private void enterSequence_main_region_main_operation_On_r1_NormalOperation_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_main_operation_On_r1_NormalOperation;
	}
	
	/* 'default' enter sequence for state Warning */
	private void enterSequence_main_region_main_operation_On_r1_Warning_default() {
		entryAction_main_region_main_operation_On_r1_Warning();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_main_operation_On_r1_Warning;
	}
	
	/* 'default' enter sequence for state working */
	private void enterSequence_main_region_main_evaluation_working_default() {
		nextStateIndex = 1;
		stateVector[1] = State.main_region_main_evaluation_working;
	}
	
	/* 'default' enter sequence for state ShutdownState */
	private void enterSequence_main_region_main_evaluation_ShutdownState_default() {
		entryAction_main_region_main_evaluation_ShutdownState();
		nextStateIndex = 1;
		stateVector[1] = State.main_region_main_evaluation_ShutdownState;
	}
	
	/* 'default' enter sequence for state SelfSteeringState */
	private void enterSequence_main_region_main_evaluation_SelfSteeringState_default() {
		entryAction_main_region_main_evaluation_SelfSteeringState();
		nextStateIndex = 1;
		stateVector[1] = State.main_region_main_evaluation_SelfSteeringState;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region operation */
	private void enterSequence_main_region_main_operation_default() {
		react_main_region_main_operation__entry_Default();
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_main_region_main_operation_On_r1_default() {
		react_main_region_main_operation_On_r1__entry_Default();
	}
	
	/* 'default' enter sequence for region evaluation */
	private void enterSequence_main_region_main_evaluation_default() {
		react_main_region_main_evaluation__entry_Default();
	}
	
	/* Default exit sequence for state Off */
	private void exitSequence_main_region_main_operation_Off() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state On */
	private void exitSequence_main_region_main_operation_On() {
		exitSequence_main_region_main_operation_On_r1();
	}
	
	/* Default exit sequence for state NormalOperation */
	private void exitSequence_main_region_main_operation_On_r1_NormalOperation() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Warning */
	private void exitSequence_main_region_main_operation_On_r1_Warning() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state working */
	private void exitSequence_main_region_main_evaluation_working() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state ShutdownState */
	private void exitSequence_main_region_main_evaluation_ShutdownState() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state SelfSteeringState */
	private void exitSequence_main_region_main_evaluation_SelfSteeringState() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_main_operation_Off:
			exitSequence_main_region_main_operation_Off();
			break;
		case main_region_main_operation_On_r1_NormalOperation:
			exitSequence_main_region_main_operation_On_r1_NormalOperation();
			break;
		case main_region_main_operation_On_r1_Warning:
			exitSequence_main_region_main_operation_On_r1_Warning();
			break;
		default:
			break;
		}
		
		switch (stateVector[1]) {
		case main_region_main_evaluation_working:
			exitSequence_main_region_main_evaluation_working();
			break;
		case main_region_main_evaluation_ShutdownState:
			exitSequence_main_region_main_evaluation_ShutdownState();
			break;
		case main_region_main_evaluation_SelfSteeringState:
			exitSequence_main_region_main_evaluation_SelfSteeringState();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_main_region_main_operation_On_r1() {
		switch (stateVector[0]) {
		case main_region_main_operation_On_r1_NormalOperation:
			exitSequence_main_region_main_operation_On_r1_NormalOperation();
			break;
		case main_region_main_operation_On_r1_Warning:
			exitSequence_main_region_main_operation_On_r1_Warning();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region_main_operation__choice_0() {
		if (check_main_region_main_operation__choice_0_tr0_tr0()) {
			effect_main_region_main_operation__choice_0_tr0();
		} else {
			effect_main_region_main_operation__choice_0_tr1();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region_main_evaluation__choice_0() {
		if (check_main_region_main_evaluation__choice_0_tr0_tr0()) {
			effect_main_region_main_evaluation__choice_0_tr0();
		} else {
			effect_main_region_main_evaluation__choice_0_tr1();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_main_operation_On_r1__entry_Default() {
		enterSequence_main_region_main_operation_On_r1_NormalOperation_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_main_operation__entry_Default() {
		enterSequence_main_region_main_operation_On_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_main_evaluation__entry_Default() {
		enterSequence_main_region_main_evaluation_working_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		setOnsensors(3);
		
		setOksensors(3);
		
		setOffsensors(0);
		
		enterSequence_main_region_main_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_main_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (react()==false) {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_main_operation_Off_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (main_region_main_react(try_transition)==false) {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_main_operation_On_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (main_region_main_react(try_transition)==false) {
				if ((sCIS1HW.det || (sCIS2HW.det || sCIS3HW.det))) {
					exitSequence_main_region_main_operation_On();
					setOnsensors(getOnsensors() - 1);
					
					setOksensors(getOksensors() - 1);
					
					react_main_region_main_operation__choice_0();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_main_operation_On_r1_NormalOperation_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (main_region_main_operation_On_react(try_transition)==false) {
				if (sCIS1HW.det) {
					exitSequence_main_region_main_operation_On_r1_NormalOperation();
					enterSequence_main_region_main_operation_On_r1_Warning_default();
				} else {
					if (sCIS2HW.det) {
						exitSequence_main_region_main_operation_On_r1_NormalOperation();
						enterSequence_main_region_main_operation_On_r1_Warning_default();
					} else {
						if (sCIS3HW.det) {
							exitSequence_main_region_main_operation_On_r1_NormalOperation();
							enterSequence_main_region_main_operation_On_r1_Warning_default();
						} else {
							did_transition = false;
						}
					}
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_main_operation_On_r1_Warning_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (main_region_main_operation_On_react(try_transition)==false) {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_main_evaluation_working_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if ((sCIS1HW.latent || (sCIS2HW.latent || sCIS3HW.latent))) {
				exitSequence_main_region_main_evaluation_working();
				setLatentsensors(getLatentsensors() + 1);
				
				setOksensors(getOksensors() - 1);
				
				react_main_region_main_evaluation__choice_0();
			} else {
				if (sCIUCHW.shutdown) {
					exitSequence_main_region_main_evaluation_working();
					enterSequence_main_region_main_evaluation_ShutdownState_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_main_evaluation_ShutdownState_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		return did_transition;
	}
	
	private boolean main_region_main_evaluation_SelfSteeringState_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		return did_transition;
	}
	
}
