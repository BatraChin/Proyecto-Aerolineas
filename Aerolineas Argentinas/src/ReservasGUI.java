
import javax.swing.JFrame;
import java.awt.Panel;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ReservasGUI {

	private JFrame frame;
	private JTextField origenTextField;
	private JTextField destinoTextField;
	private JTable tableVuelosIda;
	private JTable tableClasesIda;
	private JTable tableVuelosVuelta;
	private JTable tableClasesVuelta;
	private JTable tableUsuarios;
	
	private int legajo;
	private VuelosConexion v;
	protected static ModificarTabla m=new ModificarTabla();

	private Panel panelLugarFecha;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JRadioButton idaRadioButton;
	private JRadioButton idaVueltaRadioButton;
	private JFormattedTextField fechaIda;
	private JFormattedTextField fechaVuelta;
	private JPanel panelIda;
	private JPanel panelVueloIda;
	private JPanel panelClaseVueloIda;
	private JPanel panelVuelta;
	private JPanel panelVueloVuelta;
	private JPanel panelClaseVueloVuelta;
	private JPanel panelUsuarios;
	private JPanel panelUsuariosDisponibles;
	private JButton btnReservar;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPaneVueloIda;
	private JScrollPane scrollPaneVueloVuelta;
	private JScrollPane scrollPaneClaseVueloIda;
	private JScrollPane scrollPaneClaseVueloVuelta;
	private JScrollPane scrollPaneUsuarios;
	private JButton btnVolverAlInicio;
	private JFrame prev;

	/**
	 * Create the application.
	 */
	public ReservasGUI(VuelosConexion vuelos, JFrame contentPane,int l) {
		super();
		legajo=l;
		v=vuelos;
		prev = contentPane;
		prev.setVisible(false);
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 877, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		panelLugarFecha = new Panel();
		panelLugarFecha.setBounds(10, 10, 839, 46);
		frame.getContentPane().add(panelLugarFecha);
		panelLugarFecha.setLayout(null);
		panelLugarFecha.setVisible(true);
		
		lblNewLabel = new JLabel("Origen");
		lblNewLabel.setBounds(10, 17, 46, 14);
		panelLugarFecha.add(lblNewLabel);
		
		origenTextField = new JTextField();
		origenTextField.setBounds(54, 14, 86, 20);
		panelLugarFecha.add(origenTextField);
		origenTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Destino");
		lblNewLabel_1.setBounds(150, 17, 46, 14);
		panelLugarFecha.add(lblNewLabel_1);
		
		destinoTextField = new JTextField();
		destinoTextField.setBounds(196, 14, 86, 20);
		panelLugarFecha.add(destinoTextField);
		destinoTextField.setColumns(10);
		
		idaRadioButton = new JRadioButton("Ida");
		idaRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idaRadioButton.setSelected(true);
				idaVueltaRadioButton.setSelected(false);
				btnReservar.setEnabled(false);
				tableVuelosIda.setVisible(true);
				tableClasesIda.setVisible(true);
				tableVuelosVuelta.setVisible(false);
				tableClasesVuelta.setVisible(false);
				fechaIda.setVisible(true);
				lblNewLabel_3.setVisible(false);
				fechaVuelta.setVisible(false);
			}
		});
		idaRadioButton.setBounds(288, 14, 46, 23);
		panelLugarFecha.add(idaRadioButton);		
		idaRadioButton.setSelected(true);
		
		idaVueltaRadioButton = new JRadioButton("Ida y vuelta");
		idaVueltaRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idaVueltaRadioButton.setSelected(true);
				idaRadioButton.setSelected(false);
				btnReservar.setEnabled(false);
				tableVuelosIda.setVisible(true);
				tableClasesIda.setVisible(true);
				tableVuelosVuelta.setVisible(true);
				tableClasesVuelta.setVisible(true);
				fechaIda.setVisible(true);
				lblNewLabel_3.setVisible(true);
				fechaVuelta.setVisible(true);
			}
		});
		idaVueltaRadioButton.setBounds(336, 14, 97, 23);
		panelLugarFecha.add(idaVueltaRadioButton);
		
		
		JLabel lblNewLabel_2 = new JLabel("Fecha ida:");
		lblNewLabel_2.setBounds(483, 3, 57, 14);
		panelLugarFecha.add(lblNewLabel_2);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		fechaIda = new JFormattedTextField(format);
		fechaIda.setText("##/##/####");
		fechaIda.setBounds(550, 0, 81, 20);
		panelLugarFecha.add(fechaIda);
		
		lblNewLabel_3 = new JLabel("Fecha vuelta:");
		lblNewLabel_3.setBounds(475, 30, 81, 14);
		panelLugarFecha.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		fechaVuelta = new JFormattedTextField();
		fechaVuelta.setText("##/##/####");
		fechaVuelta.setBounds(550, 27, 81, 20);
		fechaVuelta.setVisible(false);
		panelLugarFecha.add(fechaVuelta);
		
		JButton btnBuscar = new JButton("Buscar");

		btnBuscar.setBounds(667, 13, 162, 23);
		panelLugarFecha.add(btnBuscar);
		OyenteBuscar ob= new OyenteBuscar();
		btnBuscar.addActionListener(ob);
		
		panelIda = new JPanel();
		panelIda.setBounds(10, 62, 839, 137);
		frame.getContentPane().add(panelIda);
		panelIda.setLayout(null);
		
		scrollPaneVueloIda = new JScrollPane();
		scrollPaneVueloIda.setBounds(10, 11, 543, 115);
		panelIda.add(scrollPaneVueloIda);

		
		panelVueloIda = new JPanel();
		scrollPaneVueloIda.setViewportView(panelVueloIda);
		tableVuelosIda = new JTable();
		panelVueloIda.add(tableVuelosIda);
		OyenteTablaIda oi=new OyenteTablaIda();
		tableVuelosIda.setAutoCreateRowSorter(true);
		tableVuelosIda.addMouseListener(oi);
		
		
		
		scrollPaneClaseVueloIda = new JScrollPane();
		scrollPaneClaseVueloIda.setBounds(563, 11, 266, 115);
		panelIda.add(scrollPaneClaseVueloIda);
		
		panelClaseVueloIda = new JPanel();
		scrollPaneClaseVueloIda.setViewportView(panelClaseVueloIda);
		scrollPaneClaseVueloIda.setVisible(true);
		tableClasesIda = new JTable();
		panelClaseVueloIda.add(tableClasesIda);
		tableClasesIda.setAutoCreateRowSorter(true);
		tableClasesIda.setVisible(true);
		panelClaseVueloIda.setVisible(true);
		
		
		panelVuelta = new JPanel();
		panelVuelta.setBounds(10, 210, 839, 137);
		frame.getContentPane().add(panelVuelta);
		panelVuelta.setLayout(null);
		panelVuelta.setVisible(true);
		
		scrollPaneVueloVuelta = new JScrollPane();
		scrollPaneVueloVuelta.setBounds(10, 11, 543, 115);
		panelVuelta.add(scrollPaneVueloVuelta);
		
		panelVueloVuelta = new JPanel();
		scrollPaneVueloVuelta.setViewportView(panelVueloVuelta);
		panelVueloVuelta.setVisible(true);
		
		tableVuelosVuelta = new JTable();
		panelVueloVuelta.add(tableVuelosVuelta);
		OyenteTablaVuelta ov=new OyenteTablaVuelta();
		tableVuelosVuelta.setAutoCreateRowSorter(true);
		tableVuelosVuelta.addMouseListener(ov);
		
		
		scrollPaneClaseVueloVuelta = new JScrollPane();
		scrollPaneClaseVueloVuelta.setBounds(563, 11, 266, 115);
		panelVuelta.add(scrollPaneClaseVueloVuelta);
		
		panelClaseVueloVuelta = new JPanel();
		scrollPaneClaseVueloVuelta.setViewportView(panelClaseVueloVuelta);
		panelClaseVueloVuelta.setVisible(true);
		
		tableClasesVuelta = new JTable();
		panelClaseVueloVuelta.add(tableClasesVuelta);
		
		panelUsuarios = new JPanel();
		panelUsuarios.setBounds(10, 358, 839, 137);
		panelUsuarios.setVisible(true);
		frame.getContentPane().add(panelUsuarios);
		panelUsuarios.setLayout(null);
		
		scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(10, 11, 686, 115);
		panelUsuarios.add(scrollPaneUsuarios);
		
		panelUsuariosDisponibles = new JPanel();
		scrollPaneUsuarios.setViewportView(panelUsuariosDisponibles);
		panelUsuariosDisponibles.setVisible(true);
		
		tableUsuarios = new JTable();
		panelUsuariosDisponibles.add(tableUsuarios);
		tableUsuarios.setAutoCreateRowSorter(true);
		m.refrescarTabla("select * from pasajeros", tableUsuarios,v);
		
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(265, 506, 89, 23);
		frame.getContentPane().add(btnReservar);
		OyenteReservar ore = new OyenteReservar();
		btnReservar.addActionListener(ore);
		
		btnVolverAlInicio = new JButton("Volver al inicio");
		btnVolverAlInicio.setBounds(371, 506, 133, 23);
		frame.getContentPane().add(btnVolverAlInicio);
		OyenteRegresar or= new OyenteRegresar(this.frame);
		btnVolverAlInicio.addActionListener(or);
	}
	
	private class OyenteBuscar implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			tableVuelosIda.setVisible(false);
			tableClasesIda.setVisible(false);
			tableVuelosVuelta.setVisible(false);
			tableClasesVuelta.setVisible(false);
			String origen= (String) origenTextField.getText();
			String destino= (String) destinoTextField.getText();
			if (origen.equals(destino))
				JOptionPane.showMessageDialog(new JFrame(),new String("Seleccione ciudades distintas"), 
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
			//Si todos los datos son correctamente ingresados, se muestran los vuelos de ida entre las ciudaddes especificadas un determinado dia
			else if (idaRadioButton.isSelected()){

				java.sql.Date ida=pasarFechas(fechaIda.getText());
				if (fechaIda!=null)
				{
					String consulta ="SELECT DISTINCT vuelo, modelo_avion, fecha, hora_salida, hora_llegada, origen, ciudad_origen, destino, ciudad_destino, horas_de_vuelo FROM VUELOS_DISPONIBLES WHERE ciudad_origen='"+origen+"' AND ciudad_destino='"+destino+"' AND fecha='"+ida+"';"; 
/*
					try {
						java.sql.Statement st = v.getConexionBD().createStatement();
						ResultSet rs = st.executeQuery(consulta);
						ResultSetMetaData rsmd = rs.getMetaData();
				         System.out.println("querying SELECT * FROM XXX");
				         int columnsNumber = rsmd.getColumnCount();
				         System.out.println(columnsNumber);
				         while (rs.next()) {
				             for (int i = 1; i <= columnsNumber; i++) {
				                 if (i > 1) System.out.print(",  ");
				                 String columnValue = rs.getString(i);
				                 System.out.print(columnValue + " " + rsmd.getColumnName(i));
				             }
				             System.out.println("");
				         }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					m.refrescarTabla(consulta,tableVuelosIda,v);
					tableVuelosIda.setVisible(true);
					btnReservar.setEnabled(true);

				}
				
				
			}
				
			else{
				
				java.sql.Date ida=pasarFechas(fechaIda.getText());
				java.sql.Date vuelta=pasarFechas(fechaVuelta.getText());
				
				if (fechaVuelta!=null && fechaIda!=null) 
					
					if(!ida.after(vuelta)){
						String consulta ="SELECT DISTINCT vuelo, modelo_avion, fecha, hora_salida, hora_llegada, origen, ciudad_origen, destino, ciudad_destino, horas_de_vuelo FROM VUELOS_DISPONIBLES WHERE ciudad_origen='"+origen+"' AND ciudad_destino='"+destino+"' AND fecha='"+ida+"';"; 
						m.refrescarTabla(consulta,tableVuelosIda,v);
						String consulta1 ="SELECT DISTINCT vuelo, modelo_avion, fecha, hora_salida, hora_llegada, origen, ciudad_origen, destino, ciudad_destino, horas_de_vuelo FROM VUELOS_DISPONIBLES WHERE ciudad_origen='"+destino+"' AND ciudad_destino='"+origen+"' AND fecha='"+vuelta+"';"; 
						m.refrescarTabla(consulta1,tableVuelosVuelta,v);
						tableVuelosIda.setVisible(true);
						tableVuelosVuelta.setVisible(true);
						btnReservar.setEnabled(true);

					}
				
					else 
					JOptionPane.showMessageDialog(new JFrame(),new String("Fecha de vuelta anterior a la de ida"), 
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
			private java.sql.Date pasarFechas(String f) {
				String mensajeError=null;
				if (f==null || f=="")
			      {
			         mensajeError = "Debe ingresar un valor para el campo 'Fecha'.";
			      }

			      if (mensajeError != null)
			      {
			         JOptionPane.showMessageDialog(null,
			                                       mensajeError,
			                                       "Error",
			                                       JOptionPane.ERROR_MESSAGE);
			      }
			      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			        Date parsed= null;
					try {
						parsed = format.parse(f);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			        java.sql.Date fecha = new java.sql.Date(parsed.getTime());
			      
			      return fecha;
			}
			
			private class OyenteTablaIda implements MouseListener{

				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					int row = tableVuelosIda.rowAtPoint(arg0.getPoint());
			        if (row >= 0) {
			        	String numero=(String) tableVuelosIda.getValueAt(row,0);
			        	String salida= (String) tableVuelosIda.getValueAt(row,6);
			        	Time h_salida= (Time) tableVuelosIda.getValueAt(row,3);
			        	String llegada=(String) tableVuelosIda.getValueAt(row,8);
			        	Time h_llegada= (Time) tableVuelosIda.getValueAt(row,4);
			        	String modelo=(String) tableVuelosIda.getValueAt(row,1);
			        	String query="SELECT DISTINCT CLASE,DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND CIUDAD_ORIGEN='"+salida+"' AND HORA_SALIDA='"+h_salida+"' AND CIUDAD_DESTINO='"+llegada+"' AND HORA_LLEGADA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';";
			        	//Muestra los datos de un determinado vuelo
			        	m.refrescarTabla(query,tableClasesIda,v);
						tableClasesIda.setVisible(true);
			        }
					
				}
			

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			}
			
			private class OyenteTablaVuelta implements MouseListener{

				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row = tableVuelosVuelta.rowAtPoint(arg0.getPoint());
			        if (row >= 0) {
			        	String numero=(String) tableVuelosVuelta.getValueAt(row,0);
			        	String salida= (String) tableVuelosVuelta.getValueAt(row,6);
			        	Time h_salida= (Time) tableVuelosVuelta.getValueAt(row,3);
			        	String llegada=(String) tableVuelosVuelta.getValueAt(row,8);
			        	Time h_llegada= (Time) tableVuelosVuelta.getValueAt(row,4);
			        	String modelo=(String) tableVuelosVuelta.getValueAt(row,1);
			        	String query="SELECT DISTINCT CLASE,DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND CIUDAD_ORIGEN='"+salida+"' AND HORA_SALIDA='"+h_salida+"' AND CIUDAD_DESTINO='"+llegada+"' AND HORA_LLEGADA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';";
			        	//Muestra los datos de un determinado vuelo
			        	m.refrescarTabla(query,tableClasesVuelta,v);
			        	
						tableClasesVuelta.setVisible(true);
			        }
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			}
				
				private class OyenteReservar implements ActionListener
				   {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//Obtiene las filas seleccionadas
						int rowIda,rowClaseIda, rowVuelta, rowClaseVuelta, rowPasajero;
						rowIda = tableVuelosIda.getSelectedRow();
						rowClaseIda = tableClasesIda.getSelectedRow();
						rowPasajero = tableUsuarios.getSelectedRow();
						if (rowIda==-1 || rowClaseIda==-1 || rowPasajero==-1)
						{
							JOptionPane.showMessageDialog(new JFrame(),
			                        "Falta seleccionar algun parámetro\n", 
			                        "Error",
			                        JOptionPane.ERROR_MESSAGE);
						}
						else 
						{
							CallableStatement cs = null;
							if (idaRadioButton.isSelected())
							{//Lleva a cabo el stored procedure
								try {
									
									System.out.println(" ");
									cs = v.getConexionBD().prepareCall("{ call reservarVueloIda(?,?,?,?,?,?,?)}");
									cs.setString(1,(String)tableVuelosIda.getValueAt(rowIda,0));
									System.out.println(tableVuelosIda.getValueAt(rowIda,0));
									cs.setString(2,(String)tableClasesIda.getValueAt(rowClaseIda,0));
									System.out.println(tableClasesIda.getValueAt(rowClaseIda,0));
									
									cs.setDate(3,pasarFechas(fechaIda.getText()));
									System.out.println(pasarFechas(fechaIda.getText()));
									
									cs.setString(4,(String)tableUsuarios.getValueAt(rowPasajero,0));
									System.out.println(tableUsuarios.getValueAt(rowPasajero,0));
									
									cs.setLong(5,(Long)tableUsuarios.getValueAt(rowPasajero,1));
									System.out.println(tableUsuarios.getValueAt(rowPasajero,1));
									
									cs.setInt(6,legajo);
									System.out.println(legajo);

									System.out.println("FIN ");
									cs.registerOutParameter(7, Types.VARCHAR);
									
									cs.execute();					
										JOptionPane.showMessageDialog(null, cs.getString(7),"Información"
												, JOptionPane.INFORMATION_MESSAGE);
								} catch (SQLException e1) {
									e1.printStackTrace();
									System.out.println( e1.getMessage());
									JOptionPane.showMessageDialog(new JFrame(),
											  
					                        "No se pudo realizar la operacion\n", 
					                        "Error",
					                        JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								//Ejecuta Stored procedure
								try {
									rowVuelta = tableVuelosVuelta.getSelectedRow();
									rowClaseVuelta = tableClasesVuelta.getSelectedRow();
									if (rowVuelta==-1 || rowClaseVuelta==-1 )
									{
										JOptionPane.showMessageDialog(new JFrame(),
						                        "Falta seleccionar algun parámetro\n", 
						                        "Error",
						                        JOptionPane.ERROR_MESSAGE);
									}
									else{
											cs = v.getConexionBD().prepareCall("{ call reservarVueloIdaVuelta(?,?,?,?,?,?,?,?,?,?)}");
											cs.setString(1,(String)tableVuelosIda.getValueAt(rowIda,0));
											System.out.println(tableVuelosIda.getValueAt(rowIda,0));
											cs.setString(2,(String)tableVuelosVuelta.getValueAt(rowVuelta,0));
											System.out.println(tableVuelosVuelta.getValueAt(rowVuelta,0));
											cs.setString(3,(String)tableClasesIda.getValueAt(rowClaseIda,0));
											System.out.println(tableClasesIda.getValueAt(rowClaseIda,0));
											cs.setString(4,(String)tableClasesVuelta.getValueAt(rowClaseVuelta,0));
											System.out.println(tableClasesVuelta.getValueAt(rowClaseVuelta,0));
											cs.setDate(5,pasarFechas(fechaIda.getText()));
											System.out.println(fechaIda.getText());
											cs.setDate(6,pasarFechas(fechaVuelta.getText()));
											System.out.println(fechaVuelta.getText());
											cs.setString(7,(String)tableUsuarios.getValueAt(rowPasajero,0));
											System.out.println(tableUsuarios.getValueAt(rowPasajero,0));
											cs.setLong(8,(Long)tableUsuarios.getValueAt(rowPasajero,1));
											System.out.println(tableUsuarios.getValueAt(rowPasajero,1));
											cs.setInt(9,legajo);
											
											cs.registerOutParameter(10, Types.VARCHAR);
											cs.execute();					
												JOptionPane.showMessageDialog(null, cs.getString(10),"Información"
														, JOptionPane.INFORMATION_MESSAGE);
												//Actualizo las tablas que se modificaron
												String numero=(String) tableVuelosVuelta.getValueAt(rowVuelta,0);
									        	String salida= (String) tableVuelosVuelta.getValueAt(rowVuelta,6);
									        	Time h_salida= (Time) tableVuelosVuelta.getValueAt(rowVuelta,3);
									        	String llegada=(String) tableVuelosVuelta.getValueAt(rowVuelta,8);
									        	Time h_llegada= (Time) tableVuelosVuelta.getValueAt(rowVuelta,4);
									        	String modelo=(String) tableVuelosVuelta.getValueAt(rowVuelta,1);
									        	//Muestra los datos de un determinado vuelo
									        	m.refrescarTabla("SELECT DISTINCT CLASE,DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND CIUDAD_ORIGEN='"+salida+"' AND HORA_SALIDA='"+h_salida+"' AND CIUDAD_DESTINO='"+llegada+"' AND HORA_LLEGADA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';",tableClasesVuelta,v);
									}
								} catch (SQLException e1) {
									e1.printStackTrace();
									JOptionPane.showMessageDialog(new JFrame(),
					                        "No se pudo realizar la operacion\n", 
					                        "Error",
					                        JOptionPane.ERROR_MESSAGE);
								}
							
							
							}
							//Actualizo las tablas que se modificaron
							String numero=(String) tableVuelosIda.getValueAt(rowIda,0);
				        	String salida= (String) tableVuelosIda.getValueAt(rowIda,6);
				        	Time h_salida= (Time) tableVuelosIda.getValueAt(rowIda,3);
				        	String llegada=(String) tableVuelosIda.getValueAt(rowIda,8);
				        	Time h_llegada= (Time) tableVuelosIda.getValueAt(rowIda,4);
				        	String modelo=(String) tableVuelosIda.getValueAt(rowIda,1);
				        	m.refrescarTabla("SELECT DISTINCT CLASE,DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND CIUDAD_ORIGEN='"+salida+"' AND HORA_SALIDA='"+h_salida+"' AND CIUDAD_DESTINO='"+llegada+"' AND HORA_LLEGADA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';",tableClasesIda,v);}
						
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
						
						prev.setVisible(true);
						v.desconectarBD();
						f.dispose();
						
					}
					   
				   }
}
