package com.mundo.colacteos.asociado;


import com.bd.colacteos.SQLiteHelper;
import com.bd.colacteos.asociadoDao;
import com.mundo.colacteos.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Utilidades_Asociado extends Activity{
	
	// -----------------------------------------------------------
	// atributos de la clase Utilidades Asociado
	// -----------------------------------------------------------
	private Button adicionar_aso;

	TextView  nombre, nit, codigo_finca, tipoIdentificaion,  direccion, telefono;
	
	
	
	

	// -----------------------------------------------------------------------
	// metodo que permite crear la instancia de la clase utilidades asociado
	// -----------------------------------------------------------------------
	

	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.utilidades_asociado);
	
	
	
	//-------------------------------------------------------------
	// lista asociados
	//-------------------------------------------------------------
			
	//-------------------------------------------------------------
		// lista asociados
		//-------------------------------------------------------------
				
	asociadoDao	obj= new asociadoDao(this);
	obj.abrir();
	ListView lv=(ListView)findViewById(R.id.lista_asociados);
	Cursor c=obj.leerAsociados();
	SQLiteHelper SqlHelper1=new SQLiteHelper(this);
	String[] f = new String[] { SqlHelper1.nit_asociado, SqlHelper1.nombre_Completo,  SqlHelper1.cod_finca,  SqlHelper1.tipo_identificacion,  SqlHelper1.Direccion, SqlHelper1.telefono}; 
	int[] t = new int[] {R.id.txtNitaso, R.id.txtNomAs,  R.id.textCodF,  R.id.txt_TipoIdenti,  R.id.txtDireccion,  R.id.txt_telefono};
	SimpleCursorAdapter adapter= new SimpleCursorAdapter(Utilidades_Asociado.this, R.layout.adaptador_asociado, c, f, t,1);	
	adapter.notifyDataSetChanged();
	lv.setAdapter(adapter);
	
	lv.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			nit= (TextView) view.findViewById(R.id.txtNitaso);
			nombre = (TextView) view.findViewById(R.id.txtNomAs);
			codigo_finca = (TextView) view.findViewById(R.id.textCodF);
			tipoIdentificaion= (TextView) view.findViewById(R.id.txt_TipoIdenti);
			direccion= (TextView) view.findViewById(R.id.txtDireccion);
			telefono= (TextView) view.findViewById(R.id.txt_telefono);
			
			
			
			
			String id_val = nit.getText().toString();
			String nombre_val = nombre.getText().toString();
			String finca_val = codigo_finca.getText().toString();
			String TipoI_val = tipoIdentificaion.getText().toString();
			String direccion_val = direccion.getText().toString();
			String telefono_val = telefono.getText().toString();
			
			

			Intent modify_intent = new Intent(getApplicationContext(),
					Modificar_Asociado.class);
			modify_intent.putExtra("nombre",nombre_val );
			modify_intent.putExtra("nit", id_val);
			modify_intent.putExtra("finca", finca_val);
			modify_intent.putExtra("tipo", TipoI_val);
			modify_intent.putExtra("direccion", direccion_val);			
			modify_intent.putExtra("telefono", telefono_val);
			
			
			startActivity(modify_intent);
		}
	});



	// ------------------------------------------------------------------
	// action del boton adicionar asociado
	// ------------------------------------------------------------------
	
	adicionar_aso= (Button)findViewById(R.id.btn_op_cultivo);
	adicionar_aso.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent in= new Intent(Utilidades_Asociado.this,Registrar_Asociado.class);
			startActivity(in);
			
		}
	});
	
	

	
	
	
		
	
	}

}