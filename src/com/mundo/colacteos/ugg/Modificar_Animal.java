package com.mundo.colacteos.ugg;

import com.bd.colacteos.UGG_informacionDao;
import com.mundo.colacteos.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar_Animal extends Activity implements OnClickListener{
	
	
	
	
	//-----------------------------------------------------------
	// atributos de la clase
	//-----------------------------------------------------------
	EditText cod, nombre, fecha_nacimiento, peso_UGG, raza, fecha_venta,
	fecha_muerte,motivo_muerte,genero, codigo_finca,cantidad_abortos,
	servicio_toro,  tipo_animal, numero_partos;
	Button editar, eliminar;
	long id;
	
	//------------------------------------------------------------
	// clase Dao
	//-----------------------------------------------------------
	UGG_informacionDao dbcon;
	
	
	//------------------------------------------------------------
	// instancia a la activity modificar animal
	//-----------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modificar_animal);
		
		
		dbcon = new UGG_informacionDao(this);
		dbcon.abrir();
		cod= (EditText) findViewById(R.id.edt_codiAni);
		nombre = (EditText) findViewById(R.id.edt_nombreAnimal);
		fecha_nacimiento = (EditText) findViewById(R.id.edt_fechaNa);
		peso_UGG = (EditText) findViewById(R.id.edt_pesoAnima);
		raza= (EditText) findViewById(R.id.edt_raza);

		
		fecha_venta= (EditText) findViewById(R.id.edt_fechaVenta);
		fecha_muerte = (EditText) findViewById(R.id.edt_fechaMuer);
		motivo_muerte = (EditText) findViewById(R.id.edt_motivo_Muer);
		genero = (EditText) findViewById(R.id.edt_generoAnima);
		codigo_finca= (EditText) findViewById(R.id.edt_fincaA);

		cantidad_abortos= (EditText) findViewById(R.id.edt_numAbortos);
		servicio_toro= (EditText) findViewById(R.id.edt_serviciosT);
		tipo_animal = (EditText) findViewById(R.id.edt_tipoAnimal);
		numero_partos =(EditText) findViewById(R.id.edt_numPartos);

		
		editar= (Button) findViewById(R.id.btn_actuAnim);
		eliminar = (Button) findViewById(R.id.btn_elimAnim);
		///---------------------------------
				
		Intent i = getIntent();
		String memberCod = i.getStringExtra("codigo");
		String memberNom = i.getStringExtra("nombre");
		String memberFeNa = i.getStringExtra("fechaNa");
		String memberPeso = i.getStringExtra("peso");
		String memberRaza = i.getStringExtra("raza");
		
		String memberFeVe = i.getStringExtra("fechaVen");
		String memberFeMu = i.getStringExtra("fechaMuerte");
		String memberMoMu = i.getStringExtra("MotiMuerte");
		String memberGenero = i.getStringExtra("genero");
		String memberFinca = i.getStringExtra("finca");
		
		String memberAbort = i.getStringExtra("abortos");
		String memberServi = i.getStringExtra("servicios");
		String memberTipAn = i.getStringExtra("tipoAnimal");
		String memberPartos = i.getStringExtra("partos");
		
		
		id = Long.parseLong(memberCod);
		cod.setText(memberCod);
		nombre.setText(memberNom);
		fecha_nacimiento.setText(memberFeNa);
		peso_UGG.setText(memberPeso);
		raza.setText(memberRaza);
		
		fecha_venta.setText(memberFeVe);
		fecha_muerte.setText(memberFeMu);
		motivo_muerte.setText(memberMoMu);
		genero.setText(memberGenero);
		codigo_finca.setText(memberFinca);	
		
		cantidad_abortos.setText(memberAbort);
		servicio_toro.setText(memberServi);
		tipo_animal.setText(memberTipAn);
		numero_partos.setText(memberPartos);
		
		
		
		editar.setOnClickListener(this);
		eliminar.setOnClickListener(this);
		
		
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_actuAnim:
			String codigo=cod.getText().toString();
			String nom = nombre.getText().toString();
			String fechaN=fecha_nacimiento.getText().toString();
			String peso = peso_UGG.getText().toString();
			String razas = raza.getText().toString();
			
			String fechaVe = fecha_venta.getText().toString();
			String FechaMu=fecha_muerte.getText().toString();
			String MotivoMuerte =motivo_muerte.getText().toString();
			String gene = genero.getText().toString();
			
			String codiFin = codigo_finca.getText().toString();
			String aborto=cantidad_abortos.getText().toString();
			String sertToro =servicio_toro.getText().toString();
			String tipoA =tipo_animal.getText().toString();
			String partos =numero_partos.getText().toString();
			
			
			dbcon.actualizarAnimal(codigo, nom, fechaN, peso, razas, fechaVe, FechaMu, MotivoMuerte, gene, codiFin, aborto, sertToro, tipoA, partos);
			Toast.makeText(getApplicationContext(), "Se han actualizado los datos", 1000).show();

			this.returnHome();
			break;

		case R.id.btn_elimAnim:
			// Objetos. 
			AlertDialog.Builder mensaje_dialogo = new AlertDialog.Builder(this);  	

			// Variables.
			final Long m = id;

			mensaje_dialogo.setTitle("Importante");  
			mensaje_dialogo.setMessage("Esta seguro de eliminar este animal?");            
			mensaje_dialogo.setCancelable(false);  
			mensaje_dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {  
				public void onClick(DialogInterface dialogo1, int id) {  
					try{  


						dbcon.eliminarAnimal(m);
						Toast.makeText(getApplicationContext(), "Registro Eliminado", 1000).show();

						returnHome();


					}catch(Exception e){
						Toast.makeText(getApplicationContext(), "Error al eliminar!!!", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}finally{
						dbcon.cerrar();

					}
				}  
			});  
			mensaje_dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
				public void onClick(DialogInterface dialogo1, int id) {  
					returnHome();
				}  
			});            
			mensaje_dialogo.show();  }



	}
		
		
		public void returnHome() {

			Intent home_intent = new Intent(getApplicationContext(),
					Utilidades_Animal.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			startActivity(home_intent);
		}

	
	
	
	
	
	
	

}
