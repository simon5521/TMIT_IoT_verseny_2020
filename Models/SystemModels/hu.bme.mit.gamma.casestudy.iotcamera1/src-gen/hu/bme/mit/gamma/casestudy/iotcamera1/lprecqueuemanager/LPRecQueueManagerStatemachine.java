/** Generated by YAKINDU Statechart Tools code generator. */
package hu.bme.mit.gamma.casestudy.iotcamera1.lprecqueuemanager;

import java.util.LinkedList;
import java.util.List;

public class LPRecQueueManagerStatemachine implements ILPRecQueueManagerStatemachine {
	protected class SCIPreProcessedImgImpl implements SCIPreProcessedImg {
	
		private boolean newData;
		
		
		public void raiseNewData() {
			newData = true;
		}
		
		protected void clearEvents() {
			newData = false;
		}
	}
	
	
	protected class SCIPreProcessedImgOutImpl implements SCIPreProcessedImgOut {
	
		private List<SCIPreProcessedImgOutListener> listeners = new LinkedList<SCIPreProcessedImgOutListener>();
		
		public List<SCIPreProcessedImgOutListener> getListeners() {
			return listeners;
		}
		private boolean newData;
		
		
		public boolean isRaisedNewData() {
			return newData;
		}
		
		protected void raiseNewData() {
			newData = true;
			for (SCIPreProcessedImgOutListener listener : listeners) {
				listener.onNewDataRaised();
			}
		}
		
		protected void clearEvents() {
		}
		protected void clearOutEvents() {
		
		newData = false;
		}
		
	}
	
	
	protected class SCIProcessedImgImpl implements SCIProcessedImg {
	
		private boolean newData;
		
		
		public void raiseNewData() {
			newData = true;
		}
		
		protected void clearEvents() {
			newData = false;
		}
	}
	
	
	protected class SCIStatusImpl implements SCIStatus {
	
		private List<SCIStatusListener> listeners = new LinkedList<SCIStatusListener>();
		
		public List<SCIStatusListener> getListeners() {
			return listeners;
		}
		private boolean full;
		
		
		public boolean isRaisedFull() {
			return full;
		}
		
		protected void raiseFull() {
			full = true;
			for (SCIStatusListener listener : listeners) {
				listener.onFullRaised();
			}
		}
		
		private boolean free;
		
		
		public boolean isRaisedFree() {
			return free;
		}
		
		protected void raiseFree() {
			free = true;
			for (SCIStatusListener listener : listeners) {
				listener.onFreeRaised();
			}
		}
		
		protected void clearEvents() {
		}
		protected void clearOutEvents() {
		
		full = false;
		free = false;
		}
		
	}
	
	
	protected class SCINetworkStreamImpl implements SCINetworkStream {
	
		private List<SCINetworkStreamListener> listeners = new LinkedList<SCINetworkStreamListener>();
		
		public List<SCINetworkStreamListener> getListeners() {
			return listeners;
		}
		private boolean newData;
		
		
		public boolean isRaisedNewData() {
			return newData;
		}
		
		protected void raiseNewData() {
			newData = true;
			for (SCINetworkStreamListener listener : listeners) {
				listener.onNewDataRaised();
			}
		}
		
		protected void clearEvents() {
		}
		protected void clearOutEvents() {
		
		newData = false;
		}
		
	}
	
	
	protected SCIPreProcessedImgImpl sCIPreProcessedImg;
	
	protected SCIPreProcessedImgOutImpl sCIPreProcessedImgOut;
	
	protected SCIProcessedImgImpl sCIProcessedImg;
	
	protected SCIStatusImpl sCIStatus;
	
	protected SCINetworkStreamImpl sCINetworkStream;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_NotFull,
		main_region_Full,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	private long queueMaxLen;
	
	protected long getQueueMaxLen() {
		return queueMaxLen;
	}
	
	protected void setQueueMaxLen(long value) {
		this.queueMaxLen = value;
	}
	
	
	private long queueLen;
	
	protected long getQueueLen() {
		return queueLen;
	}
	
	protected void setQueueLen(long value) {
		this.queueLen = value;
	}
	
	
	public LPRecQueueManagerStatemachine() {
		sCIPreProcessedImg = new SCIPreProcessedImgImpl();
		sCIPreProcessedImgOut = new SCIPreProcessedImgOutImpl();
		sCIProcessedImg = new SCIProcessedImgImpl();
		sCIStatus = new SCIStatusImpl();
		sCINetworkStream = new SCINetworkStreamImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		setQueueMaxLen(4);
		
		setQueueLen(0);
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
			case main_region_NotFull:
				main_region_NotFull_react(true);
				break;
			case main_region_Full:
				main_region_Full_react(true);
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
		return stateVector[0] != State.$NullState$;
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
		sCIPreProcessedImg.clearEvents();
		sCIPreProcessedImgOut.clearEvents();
		sCIProcessedImg.clearEvents();
		sCIStatus.clearEvents();
		sCINetworkStream.clearEvents();
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCIPreProcessedImgOut.clearOutEvents();
		sCIStatus.clearOutEvents();
		sCINetworkStream.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_NotFull:
			return stateVector[0] == State.main_region_NotFull;
		case main_region_Full:
			return stateVector[0] == State.main_region_Full;
		default:
			return false;
		}
	}
	
	public SCIPreProcessedImg getSCIPreProcessedImg() {
		return sCIPreProcessedImg;
	}
	
	public SCIPreProcessedImgOut getSCIPreProcessedImgOut() {
		return sCIPreProcessedImgOut;
	}
	
	public SCIProcessedImg getSCIProcessedImg() {
		return sCIProcessedImg;
	}
	
	public SCIStatus getSCIStatus() {
		return sCIStatus;
	}
	
	public SCINetworkStream getSCINetworkStream() {
		return sCINetworkStream;
	}
	
	private boolean check_main_region__choice_0_tr0_tr0() {
		return getQueueLen()==getQueueMaxLen();
	}
	
	private void effect_main_region__choice_0_tr0() {
		enterSequence_main_region_Full_default();
	}
	
	private void effect_main_region__choice_0_tr1() {
		enterSequence_main_region_NotFull_default();
	}
	
	/* Entry action for state 'NotFull'. */
	private void entryAction_main_region_NotFull() {
		sCIStatus.raiseFree();
	}
	
	/* Entry action for state 'Full'. */
	private void entryAction_main_region_Full() {
		sCIStatus.raiseFull();
	}
	
	/* 'default' enter sequence for state NotFull */
	private void enterSequence_main_region_NotFull_default() {
		entryAction_main_region_NotFull();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_NotFull;
	}
	
	/* 'default' enter sequence for state Full */
	private void enterSequence_main_region_Full_default() {
		entryAction_main_region_Full();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Full;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state NotFull */
	private void exitSequence_main_region_NotFull() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Full */
	private void exitSequence_main_region_Full() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_NotFull:
			exitSequence_main_region_NotFull();
			break;
		case main_region_Full:
			exitSequence_main_region_Full();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_0() {
		if (check_main_region__choice_0_tr0_tr0()) {
			effect_main_region__choice_0_tr0();
		} else {
			effect_main_region__choice_0_tr1();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_NotFull_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_NotFull_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (react()==false) {
				if (sCIPreProcessedImg.newData) {
					exitSequence_main_region_NotFull();
					setQueueLen((queueLen + 1));
					
					sCIPreProcessedImgOut.raiseNewData();
					
					react_main_region__choice_0();
				} else {
					if (sCIProcessedImg.newData) {
						exitSequence_main_region_NotFull();
						setQueueLen((queueLen - 1));
						
						sCINetworkStream.raiseNewData();
						
						enterSequence_main_region_NotFull_default();
					} else {
						did_transition = false;
					}
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Full_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (react()==false) {
				if (sCIProcessedImg.newData) {
					exitSequence_main_region_Full();
					setQueueLen((queueLen - 1));
					
					sCINetworkStream.raiseNewData();
					
					enterSequence_main_region_NotFull_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
}