package com.mundo.colacteos.visita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bd.colacteos.SAT_VisitaDao;
import com.mundo.colacteos.R;
import com.mundo.colacteos.sistema.SimpleFileDialog;

public class Ejecutar_Visita extends Activity {
	
//---------------------------------------------------------------------
//atributos
//---------------------------------------------------------------------
	private Button x;
	
	// --------------------------------------------
	// enlace a la clase vistadao
	// -------------------------------------------------

	SAT_VisitaDao visitaDao = new SAT_VisitaDao(this);

//-----------------------------------------------------------------------
//metodo que permite crear la instancia de la clase
//-----------------------------------------------------------------------
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ejecutar_visita);
		Button dirChooserButton = (Button) findViewById(R.id.chooseDirButton);
		
		//Iniciar atributos
		visitaDao = new SAT_VisitaDao(this);
		visitaDao.abrir();
		
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
						Toast.makeText(Ejecutar_Visita.this, "Ruta: " + 
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
		String id_visita = null;
		String cod_fincas = null;
		String fecha_visita = null;
		String hora_ingreso = null;
		String hora_salida = null;
		String clase_visita = null;
		String cumple = null;
		String num_recetario = null;
		String nit_profesional = null;
		String tipo_visita = null;
		String observaciones = null;
		String identificacion_asociado = null;

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
			while ((line = bufRdr.readLine()) != null) {

				String[] insertValues = line.split(";");
				if (flag_is_header) {
					
					id_visita = insertValues[0];
					cod_fincas = insertValues[1];
					fecha_visita = insertValues[2];
					hora_ingreso = insertValues[3];
					hora_salida = insertValues[4];
					clase_visita = insertValues[5];
					cumple = insertValues[6];
					num_recetario = insertValues[7];
					nit_profesional = insertValues[8];
					tipo_visita = insertValues[9];
					observaciones = insertValues[10];
					identificacion_asociado = insertValues[11];
					
					long idInsert = visitaDao.agregarVisita(
							id_visita, cod_fincas, fecha_visita, hora_ingreso, hora_salida, clase_visita, 
							cumple, num_recetario, nit_profesional, tipo_visita, observaciones, identificacion_asociado);
					
					if (idInsert == -500){
						Toast.makeText(Ejecutar_Visita.this, "el id de la visita ya existe", Toast.LENGTH_LONG).show();
						TextView lblLog = (TextView)findViewById(R.id.lblLog);
						lblLog.setText("El id " + id_visita + " ya existe " + "\n");
					}

					//dbHandler = new DatabaseHandler(context);
					//dbHandler.open();
					//long row=dbHandler.insertUserInfo(insertValues[1],
					//		insertValues[2]);
					//Log.e("no. of rows inserted", ""+row);

				} else {
					flag_is_header = true;
				}
			}
			//dbHandler.close();
			bufRdr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
        
}