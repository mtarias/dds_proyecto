package com.marakana;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class mainActivity extends Activity {
	
	ResourceConnector rc = new ResourceConnector();
	
	CameraObserver co = new CameraObserver();
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        
	        rc.setObserver(co);
	        String[] algo = {"1","2"};
	        
	        if(rc.receiveAction(0, algo))
	        {	               
	        rc.receiveAction(0, algo);
	        byte[] foto = co.getData();
	        Bitmap bmp=BitmapFactory.decodeByteArray(foto,0,foto.length);
	        ImageView image= (ImageView) findViewById(R.id.imageView1);
	        image.setImageBitmap(bmp);
	        }
	    }

}
