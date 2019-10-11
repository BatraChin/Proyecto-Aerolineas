
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import quick.dbtable.DBTable;

public class VuelosConexion {
	

	protected Connection conexionBD = null;
	private String servidor;
    private  String baseDatos;
    private String uriConexion;
    private String driver;

public VuelosConexion(){
	
	String driver ="com.mysql.jdbc.Driver";
	servidor = "localhost:3306";
    baseDatos = "vuelos";
    uriConexion = /*"jdbc:mysql://" + servidor + "/" + baseDatos +"?noAccessToProcedureBodies=true";*/"jdbc:mysql://" + servidor + "/" + 
            baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
} 


public boolean conectarBD(String usuario,String clave)
	   {
	      if (this.conexionBD == null)
	      {
	        /* try
	         {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	         }
	         catch (Exception ex)
	         {
	            System.out.println(ex.getMessage());
	         }*/
	   
	         try
	         {
	            this.conexionBD =  DriverManager.getConnection(uriConexion, usuario, clave);	
		        return (conexionBD.isValid(20)); 
	            
	         }
	         catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} 
	         }
	      return false;
	   }

public boolean conectarBDempleado(int legajo,String clave)
{
   if (this.conexionBD == null)
   {/*
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      }
      catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

      try
      {

        String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
               
        this.conexionBD =  DriverManager.getConnection(uriConexion, "admin", "admin");
        
        java.sql.Statement stmt = conexionBD.createStatement();
		java.sql.ResultSet rs = stmt.executeQuery("select distinct legajo,password from empleados where legajo="+legajo+" and password="+"'"+clave+"';");
        
        
        if (rs.next()){ 
        	 
        	desconectarBD();
        	 this.conexionBD = (Connection) DriverManager.getConnection(uriConexion, "empleado", "empleado");
        	 	
        	 return true;}
        else{
           desconectarBD();
           return false;}
      }
      catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      }
   return false;
}


public void desconectarBD()
{
   if (this.conexionBD != null)
   {
      try
      {
         this.conexionBD.close();
         this.conexionBD = null;
      }
      catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
}

public Connection getConexionBD()
{
	return conexionBD;
}

/*private java.sql.ResultSet consulta (String sql){
	try
	{
		
		java.sql.Statement stmt = conexionBD.createStatement();
		java.sql.ResultSet rs = stmt.executeQuery(sql);
		return rs;

	}
	catch (java.sql.SQLException ex) {}
	return null;
}*/

public void conectarTabla(DBTable tabla) {
	  try {
			  tabla.connectDatabase(driver, uriConexion, "admin","admin");
	} 
	  catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
