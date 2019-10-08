import java.awt.ScrollPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;




public class ModificarTabla {

	
	protected void refrescarTabla(String consulta, JTable tabla, VuelosConexion v)
	   {
	      try
	      {
	         Statement stmt =v.getConexionBD().createStatement();
	         ResultSet rs = stmt.executeQuery(consulta);
	         ResultSetMetaData rsmd = rs.getMetaData();
	         ConsultasTableModel modelo = new ConsultasTableModel();
	         for (int i = 1; i <= rsmd.getColumnCount(); i++)
	         

	         {
	            String columnaNombre = rsmd.getColumnLabel(i);
	            boolean columnaEditable = false;
	            modelo.agregarColumna(columnaNombre,columnaEditable);
	         }
	         tabla.setModel(modelo);
	         while (rs.next())
	         {
	            Vector<Object> fila = new Vector<Object>();  
	            for (int j = 1; j <= rsmd.getColumnCount(); j++)
	            {
	            	fila.add(rs.getObject(j));
	            }
	            
	            ((ConsultasTableModel) tabla.getModel()).addRow(fila);
	         }

	         rs.close();
	         stmt.close();
	      }
	      catch (SQLException ex)
	      {
	         
	         JOptionPane.showMessageDialog(new JFrame(),
	                                       ex.getMessage() + "\n", 
	                                       "Error al ejecutar la consulta.",
	                                       JOptionPane.ERROR_MESSAGE);
	         
	      }
	   }

	}

	   class ConsultasTableModel extends DefaultTableModel
	   {
		   Vector<Boolean> sonEditables = new Vector<Boolean>();
	 
	      public ConsultasTableModel()
	      {
	         super();
	         
	         this.sonEditables = new Vector<Boolean>();
	      }

	      public void agregarColumna(String nombre, boolean esEditable)
	      {
	         this.addColumn(nombre);
	         this.sonEditables.add(esEditable);
	      }

	     

	      public boolean isCellEditable(int rowIndex, int columnIndex)
	      {
	         return this.sonEditables.get(columnIndex);
	      }
	   }
	   
	   

