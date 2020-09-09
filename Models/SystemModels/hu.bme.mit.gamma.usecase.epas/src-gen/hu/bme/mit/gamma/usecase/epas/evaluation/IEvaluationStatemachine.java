/** Generated by YAKINDU Statechart Tools code generator. */
package hu.bme.mit.gamma.usecase.epas.evaluation;

import hu.bme.mit.gamma.usecase.epas.IStatemachine;
import java.util.List;

public interface IEvaluationStatemachine extends IStatemachine {
	public interface SCIAMonitor {
	
		public void raiseWarning();
		
		public void raiseSelfsteering();
		
		public void raiseLoa();
		
	}
	
	public SCIAMonitor getSCIAMonitor();
	
	public interface SCIBMonitor {
	
		public void raiseWarning();
		
		public void raiseSelfsteering();
		
		public void raiseLoa();
		
	}
	
	public SCIBMonitor getSCIBMonitor();
	
	public interface SCIEval {
	
		public boolean isRaisedSS();
		
		public boolean isRaisedSLoA();
		
	public List<SCIEvalListener> getListeners();
	}
	
	public interface SCIEvalListener {
	
		public void onSSRaised();
		public void onSLoARaised();
		}
	
	public SCIEval getSCIEval();
	
}
