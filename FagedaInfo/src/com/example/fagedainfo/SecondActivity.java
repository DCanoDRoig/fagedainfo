package com.example.fagedainfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private TextView nomtxt;
	private ImageView foto;
	private Button gelats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		String nom = getIntent().getStringExtra(MainActivity.NOM);
		String cognom = getIntent().getStringExtra(MainActivity.COGNOM);
		File imatge = (File) getIntent().getSerializableExtra(
				MainActivity.TEMPIMAGEFILE);

		nomtxt = (TextView) findViewById(R.id.textView1);
		foto = (ImageView) findViewById(R.id.imageView1);
		gelats = (Button) findViewById(R.id.gelats);

		nomtxt.setText("Benvingut " + nom + " " + cognom);
		/*foto.setImageBitmap(Media.getBitmap(getContentResolver(),
				Uri.fromFile(imatge)));*/
		foto.setImageBitmap(obtenirImatgeFromResource(100, 100, imatge));

		gelats.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(SecondActivity.this, GelatsActivity.class);
				startActivity(i);

			}
		});

	}

	public static int calcularInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// alçadai ampladade la imatge
		int height = options.outHeight;
		int width = options.outWidth;
		int inSampleSize = 1;
		int heightRatio, widthRatio;
		if (height > reqHeight || width > reqWidth) {
			heightRatio = Math.round((float) height / (float) reqHeight);
			widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap obtenirImatgeFromResource(int reqWidth, int reqHeight, File imatge) {
		// Primer, descodificar el bitmapambinJustDecodeBounds=true
		// per comprovarles dimensions
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imatge.getAbsolutePath());
		// Calcular inSampleSize
		options.inSampleSize = calcularInSampleSize(options, reqWidth,
				reqHeight);
		// Descodificar el bitmapambel valor indicatde inSampleSize
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(imatge.getAbsolutePath());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
