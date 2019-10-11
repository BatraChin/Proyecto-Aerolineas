import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import quick.dbtable.DBTable;

public class ConsultaSQL extends javax.swing.JInternalFrame {

	
	   protected Connection conexionBD = null;
	   private JPanel pnlConsulta;
	   private JTextArea txtConsulta;
	   private JButton botonBorrar;
	   private JButton btnEjecutar;
	   private DBTable tabla;    
	   private JScrollPane scrConsulta;
	   private JScrollPane scroll;
	   private JPanel pnlTablas;
	   private JList listaTablas;
	   private DBTable muestro; //tabla a mostrar
	   private TextArea muestro2;
	   private VuelosConexion dbVuelos;
	   private JFrame panelContent,frame;
	   private JPanel panelPrincipal;
	   private JButton btnVolverAlInicio;
	   private int controlExecute;
	   private int controlUpdate;
		
		
	   public ConsultaSQL(VuelosConexion dbV , JFrame frame) 
	   {
		  
	      super();
	      dbVuelos = dbV;
	      panelContent= new JFrame();
	      panelContent = frame;	      
	      initGUI();
	      
	   }
	   
	   private void initGUI() 
	   {
	      try {
	    	 controlUpdate = 0;
	    	 controlExecute = 0;
	    	 frame = new JFrame();
  			 frame.setBounds(100, 100, 1100, 576);
  			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  			 frame.setContentPane(this.getContentPane());
  			 frame.setVisible(true);
	         setPreferredSize(new Dimension(1100, 600));
	         this.setBounds(0, 0, 800, 600);
	         setVisible(true);
	         BorderLayout thisLayout = new BorderLayout();
	         getContentPane().setLayout(thisLayout);
	         this.setClosable(true);
	         this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	         this.setMaximizable(true);
	         {
	            pnlConsulta = new JPanel();
	            pnlConsulta.setSize(1100, 200);
	            panelPrincipal = new JPanel();
	            panelPrincipal.setLayout(new BorderLayout());
	            panelPrincipal.add(pnlConsulta, BorderLayout.NORTH);
	            {
	               scrConsulta = new JScrollPane();
	               pnlConsulta.add(scrConsulta);
	               {
	                  txtConsulta = new JTextArea();
	                  scrConsulta.setViewportView(txtConsulta);
	                  txtConsulta.setTabSize(3);
	                  txtConsulta.setColumns(80);
	                  txtConsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
	                  txtConsulta.setText("");
	                  txtConsulta.setFont(new java.awt.Font("Monospaced",0,12));
	                  txtConsulta.setRows(5);
	               }
	            }
	            
	            {
	               btnEjecutar = new JButton();
	               pnlConsulta.add(btnEjecutar);
	               btnEjecutar.setText("Ejecutar");
	               btnEjecutar.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent evt) {
	                     btnEjecutarActionPerformed(evt);
	                  }
	               });
	            }
	            
	            {
	            	botonBorrar = new JButton();
	            	pnlConsulta.add(botonBorrar);
	            	botonBorrar.setText("Borrar");            
	            	botonBorrar.addActionListener(new ActionListener() {
	            		public void actionPerformed(ActionEvent arg0) {
	            		  txtConsulta.setText("");            			
	            		}
	            	});
	            }
	            
	            {	
	                btnVolverAlInicio = new JButton("Volver al inicio");
		       		btnVolverAlInicio.setBounds(371, 506, 133, 23);
		       		pnlConsulta.add(btnVolverAlInicio);
		       		OyenteRegresar or= new OyenteRegresar(this.frame);
		       		btnVolverAlInicio.addActionListener(or);
		       		
	            }
  
	        	tabla = new DBTable();
	        	muestro = new DBTable();
	        	// Agrega la tabla al frame
	            panelPrincipal.add(tabla, BorderLayout.CENTER);           
	                      
	           // Setea la tabla para sólo lectura (no se puede editar su contenido)  
	           tabla.setEditable(false);       
	           { 
		           //Agrego un nuevo panel  y le seteo grid en SOUTH
		           pnlTablas = new JPanel();
	
		           {
		        	   //Creo las tablas inferiores y las vinculo
		        	   this.showTables();
		               muestro2 = new TextArea(8,65);
		               pnlTablas.add(muestro2);
		               pnlTablas.add(scroll);
				       pnlTablas.setVisible(true);
    
		           }
		           panelPrincipal.add(pnlTablas, BorderLayout.SOUTH);
		           
	           }

	          	 panelPrincipal.setVisible(true);
			     this.getContentPane().add(panelPrincipal);
			    
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      
	   }

	   private void btnEjecutarActionPerformed(ActionEvent evt) 
	   {
	       this.ejecutarQuery();
	   	   this.refrescarShowTables();

	   	   //lo hago para que se actualice el JList que muestra todaslas tablas
	   	   panelPrincipal.repaint();
	   	   panelPrincipal.validate();
	   }
	   
	   
	   // controlUpdate / cntrolExecute son 0 si se ejecuta correctamente la actualizacion/consulta
	   // pone controlUpdate en 1 si no se ejecuto correctamente la actualizacion
	   // si controlUpdate / cntrolExecute son 1 en simultaneo, significa que hubo un error
	   private void ejecutarUpdate() {
		   try{
			   
		        Statement stmt = dbVuelos.getConexionBD().createStatement();
		        String modificacion = this.txtConsulta.getText().trim();
		        stmt.executeUpdate(modificacion);
		        
		        JOptionPane.showMessageDialog(null, "Modificacion realizada correctamente.","Información"
						, JOptionPane.INFORMATION_MESSAGE);

		        stmt.close();
		        controlUpdate = 0;
		     }
		     catch (SQLException ex)
		     {
		    	 controlUpdate =1;
		    	 if(controlExecute == 0)

		    		 ejecutarQuery();
		    	 else
		    		 
			        	{
				        	JOptionPane.showMessageDialog(new JFrame(),
				        			   //ex.getMessage() + "\n", 
					        		"Error al ejecutar la consula SQL "  + "\n", 
				                                      "Error.",
				                                      JOptionPane.ERROR_MESSAGE);
				        	controlExecute = 0;
				        	controlUpdate = 0;
			        	}
		     }
		   
	}

    // controlUpdate / cntrolExecute son 0 si se ejecuta correctamente la actualizacion/consulta
    // pone controlExecute en 1 si no se ejecuto correctamente la actualizacion
    // si controlUpdate / cntrolExecute son 1 en simultaneo, significa que hubo un error
	private void ejecutarQuery()
	   {
		try
	      {  
	    	  dbVuelos.conectarTabla(tabla); 
	    	  // seteamos la consulta a partir de la cual se obtendrán los datos para llenar la tabla
	    	  tabla.setSelectSql(this.txtConsulta.getText().trim());

	    	  // obtenemos el modelo de la tabla a partir de la consulta para 
	    	  // modificar la forma en que se muestran de algunas columnas  
	    	  tabla.createColumnModelFromQuery();    	    
	    	  for (int i = 0; i < tabla.getColumnCount(); i++)
	    	  { // para que muestre correctamente los valores de tipo TIME (hora)  		   		  
	    		 if	 (tabla.getColumn(i).getType()==Types.TIME)  
	    		 {    		 
	    		  tabla.getColumn(i).setType(Types.CHAR);  
	  	       	 }
	    		 // cambiar el formato en que se muestran los valores de tipo DATE
	    		 if	 (tabla.getColumn(i).getType()==Types.DATE)
	    		 {
	    		    tabla.getColumn(i).setDateFormat("dd/MM/YYYY");
	    		 }
	          }  
	    	  // actualizamos el contenido de la tabla.   	     	  
	    	  tabla.refresh();
	    	  // No es necesario establecer  una conexión, crear una sentencia y recuperar el 
	    	  // resultado en un resultSet, esto lo hace automáticamente la tabla (DBTable) a 
	    	  // patir  de  la conexión y la consulta seteadas con connectDatabase() y setSelectSql() respectivamente.  
	    	  controlExecute = 0;
	       }
	      catch (SQLException ex)
	      {/*
	    	  JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                      ex.getMessage() + "\n", 
                      "Error al ejecutar la consulta.",
                      JOptionPane.ERROR_MESSAGE);*/
	    	  
	         
	    	 controlExecute = 1;
	    	 //todavia no se ejecuto el update
	    	 if (controlUpdate == 0)
	    		 ejecutarUpdate();
	    	 	
	    	 else	{
			        	// en caso de error, se muestra la causa en la consola
				         System.out.println("SQLException: " + ex.getMessage());
				         System.out.println("SQLState: " + ex.getSQLState());
				         System.out.println("VendorError: " + ex.getErrorCode());
//			         	JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
//			                                       ex.getMessage() + "\n", 
//			                                       "Error al ejecutar la consulta.",
//			                                       JOptionPane.ERROR_MESSAGE);
				         JOptionPane.showMessageDialog(new JFrame(),
						        	
                                 //ex.getMessage() + "\n", 
				        		"Error al ejecutar la consulta SQL. "  + "\n",
                                 "Error",
                                 JOptionPane.ERROR_MESSAGE);
			         	controlExecute = 0;
			         	controlUpdate = 0;
			         }
	        
	      }
	      
	   }
	
	   
	   protected void showTables()
	   {
		   try
		      {    

			   
	           String servidor = "localhost:3306";
	           String baseDatos = "vuelos";
	           String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos;
			   conexionBD = DriverManager.getConnection (uriConexion,"admin", "admin");
			   System.out.println(conexionBD);
	           if (conexionBD != null) {
	        	   System.out.println("Conexion exitosa!");
	        	  } else {
	        	      System.out.println("Conexion fallida!");
	        	  }
				Statement s = conexionBD.createStatement(); 
	        	ResultSet rs = s.executeQuery ("SHOW TABLES;");

	        	int rowcount = 0;
	        	//Obtengo la cantidad de filas de tablas
	        	if (rs.last()) {
	  	        	  rowcount = rs.getRow();
	        			rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
	        	}
	  	    
//	        	while (rs.next()) 
//	        	{ 
//	        		System.out.println (rs.getString(1));
//
//	        	     
//	        	}
	        	
	        	//cargo el JPanel con el resulset
	        	String[] datos = new String [rowcount+1];
	            
	            int i = 0;
	        	while (rs.next()) 
	        	{ 
	        		datos[i] = rs.getString(1);
	        	//	System.out.println(datos[i]);
	        		i++;
	        	}
	        	 listaTablas = new JList<String>(datos);
	        	 listaTablas.addMouseListener(new MouseAdapter() {
	                   public void mouseClicked(MouseEvent evt) {
	                      tablaMouseClicked(evt);
	                   }
	            	});
	        	 
	        	 scroll=new JScrollPane(listaTablas);	         
		       }
		      catch (SQLException ex)
		      {
		         // en caso de error, se muestra la causa en la consola
		         System.out.println("SQLException: " + ex.getMessage());
		         System.out.println("SQLState: " + ex.getSQLState());
		         System.out.println("VendorError: " + ex.getErrorCode());
		         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
		                                       ex.getMessage() + "\n", 
		                                       "Error al ejecutar la consulta.",
		                                       JOptionPane.ERROR_MESSAGE);
		      }
	   }
	   
	   //Oyente de la tabla de que muestra showTables
	   private void tablaMouseClicked(MouseEvent evt) 
	   {
		   if ((this.listaTablas.getSelectedIndex() != -1) && (evt.getClickCount() == 1))
	      {	
	    	this.refrescarAtributos();
	      }
	   }
	   
	   private void refrescarAtributos()
	   {
	      try
	      {
	    	  muestro2.setText("");
	         Statement stmt = this.conexionBD.createStatement();	        
	         String sql = " SHOW COLUMNS FROM " + listaTablas.getSelectedValue()+ " FROM vuelos;";

	         ResultSet rs = stmt.executeQuery(sql);
	         while (rs.next()) 
	        	{ 
	        		muestro2.setText(muestro2.getText()+" " +rs.getString(1)+ " |" );
	        	     
	        	}
	         rs.close();
	         stmt.close();
	      }
	      catch (SQLException ex)
	      {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState: " + ex.getSQLState());
	         System.out.println("VendorError: " + ex.getErrorCode());
	      }
	   }
	   
	   private class OyenteRegresar implements ActionListener
	   {
		 JFrame f;
		 public OyenteRegresar(JFrame frame){
			 f=frame;
		 }
		@Override
		public void actionPerformed(ActionEvent e) {
			
			panelContent.setVisible(true);
			dbVuelos.desconectarBD();
			f.dispose();
			
		}
		   
	   }
	   
	   private void refrescarShowTables()
	   {
		   	   pnlTablas.remove(scroll);
		   	   scroll = null;
		   	   muestro2.setVisible(false);
	           String servidor = "localhost:3306";
	           String baseDatos = "vuelos";
	           String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos;
			  try{ 
	           conexionBD = DriverManager.getConnection (uriConexion,"admin", "admin");
	//		   conexionBD = dbVuelos.getConexionBD();
			   System.out.println(conexionBD);
	           if (conexionBD != null) {
	        	   System.out.println("Conexion exitosa!");
	        	  } else {
	        	      System.out.println("Conexion fallida!");
	        	  }
				Statement s = conexionBD.createStatement(); 
	        	ResultSet rs = s.executeQuery ("SHOW TABLES;");
				   int rowcount = 0;
		       	//Obtengo la cantidad de filas de tablas
		       	if (rs.last()) {
		 	        	  rowcount = rs.getRow();
		       			rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
		       	}		       	
		       	//cargo el JPanel con el resulset
		       	String[] datos = new String [rowcount+1];
		           
		           int i = 0;
		       	while (rs.next()) 
		       	{ 
		       		datos[i] = rs.getString(1);
		       		i++;
		       	}
		       	 listaTablas = new JList<String>(datos);
		       	 listaTablas.addMouseListener(new MouseAdapter() {
		                  public void mouseClicked(MouseEvent evt) {
		                     tablaMouseClicked(evt);
		                  }
		           	});
		       	 scroll=new JScrollPane(listaTablas);
		       	pnlTablas.add(scroll);
			    pnlTablas.setVisible(true);
			    muestro2.setVisible(true);
			  }
	   	
	      catch (SQLException ex)
	      {
	         // en caso de error, se muestra la causa en la consola
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState: " + ex.getSQLState());
	         System.out.println("VendorError: " + ex.getErrorCode());
	         JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
	                                       ex.getMessage() + "\n", 
	                                       "Error al ejecutar la consulta.",
	                                       JOptionPane.ERROR_MESSAGE);
	      }
	   }
	
}
