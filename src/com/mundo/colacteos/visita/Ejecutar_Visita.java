package com.mundo.colacteos.visita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.mundo.colacteos.MainActivity;
import com.mundo.colacteos.R;
import com.mundo.colacteos.sistema.SimpleFileDialog;

public class Ejecutar_Visita extends Activity {
	
//---------------------------------------------------------------------
//atributos
//---------------------------------------------------------------------
	private Button x;

//-----------------------------------------------------------------------
//metodo que permite crear la instancia de la clase
//-----------------------------------------------------------------------
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ejecutar_visita);
		Button dirChooserButton = (Button) findViewById(R.id.chooseDirButton);
        dirChooserButton.setOnClickListener(new OnClickListener() 
        {
			String m_chosen;
			@Override
			public void onClick(View v) {
				/////////////////////////////////////////////////////////////////////////////////////////////////
				//Create FileOpenDialog and register a callback
				/////////////////////////////////////////////////////////////////////////////////////////////////
				SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(Ejecutar_Visita.this, "FileOpen",
						new SimpleFileDialog.SimpleFileDialogListener()
				{
					@Override
					public void onChosenDir(String chosenDir) 
					{
						// The code in this function will be executed when the dialog OK button is pushed 
						m_chosen = chosenDir;
						Toast.makeText(Ejecutar_Visita.this, "Chosen FileOpenDialog File: " + 
								m_chosen, Toast.LENGTH_LONG).show();
						importDataFromCSV(m_chosen);
					}
				});
				
				//You can change the default filename using the public variable "Default_File_Name"
				FileOpenDialog.Default_File_Name = "";
				FileOpenDialog.chooseFile_or_Dir();
				
				/////////////////////////////////////////////////////////////////////////////////////////////////

			}
		});
	}
	
	public void importDataFromCSV(String ruta) {
		int i = 0;

		boolean flag_is_header = false;

		File file = new File(ruta);
		if (file.exists()) {
		}
		BufferedReader bufRdr = null;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;

		try {
			/*while ((line = bufRdr.readLine()) != null) {

				String[] insertValues = line.split(";");
				if (flag_is_header) {

					dbHandler = new DatabaseHandler(context);
					dbHandler.open();
					long row=dbHandler.insertUserInfo(insertValues[1],
							insertValues[2]);
					Log.e("no. of rows inserted", ""+row);

				} else {
					flag_is_header = true;
				}
			}
			dbHandler.close();*/
			bufRdr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
        
}