package com.marakana;

import java.io.File;
import java.io.FileInputStream;

import com.example.ddsapp.MainActivity;
import com.example.ddsapp.compartirRecurso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;


public class ResourceConnector implements IResource 
{
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
		
		CameraAct.preview.camera.stopPreview();
		availability = true;
	}

	public boolean receiveAction(int i, String[] s) {		
		
	try{
		
		intent = new Intent(CameraActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		byte[] data = CameraAct.preview.getFoto();
		notifyAllObservers(data);
		return true;
	}
	catch(Exception e)
	{
		String o = e.getMessage();
		observer.consumptionFailed(id, o);
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
