package com.mundo.colacteos.asociado;

import com.bd.colacteos.UGG_informacionDao;
import com.bd.colacteos.asociadoDao;
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


public class Modificar_Asociado extends Activity implements OnClickListener {
	
	//-------------------------------------------------
		// atributos de la clase
		//---------------------------------------------------
		EditText nombre, nit, finca,  direccion, telefono , tipoId;
		Button editar, eliminar, cultivo;
		long id;
		asociadoDao dbcon;
		
		
		//----------------------ivo-----------------------------------
		//instacia a la clase modificar
		//--------------------------------------------------------
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.modificar_asociado);
			
			dbcon = new asociadoDao(this);
			dbcon.abrir();
			
			nombre=(EditText)findViewById(R.id.edt_nomAso);
			nit=(EditText)findViewById(R.id.edt_nitAsoc);
			finca=(EditText)findViewById(R.id.edt_fincaAso);
			tipoId=(EditText)findViewById(R.id.edt_tipoIden);
			direccion=(EditText)findViewById(R.id.edt_direccionAso);
			telefono=(EditText)findViewById(R.id.edt_telefonoAso);
			editar= (Button) findViewById(R.id.btn_actuAsi);
			eliminar = (Button) findViewById(R.id.btn_elimAso);
			
			
			///---------------------------------
		
			
			
			Intent i = getIntent();
			String memberNom = i.getStringExtra("nombre");
			String memberCodi = i.getStringExtra("nit");
			String memberFinca = i.getStringExtra("finca");
			String memberTipo = i.getStringExtra("tipo");
			String memberDire= i.getStringExtra("direccion");
			String memberTel= i.getStringExtra("telefono");
			
			
			//-------------------------------
			
			id = Long.parseLong(memberCodi);
			nit.setText(memberCodi);
			nombre.setText(memberNom);
			finca.setText(memberFinca);
			tipoId.setText(memberTipo);
			direccion.setText(memberDire);			
			telefono.setText(memberTel);
			
			editar.setOnClickListener(this);
			eliminar.setOnClickListener(this);
			
			
		}


		
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_actuAsi:
				String nits=nit.getText().toString();
				String nom = nombre.getText().toString();
				String codFinca=finca.getText().toString();
				String tipoIdentificacion = tipoId.getText().toString();
				String dire = direccion.getText().toString();				
				String tele = telefono.getText().toString();
				
				
				
				dbcon.actualizarAsociado(id, codFinca, nom, dire, tipoIdentificacion, tele);
				Toast.makeText(getApplicationContext(), "Se han actualizado los datos", 1000).show();

				this.returnHome();
				break;

			case R.id.btn_elimAso:
				 
				AlertDialog.Builder mensaje_dialogo = new AlertDialog.Builder(this);  	

				// Variables.
				final Long m = id;

				mensaje_dialogo.setTitle("Importante");  
				mensaje_dialogo.setMessage("Esta seguro de eliminar este animal?");            
				mensaje_dialogo.setCancelable(false);  
				mensaje_dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialogo1, int id) {  
						try{  


							dbcon.eliminarAsociado(m);
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
						Utilidades_Asociado.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(home_intent);
			}

		
		

	


}
