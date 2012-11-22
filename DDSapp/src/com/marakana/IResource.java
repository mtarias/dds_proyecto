package com.marakana;

public interface IResource {
	
	public boolean isAvailable();	
	public void cancelConsumption();
	
	//public void setObserver(IConsumptionObs observer);
	
	public boolean receiveAction(int i, String [] s);
	public int getStatus(); 
	public void setId(int i);
}
