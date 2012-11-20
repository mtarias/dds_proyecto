package com.example.ddsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class compartirRecurso extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compartir_recurso_activity);
        getIntent();
    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        
	        return true;
	        
	 }
	
}
