package com.example.ddsapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
  private static final String TAG = "CameraDemo";
  private static Context context;
  Preview preview; // <1>
  Button buttonClick; // <2>
  Button boton2;
  byte[] foto;
  
  //** Called when the activity is first created. *//*
  
  public byte[] getFoto()
  {
	  return foto;
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.camera_activity_layout);
    CameraActivity.context = getApplicationContext();
    preview = new Preview(this); // <3>
    ((FrameLayout) findViewById(R.id.preview)).addView(preview); // <4>

    buttonClick = (Button) findViewById(R.id.buttonClick);
    buttonClick.setOnClickListener(new OnClickListener() {
      public void onClick(View v) { // <5>
        preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
      }
    });
    
    boton2 = (Button) findViewById(R.id.boton2);
    boton2.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			//preview.camera.startPreview();
			preview.camera.stopPreview();
			preview.camera.release();
			
			Intent intent = new Intent(CameraActivity.this,MainActivity.class);
			intent.putExtra("foto", foto);
			startActivity(intent);
			
		}
	});

    Log.d(TAG, "onCreate'd");
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
  
  public static Context getAppContext() {
	  return context;
  }
  

}
