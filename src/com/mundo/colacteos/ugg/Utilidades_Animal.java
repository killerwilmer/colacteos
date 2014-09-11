package com.mundo.colacteos.ugg;



import com.bd.colacteos.SQLiteHelper;
import com.bd.colacteos.UGG_informacionDao;
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

public class Utilidades_Animal  extends Activity{

	// -----------------------------------------------------------
	// atributos de la clase Utilidades Asociado
	// -----------------------------------------------------------
			private Button adicionar_animal;
		
			ListView lv;
		
			
			TextView cod, nombre, fecha_nacimiento, peso_UGG, raza, fecha_venta,
			fecha_muerte,motivo_muerte,genero, codigo_finca,cantidad_abortos,
			servicio_toro,  tipo_animal, numero_partos;
			
			
	// -----------------------------------------------------------------------
	// metodo que permite crear la instancia de la clase utilidades de animal
	// -----------------------------------------------------------------------
			
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.utilidades_animales);
				
				
				

				//---------------------------------------------------------------------
				//lista de animales
				//---------------------------------------------------------------------
				UGG_informacionDao	dbcon= new UGG_informacionDao(this);
				dbcon.abrir();
				lv=(ListView)findViewById(R.id.listaUGG);
				Cursor cursor=dbcon.listaAnimales();
				SQLiteHelper SqlHelper;
				SqlHelper=new SQLiteHelper(this);
				String[] from = new String[] {
						SqlHelper.codigo_UGG, 
						SqlHelper.nombre_UGG,
						SqlHelper.fecha_nacimiento,
						SqlHelper.peso_UGG,
						SqlHelper.raza_UGG,
						SqlHelper.fecha_venta,
						SqlHelper.fecha_muerte, 
						SqlHelper.motivo_muerte, 
						SqlHelper.genero,
						SqlHelper.codi_finca,
						SqlHelper.cantidad_abortos,
						SqlHelper.servicio_toro,
						SqlHelper.tipo_animal,
						SqlHelper.numero_partos	}; 
				int[] to = new int[] { 
					R.id.txt_codigoAnimal,
					R.id.txt_nombreAnimal,
					R.id.txt_fechaNacimiento,
					R.id.txt_pesoAnimal,
					R.id.txt_razaAnimal,
					R.id.txt_fechaVenta,
					R.id.txt_fechaMuerte,
					R.id.txt_motivoMuerte,
					R.id.txt_generoAnimal,
					R.id.txt_codigoFincaAnimal,
					R.id.txt_cantidadAbortos,
					R.id.txt_servicioToro,
					R.id.txt_tipoAnimal,
					R.id.txt_numPartoss,
					
						};
				SimpleCursorAdapter adapter= new SimpleCursorAdapter(Utilidades_Animal.this, R.layout.adaptador_ugg, cursor, from, to, 1);	
				adapter.notifyDataSetChanged();
				lv.setAdapter(adapter);
				
				
				
				//----
				
				lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						cod= (TextView) view.findViewById(R.id.txt_codigoAnimal);
						nombre = (TextView) view.findViewById(R.id.txt_nombreAnimal);
						fecha_nacimiento = (TextView) view.findViewById(R.id.txt_fechaNacimiento);
						peso_UGG = (TextView) view.findViewById(R.id.txt_pesoAnimal);
						raza= (TextView) view.findViewById(R.id.txt_razaAnimal);

						
						fecha_venta= (TextView) view.findViewById(R.id.txt_fechaVenta);
						fecha_muerte = (TextView) view.findViewById(R.id.txt_fechaMuerte);
						motivo_muerte = (TextView) view.findViewById(R.id.txt_motivoMuerte);
						genero = (TextView) view.findViewById(R.id.txt_generoAnimal);
						codigo_finca= (TextView) view.findViewById(R.id.txt_codigoFincaAnimal);

						cantidad_abortos= (TextView) view.findViewById(R.id.txt_cantidadAbortos);
						servicio_toro= (TextView) view.findViewById(R.id.txt_servicioToro);
						tipo_animal = (TextView) view.findViewById(R.id.txt_tipoAnimal);
						numero_partos = (TextView) view.findViewById(R.id.txt_numPartoss);
				
						
						
						
						String id_val = cod.getText().toString();
						String nombre_val = nombre.getText().toString();
						String fechaNa_val = fecha_nacimiento.getText().toString();
						String pesao_val = peso_UGG.getText().toString();
						String raza_val = raza.getText().toString();
						
						
						String fechVen_val = fecha_venta.getText().toString();
						String fechaMue_val = fecha_muerte.getText().toString();
						String motivMuer_val = motivo_muerte.getText().toString();
						String gen_val = genero.getText().toString();
						String finca_val = codigo_finca.getText().toString();
						
						String abortos_val = cantidad_abortos.getText().toString();
						String servicios_val = servicio_toro.getText().toString();
						String tipoAnimal_val = tipo_animal.getText().toString();
						String pasrtos_val = numero_partos.getText().toString();
						
						

						Intent modify_intent = new Intent(getApplicationContext(),
								Modificar_Animal.class);
						modify_intent.putExtra("codigo", id_val);
						modify_intent.putExtra("nombre", nombre_val);
						modify_intent.putExtra("fechaNa", fechaNa_val);
						modify_intent.putExtra("peso", pesao_val);
						modify_intent.putExtra("raza", raza_val);
						
						modify_intent.putExtra("fechaVen", fechVen_val);
						modify_intent.putExtra("fechaMuerte", fechaMue_val);
						modify_intent.putExtra("MotiMuerte", motivMuer_val);
						modify_intent.putExtra("genero", gen_val);
						modify_intent.putExtra("finca", finca_val);
						
						modify_intent.putExtra("abortos", abortos_val);
						modify_intent.putExtra("servicios", servicios_val);
						modify_intent.putExtra("tipoAnimal", tipoAnimal_val);
						modify_intent.putExtra("partos", pasrtos_val);
						
						startActivity(modify_intent);
					}
				});
			
				
				

	// ------------------------------------------------------------------
	// accion del boton adicionar animal
	// ------------------------------------------------------------------
			
				adicionar_animal=(Button)findViewById(R.id.btn_regisAnimal);
				adicionar_animal.setOnClickListener(new View.OnClickListener() {
					
					@Override
			public void onClick(View v) {
						
						Intent ini= new Intent(Utilidades_Animal.this,Registrar_Ugg.class);
						startActivity(ini);
						
							
						
						}
						

			
		});

		
				
				
				
	}
			
			
			

}