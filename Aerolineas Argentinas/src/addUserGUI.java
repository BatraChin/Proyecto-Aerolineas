import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import javax.swing.JButton;

public class addUserGUI {

	private JFrame frame;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField tipoDoc;
	private JTextField nroDoc;
	private JTextField direccion;
	private JTextField telefono;
	private JTextField nacionalidad;
	private VuelosConexion v;
	private JTable tableUsuarios;


	/**
	 * Create the application.
	 */
	public addUserGUI(VuelosConexion vuelos, JTable t) {
		v= vuelos;
		tableUsuarios=t;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 282, 259);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblTipoDeDocumento = new JLabel("Tipo de documento");
		lblTipoDeDocumento.setBounds(10, 61, 91, 14);
		frame.getContentPane().add(lblTipoDeDocumento);
		
		JLabel lblNumeroDeDocumento = new JLabel("Numero de documento");
		lblNumeroDeDocumento.setBounds(10, 86, 114, 14);
		frame.getContentPane().add(lblNumeroDeDocumento);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(10, 111, 46, 14);
		frame.getContentPane().add(lblDireccin);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 136, 46, 14);
		frame.getContentPane().add(lblTelefono);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(10, 161, 60, 14);
		frame.getContentPane().add(lblNacionalidad);
		
		nombre = new JTextField();
		nombre.setBounds(136, 8, 122, 20);
		frame.getContentPane().add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setBounds(136, 33, 122, 20);
		frame.getContentPane().add(apellido);
		apellido.setColumns(10);
		
		tipoDoc = new JTextField();
		tipoDoc.setBounds(136, 58, 122, 20);
		frame.getContentPane().add(tipoDoc);
		tipoDoc.setColumns(10);
		
		nroDoc = new JTextField();
		nroDoc.setBounds(136, 83, 122, 20);
		frame.getContentPane().add(nroDoc);
		nroDoc.setColumns(10);
		
		direccion = new JTextField();
		direccion.setBounds(136, 108, 122, 20);
		frame.getContentPane().add(direccion);
		direccion.setColumns(10);
		
		telefono = new JTextField();
		telefono.setBounds(136, 133, 122, 20);
		frame.getContentPane().add(telefono);
		telefono.setColumns(10);
		
		nacionalidad = new JTextField();
		nacionalidad.setBounds(136, 158, 122, 20);
		frame.getContentPane().add(nacionalidad);
		nacionalidad.setColumns(10);
		
		JButton btnAgregarUsuario = new JButton("Agregar usuario");
		btnAgregarUsuario.setBounds(77, 186, 114, 23);
		frame.getContentPane().add(btnAgregarUsuario);
		OyenteAceptar oa=new OyenteAceptar(this.frame);
		btnAgregarUsuario.addActionListener(oa);	
		
	}
	
	private class OyenteAceptar implements ActionListener
	{
		 JFrame f;
		 public OyenteAceptar(JFrame frame){
			 f=frame;
		 }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (nombre.getText().length()==0 || apellido.getText().length()==0 || tipoDoc.getText().length()==0 || nroDoc.getText().length()==0 || nacionalidad.getText().length()==0 || telefono.getText().length()==0 || direccion.getText().length()==0)
			{
				JOptionPane.showMessageDialog(new JFrame(),
                        "Se deben ingresar todos los campos\n", 
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				ModificarTabla mod=new ModificarTabla();
				System.out.println("INSERT INTO pasajeros VALUES ('"+tipoDoc.getText()+"', "+Integer.parseInt(nroDoc.getText())+", '"+apellido.getText()+"' ,'"+nombre.getText()+"', '"+direccion.getText()+"', '"+telefono.getText()+"', '"+nacionalidad.getText()+"');");
				
				try{
					 // se crea una sentencia o comando jdbc para realizar la consulta
			        Statement stmt =v.getConexionBD().createStatement();
			        
			        stmt.executeUpdate("INSERT INTO pasajeros VALUES ('"+tipoDoc.getText()+"', "+Integer.parseInt(nroDoc.getText())+", '"+apellido.getText()+"' ,'"+nombre.getText()+"', '"+direccion.getText()+"', '"+telefono.getText()+"', '"+nacionalidad.getText()+"');");
			        
			        JOptionPane.showMessageDialog(null, "Modificacion realizada correctamente.","Información"
							, JOptionPane.INFORMATION_MESSAGE);

			        stmt.close();
			     }
			     catch (SQLException ex)
			     {
			        
			        JOptionPane.showMessageDialog(new JFrame(),
			                                      ex.getMessage() + "\n", 
			                                      "Error al ejecutar la consulta.",
			                                      JOptionPane.ERROR_MESSAGE);
			        
			     }
								
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(new JFrame(),
	                        "El campo Nro de Documento debe ser numérico\n", 
	                        "Error",
	                        JOptionPane.ERROR_MESSAGE);
				}

				mod.refrescarTabla("select * from pasajeros", tableUsuarios,v);
				f.dispose();
				
			}
			
			}
			
	}
	
}
