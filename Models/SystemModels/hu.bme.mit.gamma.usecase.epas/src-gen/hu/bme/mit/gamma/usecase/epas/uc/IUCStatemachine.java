/** Generated by YAKINDU Statechart Tools code generator. */
package hu.bme.mit.gamma.usecase.epas.uc;

import hu.bme.mit.gamma.usecase.epas.IStatemachine;
import java.util.List;

public interface IUCStatemachine extends IStatemachine {
	public interface SCIHWFault {
	
		public void raiseShutdown();
		
	}
	
	public SCIHWFault getSCIHWFault();
	
	public interface SCIFault {
	
		public boolean isRaisedShutdown();
		
	public List<SCIFaultListener> getListeners();
	}
	
	public interface SCIFaultListener {
	
		public void onShutdownRaised();
		}
	
	public SCIFault getSCIFault();
	
}
