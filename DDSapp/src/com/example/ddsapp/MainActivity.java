package com.example.ddsapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }
    
    

    
}
