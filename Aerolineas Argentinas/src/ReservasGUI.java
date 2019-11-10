
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
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPaneVueloIda;
	private JScrollPane scrollPaneVueloVuelta;
	private JScrollPane scrollPaneClaseVueloIda;
	private JScrollPane scrollPaneClaseVueloVuelta;
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
		//prev.setVisible(false);
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 878, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		panelLugarFecha = new Panel();
		panelLugarFecha.setBounds(10, 10, 839, 100);
		frame.getContentPane().add(panelLugarFecha);
		panelLugarFecha.setLayout(null);
		panelLugarFecha.setVisible(true);
		
		lblNewLabel = new JLabel("Origen");
		lblNewLabel.setBounds(87, 20, 46, 14);
		panelLugarFecha.add(lblNewLabel);
		
		origenTextField = new JTextField();
		origenTextField.setBounds(131, 17, 86, 20);
		panelLugarFecha.add(origenTextField);
		origenTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Destino");
		lblNewLabel_1.setBounds(246, 20, 46, 14);
		panelLugarFecha.add(lblNewLabel_1);
		
		destinoTextField = new JTextField();
		destinoTextField.setBounds(302, 17, 86, 20);
		panelLugarFecha.add(destinoTextField);
		destinoTextField.setColumns(10);
		
		idaRadioButton = new JRadioButton("Ida");
		idaRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idaRadioButton.setSelected(true);
				idaVueltaRadioButton.setSelected(false);
				tableVuelosIda.setVisible(true);
				tableClasesIda.setVisible(true);
				tableVuelosVuelta.setVisible(false);
				tableClasesVuelta.setVisible(false);
				fechaIda.setVisible(true);
				lblNewLabel_3.setVisible(false);
				fechaVuelta.setVisible(false);
			}
		});
		idaRadioButton.setBounds(431, 16, 46, 23);
		panelLugarFecha.add(idaRadioButton);		
		idaRadioButton.setSelected(true);
		
		idaVueltaRadioButton = new JRadioButton("Ida y vuelta");
		idaVueltaRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idaVueltaRadioButton.setSelected(true);
				idaRadioButton.setSelected(false);
				
				tableVuelosIda.setVisible(true);
				tableClasesIda.setVisible(true);
				tableVuelosVuelta.setVisible(true);
				tableClasesVuelta.setVisible(true);
				fechaIda.setVisible(true);
				lblNewLabel_3.setVisible(true);
				fechaVuelta.setVisible(true);
			}
		});
		idaVueltaRadioButton.setBounds(496, 16, 97, 23);
		panelLugarFecha.add(idaVueltaRadioButton);
		
		
		JLabel lblNewLabel_2 = new JLabel("Fecha ida:");
		lblNewLabel_2.setBounds(87, 58, 57, 14);
		panelLugarFecha.add(lblNewLabel_2);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		fechaIda = new JFormattedTextField(format);
		fechaIda.setText("##/##/####");
		fechaIda.setBounds(141, 55, 81, 20);
		panelLugarFecha.add(fechaIda);
		
		lblNewLabel_3 = new JLabel("Fecha vuelta:");
		lblNewLabel_3.setBounds(227, 58, 81, 14);
		panelLugarFecha.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		fechaVuelta = new JFormattedTextField();
		fechaVuelta.setText("##/##/####");
		fechaVuelta.setBounds(302, 55, 81, 20);
		fechaVuelta.setVisible(false);
		panelLugarFecha.add(fechaVuelta);
		
		JButton btnBuscar = new JButton("Buscar");

		btnBuscar.setBounds(622, 38, 162, 23);
		panelLugarFecha.add(btnBuscar);
		OyenteBuscar ob= new OyenteBuscar();
		btnBuscar.addActionListener(ob);
		
		panelIda = new JPanel();
		panelIda.setBounds(10, 144, 839, 137);
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
		panelVuelta.setBounds(10, 310, 839, 137);
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
		
		btnVolverAlInicio = new JButton("Volver al inicio");
		btnVolverAlInicio.setBounds(370, 492, 133, 23);
		frame.getContentPane().add(btnVolverAlInicio);
		
		JLabel lblVueloDeIda = new JLabel("Vuelo de Ida");
		lblVueloDeIda.setBounds(27, 132, 75, 14);
		frame.getContentPane().add(lblVueloDeIda);
		
		JLabel lblVueloDeVuelta = new JLabel("Vuelo de Vuelta");
		lblVueloDeVuelta.setBounds(31, 296, 97, 14);
		frame.getContentPane().add(lblVueloDeVuelta);
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
					String consulta ="SELECT DISTINCT Cod_vuelo, Modelo_avion, Fecha, Hora_salida, Hora_llegada, Pais_origen, Ciudad_origen, Pais_destino, Ciudad_destino, Tiempo_estimado FROM VUELOS_DISPONIBLES WHERE Ciudad_origen='"+origen+"' AND Ciudad_destino='"+destino+"' AND Fecha='"+ida+"';"; 
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
					System.out.println("aca");
					tableVuelosIda.setVisible(true);
					

				}
				
				
			}
				
			else{
				
				java.sql.Date ida=pasarFechas(fechaIda.getText());
				java.sql.Date vuelta=pasarFechas(fechaVuelta.getText());
				
				if (fechaVuelta!=null && fechaIda!=null) 
					
					if(!ida.after(vuelta)){
						String consulta ="SELECT DISTINCT Cod_vuelo, Modelo_avion, Fecha, Hora_salida, Hora_llegada, Pais_origen, Ciudad_origen, Pais_destino, Ciudad_destino, Tiempo_estimado FROM VUELOS_DISPONIBLES WHERE Ciudad_origen='"+origen+"' AND Ciudad_destino='"+destino+"' AND Fecha='"+ida+"';"; 
						m.refrescarTabla(consulta,tableVuelosIda,v);
						String consulta1 ="SELECT DISTINCT Cod_vuelo, Modelo_avion, Fecha, Hora_salida, Hora_llegada, Pais_origen, Ciudad_origen, Pais_destino, Ciudad_destino, Tiempo_estimado FROM VUELOS_DISPONIBLES WHERE Ciudad_origen='"+destino+"' AND Ciudad_destino='"+origen+"' AND Fecha='"+vuelta+"';"; 
						m.refrescarTabla(consulta1,tableVuelosVuelta,v);
						tableVuelosIda.setVisible(true);
						tableVuelosVuelta.setVisible(true);
						

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
			        	String query="SELECT DISTINCT Clase, Asientos_disponibles,Precio FROM vuelos_disponibles WHERE Cod_vuelo='"+numero+"' AND Ciudad_origen='"+salida+"' AND Hora_salida='"+h_salida+"' AND Ciudad_destino='"+llegada+"' AND Hora_llegada='"+h_llegada+"' AND Modelo_avion='"+modelo+"';";
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
