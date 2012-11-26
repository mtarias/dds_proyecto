package com.example.ddsapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private CameraObserver co = new CameraObserver();
	private static Context context;
	ResourceConnector rc = new ResourceConnector();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        
      
        
        //Botón compartir recurso
        
        Button but = (Button) findViewById(R.id.button1);
        but.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    						
    			Intent intent = new Intent(MainActivity.this,compartirRecurso.class);
    			startActivity(intent);
    			
    		}
    	}); 
        
        
        //Botón buscar recursos
        
        Button bib = (Button) findViewById(R.id.button2);
        bib.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    						
    			Intent intent = new Intent(MainActivity.this,buscarRecurso.class);
    			startActivity(intent);
    			
    		}
    	}); 
        
        Button bot3 = (Button) findViewById(R.id.button3);
        bot3.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    					
    			
    			
    			rc.setObserver(co);
    			String[] s = new String[2];
    			s[0] = "hola";
    			s[1] = "chao";
    			
    			rc.receiveAction(1, s);
    			
    			//byte[] foto = co.getData();
    			//Bitmap bmp=BitmapFactory.decodeByteArray(foto,0,foto.length);
    	        //ImageView image= (ImageView) findViewById(R.id.imageView1);
    	        //image.setImageBitmap(bmp);
    	        //rc.cancelConsumption();
    	        //co.consumptionFinished(1, foto);
    			
    		
    			
    		}
    	});
        
        Button bot5 = (Button) findViewById(R.id.button5);
        bot5.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    					
    			
    			
    			rc.setObserver(co);
    			String[] s = new String[2];
    			s[0] = "hola";
    			s[1] = "chao";
    			
    			rc.receiveAction(2, s);
    			
    			//byte[] foto = co.getData();
    			//Bitmap bmp=BitmapFactory.decodeByteArray(foto,0,foto.length);
    	        //ImageView image= (ImageView) findViewById(R.id.imageView1);
    	        //image.setImageBitmap(bmp);
    	        //rc.cancelConsumption();
    	        //co.consumptionFinished(1, foto);
    			
    		
    			
    		}
    	});
        Button bot4 = (Button) findViewById(R.id.button4);
        bot4.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    						
    			Log.v("ver Foto","A");
    			if(getIntent().getExtras().getByteArray("foto")!=null)
    			{
    				Log.v("ver Foto","B");
    				byte[] foto = getIntent().getExtras().getByteArray("foto");
    				Bitmap bmp=BitmapFactory.decodeByteArray(foto,0,foto.length);
        	        ImageView image= (ImageView) findViewById(R.id.imageView1);
        	        image.setImageBitmap(bmp);
        	        rc.notifyAllObservers(foto);
        	        Log.v("Foto", ""+foto);
    			}
    			
    		}
    	}); 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }
    
    public static Context getAppContext() {
  	  return context;
    }
   
    

    
}
