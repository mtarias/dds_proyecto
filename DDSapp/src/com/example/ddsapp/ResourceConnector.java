package com.example.ddsapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;


public class ResourceConnector implements IResource 
{
	Context context;
	Intent intent;
	boolean availability = true;
	int id = 0;

	IConsumptionObs observer;

	
	void notifyAllObservers(byte[] data) {	        
        observer.consumptionFinished(id, (Object)data);
    }
	
	
	public boolean isAvailable() {
		return availability;
	}

	public void cancelConsumption() {
		
		//CameraAct.preview.camera.stopPreview();
		availability = true;
	}

	public boolean receiveAction(int i, String[] s) {		
		
	try{
		CameraActivity ca = new CameraActivity();
		Context context = MainActivity.getAppContext();
		Intent intent = new Intent(context, CameraActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		
		byte[] data = ca.preview.getFoto();
	notifyAllObservers(data);
		return true;
	}
	catch(Exception e)
	{
		Log.e("Exception", ""+e.toString()+"--"+e.fillInStackTrace());
		//String o = e.getMessage();
		//observer.consumptionFailed(id, o);
		return false;
	}
	}

	public int getStatus() {
		return 0;
	}

	public void setId(int i) {
		id = i;
	}

	public void setObserver(IConsumptionObs observer) {
		this.observer = observer;
		
	}

}
