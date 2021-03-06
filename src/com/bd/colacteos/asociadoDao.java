package com.bd.colacteos;
import com.Datos.colacteos.Asociado;
import com.Datos.colacteos.SAT_Profesional;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class asociadoDao {

	
	private  SQLiteDatabase bd;
	private  SQLiteHelper dbHelper;
	Context con;
	
	
	
	//-------------------------------------------------
	//constructor de la clase
	//-------------------------------------------------
	

	public asociadoDao(Context contexto){
		dbHelper= new SQLiteHelper(contexto);
	}
	

	//---------------------------------------------------
	//abre la conexion  a la base de datos
	//----------------------------------------------------
		
		public void abrir()
		  {
			bd= dbHelper.getWritableDatabase();
		  }
		/*public asociadoDao abrir() throws SQLException {
			 SQLiteHelper dbhelper = new SQLiteHelper(con);
			bd = dbhelper.getWritableDatabase();
			return this;

		}*/
		
		//--------------------------------------------------
		  //cerrar la conexion de la base de datos
		  //-------------------------------------------------
		
		public void cerrar(){
			dbHelper.close();
		  }
		
		//-----------------------------------------
		//metodo para agregar un asociado
		//---------------------------------------------------------
		
		public int crearAsociado(String nit_asociado,String cod_finca, String nombre_Completo, String Direccion , String telefono, String tipo_identificacion)
		{	
			int estado = 0;
			Asociado asociado = null;
			asociado = buscarAsociado(nit_asociado);
			if (asociado != null) {
				estado = -500;
			} else {
				abrir();
				dbHelper.getWritableDatabase();

				ContentValues valores =new  ContentValues();
				valores.put("nit_asociado", nit_asociado);
				valores.put("cod_finca",cod_finca);
				valores.put("nombre_Completo",nombre_Completo);
				valores.put("Direccion", Direccion);
				valores.put("telefono", telefono);
				valores.put("tipo_identificacion", tipo_identificacion);
				


				bd.insert(SQLiteHelper.SAT_terceros_asociados, null, valores);

				//bd.close();
			}
			return estado;
	}
		//--------------------------------------------------------------------------
		// metodo que permite  leer datos de asociado
		//--------------------------------------------------------------------------
		
		public Cursor leerAsociados() {
			String[] allColumns = new String[] {"rowid AS _id", dbHelper.nit_asociado, dbHelper.nombre_Completo, dbHelper.cod_finca,  dbHelper.tipo_identificacion,  dbHelper.Direccion, dbHelper.telefono };
			Cursor c = bd.query(SQLiteHelper.SAT_terceros_asociados, allColumns, null, null, null,null, null);
			if (c != null) {
				c.moveToFirst();
			}
			return c;
		}
		

		//------------------------------------------------------------------------
		//metodo para eliminar un asociado
		//------------------------------------------------------------------------
		public void eliminarAsociado(long nit_asociado){
			 bd.delete(SQLiteHelper.SAT_terceros_asociados,dbHelper.nit_asociado+ "="+ nit_asociado, null);
		 }
		
		 
		//----------------------------------------------------
		 //metodo  que sirve para buscar un asociado
		 //---------------------------------------------------
		 
		 
		 public Cursor buscarUsuario(String nit){
			 String[] columnas= new  String[]{dbHelper.nit_asociado,dbHelper.cod_finca, dbHelper.nombre_Completo, dbHelper.Direccion, dbHelper.tipo_identificacion};
			 return bd.query(SQLiteHelper.SAT_terceros_asociados, columnas, dbHelper.nit_asociado+"?",new String[]{nit},  null,  null,  null,  null);
		 }
		 
		 public Asociado buscarAsociado(String Nit) {
				abrir();
				dbHelper.getReadableDatabase();
				String[] valores = { dbHelper.nit_asociado,dbHelper.cod_finca, dbHelper.nombre_Completo, dbHelper.Direccion, dbHelper.telefono, dbHelper.tipo_identificacion };
				Cursor c = bd.query(SQLiteHelper.SAT_terceros_asociados, valores,
						"nit_asociado=" + Nit, null, null, null, null, null);
				Asociado asociado = null;
				if (c.moveToFirst()) {
					asociado = new Asociado(c.getString(0),
							c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
				}

				bd.close();
				c.close();
				return asociado;
			}
		 
		//----------------------------------------------------
		 //metodo  que sirve para actualizar un asociado
		 //---------------------------------------------------
		 
		 public int actualizarAsociado(long nit_asociado,String cod_finca, String nombre_Completo, String Direccion,String tipo_identificacion, String telefono ) {
				
					ContentValues valores = new ContentValues();
					valores.put("nit_asociado", nit_asociado);
					valores.put("cod_finca",cod_finca);
					valores.put("nombre_Completo",nombre_Completo);
					valores.put("Direccion", Direccion);
					valores.put("tipo_identificacion", tipo_identificacion);
					valores.put("telefono", telefono);
					
					int i= bd.update(SQLiteHelper.SAT_terceros_asociados, valores, dbHelper.nit_asociado+"="+ nit_asociado, null);
									
				
				return i;
			}
		 
			//-------------------------------------------
			// devuelve codigo de asociado
			//------------------------------------

			public Cursor darCodigoAsociado() throws SQLException
			{
				String[] columns = new String[] { "rowid AS _id", dbHelper.nit_asociado};
				Cursor c = bd.query( true, SQLiteHelper.SAT_terceros_asociados, columns, null, null, null, null, null, null);
				return c;
			}
		 
		
		 
		 
}

