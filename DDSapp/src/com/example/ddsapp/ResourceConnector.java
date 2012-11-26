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

	IConsumptionObs observer = new CameraObserver();


	void notifyAllObservers(byte[] data) {	        
		observer.consumptionFinished(id, (Object)data);
	}


	public boolean isAvailable() {
		return availability;
	}

	public void cancelConsumption() {

		CameraActivity.preview.camera.stopPreview();
		availability = true;
	}

	public boolean receiveAction(int i, String[] s) {		

		try{
			
			Thread toRun = new Thread()
		       {
		              public void run()
		              {
		            	  Log.v("run","");
		            	  Notificar();
		              }
		       };
		       toRun.start();

			Context context = MainActivity.getAppContext();
			Intent intent = new Intent(context, CameraActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			
			
			
			
			
			

			/*byte[] data = CameraActivity.getFoto();

			Log.v("Foto1",""+data);
			while(data==null)
			{
				data = CameraActivity.getFoto();
			}
			notifyAllObservers(data);
			Log.v("Foto2",""+data);*/
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

	public void Notificar()
	{
		byte[] foto = CameraActivity.preview.getFoto();
		Log.v("Foto", ""+foto);
		while(foto == null)
		{
			Log.v("", "Esperando...");
			foto = CameraActivity.getFoto();
		}
		notifyAllObservers(foto);
		Log.v(":","Notifico");
	}

}
