package com.example.fagedainfo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher;

public class GelatsActivity extends Activity implements ViewSwitcher.ViewFactory{
 
	GridView gridView;
 
	public static Gelat[] gelats;
	private TextSwitcher mSwitcher;
	private TextSwitcher nomSwitcher;
	
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Typeface type = Typeface.createFromAsset(getAssets(),"bellevuebq.ttf"); 
		TextView titol = (TextView) findViewById(R.id.Titol);
		titol.setTypeface(type);
		titol.setTextSize(40);
		
		nomSwitcher = (TextSwitcher) findViewById(R.id.nomGelat);
		nomSwitcher.setFactory(this);
		

		
        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
        mSwitcher.setFactory(this);
     
        TextView textnom = (TextView) nomSwitcher.getChildAt(nomSwitcher.getChildCount()-1);
		textnom.setTextSize(21);
		textnom = (TextView) nomSwitcher.getChildAt(nomSwitcher.getChildCount()-2);
			textnom.setTextSize(21);
 
        Animation in = AnimationUtils.loadAnimation(this,  android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        nomSwitcher.setInAnimation(in);
        nomSwitcher.setOutAnimation(out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        nomSwitcher.setText("Varietats");
        mSwitcher.setText("El gelat de llet de La Fageda s’elabora amb llet fresca pasteuritzada i nata de la nostra granja de la Garrotxa, que es combinen amb la resta d’ingredients, també naturals i sense colorants ni conservants. A l’obrador de la nova fàbrica de Badalona utilitzem formulacions i mètodes tradicionals.");
		
		gelats = new Gelat[5];
		gelats[0]=new Gelat(R.drawable.vainilla,"Vainilla i nous de macalania","Dos sabors intensos, en una combinació plena de força: la cremositat del producte lacti natural amb la macadàmia, Un dels fruits secs més selectes i elegants del món.");
		gelats[3]=new Gelat(R.drawable.maduixa ,"Nata i maduixes","Una de les postres més suggestives, ara també feta gelat. Pel seu sabor i el seu agradable aroma, les maduixes són l'estrella de la primavera.");
		gelats[4]=new Gelat(R.drawable.fruitesdelbosc,"Yogurt amb fruites del bosc","Una opció pels que més es cuiden. La combinació del yogurt de sempre amb nabius, una font de vitamines, minerals i antioxidants.");
		gelats[2]=new Gelat(R.drawable.yogurt ,"Yogurt de granja","El gelat més genuí de la Fageda. Un gelat amb el gust del yogurt de sempre, elaborat amb la llet de les nostres vaques.");
		gelats[1]=new Gelat(R.drawable.xocolata,"Xocolata amb trossets de xocolata","La combinació ideal per els enamorats de la xocolata. Redescobreix l'autèntic plaer de la xocolata elaborada amb mètodes tradicionals i productes naturals, sense colorants ni conservants");
		
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setStretchMode(2);
		gridView.setAdapter(new GelatAdapter(this, gelats));
		
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				
				nomSwitcher.setText(gelats[position].nom);
				mSwitcher.setText(gelats[position].descripcio);
				
				
				

			}
		});
 
	}



	@Override
	public View makeView() {
		TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
		return t;
	}
 
}