package com.example.ddsapp;

public interface IConsumptionObs {
	public void consumptionFinished(int i, Object o);
	public void consumptionFailed(int i, Object o);
	public void consumptionInterrupted(int i, Object o);
	public void consumptionUpdate(int i, Object o);
	
}
