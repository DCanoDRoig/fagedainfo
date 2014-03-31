package com.example.fagedainfo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Classe que mostra un exemple de com captura una foto fent ús de les
 * aplicacions que ja hi pugui haver instal·lades en el dispostiu
 * 
 * @author Marc Nicolau
 * 
 */
public class MainActivity extends Activity {
	
	public final static String NOM = "NOM";
	public final static String COGNOM = "COGNOM";
	public final static String TEMPIMAGEFILE = "TEMPIMAGEFILE";

	// un codi per l'aplicació a obrir
	static final int CAMERA_APP_CODE = 100;

	private EditText nom, cognom;
	private ImageButton ferfoto;
	// el fitxer on es guardarà la imatge
	private File tempImageFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		nom = (EditText) findViewById(R.id.editText1);
		cognom = (EditText) findViewById(R.id.editText2);
		ferfoto = (ImageButton) findViewById(R.id.buttonFoto);		
		
	}

	/**
	 * Mètode que comprova si hi ha una aplicició per a captura de fotos
	 * 
	 * @param context
	 * @param action
	 * @return true si existeix, false en cas contrari
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * Mètode que respon a l'event clic del botó
	 * 
	 * @param view
	 * @throws IOException
	 */
	public void ferUnaFoto(View view) throws IOException {
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			// intenció de fer una foto
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// crear la ruta del fitxer on desar la foto
			tempImageFile = crearFitxer();
			// li passem paràmetres a l'Inent per indicar que es vol guarda
			// la captura en un fitxer
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempImageFile));
			// inciar l'intent
			startActivityForResult(takePictureIntent, CAMERA_APP_CODE);
		} else {
			Toast.makeText(this, "No hi ha cap aplicació per capturar fotos",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_APP_CODE) {
			if (resultCode == RESULT_OK) {
				// mostrar el fitxer que ha desat l'app de captura
				/*imatge.setImageBitmap(Media.getBitmap(getContentResolver(),
						Uri.fromFile(tempImageFile)));*/
				Intent i = new Intent(MainActivity.this, SecondActivity.class);
				
				i.putExtra(NOM, nom.getText().toString());
				i.putExtra(COGNOM, cognom.getText().toString());
				i.putExtra(TEMPIMAGEFILE, tempImageFile);
				
				startActivity(i);
			}
		}
	}

	/**
	 * Crea la ruta absoluta per a un nou fitxer temporal
	 * @return L'objecte File que representa el fitxer
	 */
	private File crearFitxer() {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "foto" + timeStamp + ".jpg";
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());
		if (!path.exists())
			path.mkdirs();

		return new File(path, imageFileName);
	}

}

