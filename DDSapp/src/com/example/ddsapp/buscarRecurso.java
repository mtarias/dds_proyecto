package com.example.ddsapp;

import java.util.ArrayList;
import java.util.Iterator;

import cl.puc.dds.appmgr.external.IResource;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class buscarRecurso extends Activity {

	private MyApplication app;
	private ArrayList<IResource> ListaRecursos;
	private String[] ListaTipoRecurso;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_recurso_activity);
        getIntent();
        
        app = new MyApplication();
        
        ListaRecursos = app.getAllForeignResources();
        //ListaTipoRecurso = new String[ListaRecursos.size()];
        ListaTipoRecurso = new String[2];
        int j = 0;
        
        ListaTipoRecurso[0]= "Hola";
        ListaTipoRecurso[1]= "Hola2";
        
       // for(Iterator<IResource> i = ListaRecursos.iterator(); i.hasNext(); ) {
        //	  IResource resource = i.next();
        	//  String type = resource.getType();
        	  //ListaTipoRecurso[j] = type;
        	  //j++;
        //	}
        
        ArrayAdapter adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, ListaTipoRecurso);
        
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(mMessageClickedHandler); 
        
    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        
	        return true;
	        
	 }
	 
	 private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
		    public void onItemClick(AdapterView parent, View v, int position, long id) {
		       Log.v("BuscarRecurso", "Position: "+position+" id: "+id);
		       IResource resource = ListaRecursos.get(position);
		       app.userResource(resource);
		    }
		};

		
	 
	 
	
	
}
