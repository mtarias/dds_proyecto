package com.example.ddsapp;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class compartirRecurso extends Activity {

	private MyApplication app;
	private ArrayList<IResource> ListaRecursosLocales; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compartir_recurso_activity);
        getIntent();
        app = new MyApplication();
        
       // ListaRecursosLocales = app.getAllLocalResources();
        
        for(Iterator<IResource> i = ListaRecursosLocales.iterator(); i.hasNext(); ) {
        	  IResource resource = i.next();
      //  	  resource. = true;
        	}
        
        
    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        
	        return true;
	        
	 }
	
}
