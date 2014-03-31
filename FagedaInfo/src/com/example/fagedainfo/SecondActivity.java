package com.example.fagedainfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity {
	
	private TextView nomtxt;
	private ImageView foto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		String nom = getIntent().getStringExtra(MainActivity.NOM);
		String cognom = getIntent().getStringExtra(MainActivity.COGNOM);
		File imatge = (File) getIntent().getSerializableExtra(MainActivity.TEMPIMAGEFILE);
		
		nomtxt = (TextView) findViewById(R.id.textView1);
		foto = (ImageView) findViewById(R.id.imageView1);
		
		nomtxt.setText("Benvingut " + nom +" "+ cognom);
		try {
			foto.setImageBitmap(Media.getBitmap(getContentResolver(),Uri.fromFile(imatge)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
