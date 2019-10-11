

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaLoginAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private static ventanaLoginAdmin frame = new ventanaLoginAdmin();

	private JPanel panel;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JButton btnIngreso;
	private VuelosConexion dbVuelos;
	private String password;
	private JFrame panelContent;
	private JTextField textField;

	

	/**
	 * Create the frame.
	 */
	public ventanaLoginAdmin() {
		
		dbVuelos = new VuelosConexion();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setTitle("LOGIN ADMINISTRADOR");

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("usuario");
		lblUsuario.setBounds(30, 55, 48, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("contrase\u00F1a");
		lblContrasea.setBounds(30, 95, 59, 14);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("usuario");
		txtUsuario.setBounds(137, 52, 96, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("contrase\u00F1a");
		passwordField.setBounds(147, 92, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnCancelar.setBounds(21, 172, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					
				
				password= "";
				char[] p=passwordField.getPassword();
	            for(char s:p)
	               password=password+s;
	            
	            
				if (!password.equals("admin") || !txtUsuario.getText().equals("admin"))
						{JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
			                    JOptionPane.ERROR_MESSAGE);
						//txtUsuario.setText("");
						passwordField.setText("");
						}
				else
				   {
					
					if (dbVuelos.conectarBD(txtUsuario.getText(), password))
					{
	
							ConsultaSQL consultasSQL=new ConsultaSQL(dbVuelos,panelContent);
							txtUsuario.setText("");
							passwordField.setText("");
							setVisible(false);
					}
					/*else 
						JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
		                    JOptionPane.ERROR_MESSAGE);
				   */
				   }
					
			}
		});
		btnConfirmar.setBounds(205, 172, 89, 23);
		contentPane.add(btnConfirmar);
	}
	
	
	
	
}
