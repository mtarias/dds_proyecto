package com.marakana;

public class CameraObserver implements IConsumptionObs
{
	byte[] data;

	public byte[] getData()
	{
		return data;
	}

	public void consumptionFinished(int i, Object o) {
		// TODO Auto-generated method stub
		data = (byte[]) o;
		
	}

	public void consumptionFailed(int i, Object o) {
		// TODO Auto-generated method stub
		
	}

	public void consumptionInterrupted(int i, Object o) {
		// TODO Auto-generated method stub
		
	}

	public void consumptionUpdate(int i, Object o) {

		data = (byte[]) o;
		
	}
	
	
}
