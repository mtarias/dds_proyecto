package com.example.ddsapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;


public class CameraManager{
  private static final String TAG = "CameraDemo";
  
  byte[] foto;
  
  //** Called when the activity is first created. *//*
  
  public byte[] getFoto()
  {
	  return foto;
  }
  
public byte[] takePicture()
{
	
	
	
	Camera cam = Camera.open();
	cam.startPreview();
	cam.takePicture(shutterCallback, rawCallback, jpegCallback);
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Log.v(TAG, ""+foto.toString());
	
	return foto;
}
 

  // Called when shutter is opened
  ShutterCallback shutterCallback = new ShutterCallback() { // <6>
    public void onShutter() {
      Log.d(TAG, "onShutter'd");
    }
  };

  // Handles data for raw picture
  PictureCallback rawCallback = new PictureCallback() { // <7>
    public void onPictureTaken(byte[] data, Camera camera) {
      Log.d(TAG, "onPictureTaken - raw");
    }
  };

  // Handles data for jpeg picture
  PictureCallback jpegCallback = new PictureCallback() { // <8>
    public void onPictureTaken(byte[] data, Camera camera) {
      FileOutputStream outStream = null;
      try {
        // Write to SD Card
    	foto = data;
        outStream = new FileOutputStream(String.format("/sdcard/%d.jpg",
            System.currentTimeMillis())); // <9>
        outStream.write(data);
        outStream.close();
        Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
      } catch (FileNotFoundException e) { // <10>
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
      }
      Log.d(TAG, "onPictureTaken - jpeg");
    }
  };

}