package com.example.ddsapp;

import java.util.ArrayList;
import java.util.Iterator;

import cl.puc.dds.appmgr.external.IResource;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class buscarRecurso extends Activity {

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_recurso_activity);
        getIntent();
        
        MyApplication app = new MyApplication();
        
        ArrayList<IResource> ListaRecursos = app.getAllForeignResources();
        String[] ListaTipoRecurso = new String[ListaRecursos.size()];
        int j = 0;
        
        for(Iterator<IResource> i = ListaRecursos.iterator(); i.hasNext(); ) {
        	  IResource resource = i.next();
        	  String type = resource.getType();
        	  ListaTipoRecurso[j] = type;
        	  j++;
        	}
        
        
    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        
	        return true;
	        
	 }
	
	
}
