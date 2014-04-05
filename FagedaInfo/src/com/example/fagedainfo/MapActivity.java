package com.example.fagedainfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends Activity{
	
	private static final LatLng FAGEDA = new LatLng(42.1388937,2.5217332);
	
	private GoogleMap mapa;
	private Button ruta;
	MarkerOptions markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        ruta = (Button) findViewById(R.id.btnRuta);
        configurarMapa();
        
        mapa.animateCamera(
				CameraUpdateFactory.newLatLngZoom(FAGEDA, 17),
				2000, null);
        
        mapa.addMarker(
				new MarkerOptions()
						// aplica un marcador al mapa
						.position(FAGEDA)
						.snippet(
								"Estudis: ESO, Batxillerat, Cicles Formatius i CAS"))
				.setIcon(BitmapDescriptorFactory//canvia el color de la icona
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        
        ruta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Toast.makeText(MapActivity.this, "Under Construction. Se siente xD", Toast.LENGTH_LONG).show();	  
		        
			}
		});
    }    
    
    private void configurarMapa() {
		// Fer una comprovació de l'objecte map amb null per confirmar
		// que no l'hàgim instanciat prèviament
		if (mapa == null) {
			mapa = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// Comprovar si s'ha obtingut correctament l'objecte
			if (mapa != null) {
				// El mapa s'ha comprovat. Ara es pot manipular
				mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);

				mapa.getUiSettings().setCompassEnabled(true);// fica la bruixola
			}
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }
    
}
