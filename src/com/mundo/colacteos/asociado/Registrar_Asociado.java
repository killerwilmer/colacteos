package com.mundo.colacteos.asociado;

import java.util.ArrayList;
import java.util.List;

import com.bd.colacteos.SQLiteHelper;
import com.bd.colacteos.asociadoDao;
import com.bd.colacteos.fincaDao;
import com.mundo.colacteos.R;
import com.mundo.colacteos.Utilidades;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Registrar_Asociado extends Activity implements
		OnItemSelectedListener {

	// --------------------------------------------
	// enlace a la clase asociadoDao
	// -------------------------------------------------

	asociadoDao asociadoDao = new asociadoDao(this);

	// -----------------------------------------------------------
	// atributos de la clase Registrar asociado
	// -----------------------------------------------------------
	EditText nombres, nit, direccion, telefono;
	Spinner codFinca, tipoIdentificacion;
	private Button registrar, cancelar;
	private Bundle bundle;
	private int seleccion;

	// -----------------------------------------------------------------------
	// metodo que permite instanciar la clase
	// -----------------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrar_asociado);

		// ------------------------------------------------------------------------
		// inicializacion de atributos
		// --------------------------------------------------------------------------
		nit = (EditText) findViewById(R.id.edNit);
		nombres = (EditText) findViewById(R.id.edUsuario);
		direccion = (EditText) findViewById(R.id.edTDirecion);
		telefono = (EditText) findViewById(R.id.edtTelefono);
		tipoIdentificacion = (Spinner) findViewById(R.id.spnTipId);
		codFinca = (Spinner) findViewById(R.id.spnCodFinca);// id del spinner

		asociadoDao = new asociadoDao(this);
		asociadoDao.abrir();

		// ------------------------------------------------------------------------
		// spiner tipo identificacion
		// -------------------------------------------------------------------------

		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.tipoIdSpinner, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tipoIdentificacion.setAdapter(adapter);
		bundle = getIntent().getExtras();
		
		
		//------------------------------------------------------------------------
		//spiner codigo finca
		//------------------------------------------------------------------------
		fincaDao	dbcon= new fincaDao(this);
		dbcon.abrir();
		codFinca=(Spinner)findViewById(R.id.spnCodFinca);//id del spinner
		Cursor cursor=dbcon.darCodigoFinca();
		SQLiteHelper SqlHelper;
		SqlHelper=new SQLiteHelper(this);
		String[] from = new String[] { SqlHelper.codigo_finca}; 
		//SqlHelper.cod_sistema, 
		int[] to = new int[] { android.R.id.text1 };
		//android.R.id.text2};
		SimpleCursorAdapter a= new SimpleCursorAdapter(Registrar_Asociado.this, android.R.layout.simple_list_item_1, cursor, from, to);	
		a.notifyDataSetChanged();
		codFinca.setAdapter(a);


		
	
		
		//---------------------------------------------------------------
		//opcion del boton registrar asociado
		//---------------------------------------------------------------

		// ------------------------------------------------------------------------
		// spiner codigo finca
		// ------------------------------------------------------------------------
		/*fincaDao dbcon = new fincaDao(this);
		dbcon.abrir();
		
		Cursor cursor = dbcon.darCodigoFinca();
		
		List<String> arraylist = new ArrayList<String>();

	    while(cursor.moveToNext()) {
	        arraylist.add(cursor.getString(1));
	    }
	    cursor.close();
	    
	    ArrayAdapter adapterk = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arraylist);
	    codFinca.setAdapter(adapterk);*/
		/*
		SQLiteHelper SqlHelper;
		SqlHelper = new SQLiteHelper(this);
		String[] from = new String[] { SqlHelper.codigo_finca };
		// SqlHelper.cod_sistema,
		int[] to = new int[] { android.R.id.text1 };
		// android.R.id.text2};
		SimpleCursorAdapter a = new SimpleCursorAdapter(
				Registrar_Asociado.this, android.R.layout.simple_list_item_1,
				cursor, from, to);
		a.notifyDataSetChanged();
		
		codFinca.setAdapter(a);*/

		// ---------------------------------------------------------------
		// opcion del boton registrar asociado
		// ---------------------------------------------------------------

		registrar = (Button) findViewById(R.id.btn_registrarAso);
		registrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				 Cursor finca=(Cursor)codFinca.getSelectedItem();
				String nombre_asociado = nombres.getText().toString();
				String Nit = nit.getText().toString();
				//String finca_asociado = codFinca.getSelectedItem().toString();
				String tipIdentificacion = tipoIdentificacion.getSelectedItem()	.toString();
				String direccion_asociado = direccion.getText().toString();
				String telefono_asociado = telefono.getText().toString();

				String finca_asociado=finca.getString(finca.getColumnIndex("codigo_finca"));

				if (nombre_asociado.trim().equals("") || Nit.trim().equals("")
						|| finca_asociado.trim().equals("")
						|| tipIdentificacion.trim().equals("")
						|| direccion_asociado.trim().equals("")
						|| telefono_asociado.trim().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Todos los campos son necesarios", 1000).show();
				} else {
					
					int estado=0;
					

					estado = asociadoDao.crearAsociado(Nit, finca_asociado,
							nombre_asociado, direccion_asociado,
							telefono_asociado, tipIdentificacion);
					if(estado== -500){
						Toast.makeText(getApplicationContext(), "Este nit ya esta registrdo", 1000).show();
					}
					else{
						if (asociadoDao == null) {
							Toast.makeText(getApplicationContext(),
									"Asociado  No Registrado", 1000).show();
						} else {

							Toast.makeText(getApplicationContext(),
									"Asociado Registrado con Exito", 1000).show();
							nit.setText("");
							codFinca.getSelectedItem();
							nombres.setText("");
							direccion.setText("");
							telefono.setText("");
							tipoIdentificacion.getSelectedItem();

						}
					}
				}
			}

		});

		// ---------------------------------------------------------------
		// opcion del boton cancelar registro de asociado
		// ---------------------------------------------------------------

		cancelar = (Button) findViewById(R.id.btn_op_cultivo);
		cancelar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Registrar_Asociado.this, Utilidades.class);
				startActivity(i);

			}
		});

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		int seleccion = arg2;

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
