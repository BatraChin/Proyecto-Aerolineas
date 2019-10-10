import java.awt.BorderLayout;
import java.awt.EventQueue;

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
		            String aux=MD5(password);
		            password=aux;
					
					
					System.out.println(passwordField.getText());
					if (dbVuelos.conectarBD("admin", "admin"))
					{
	
						System.out.println(passwordField.getText());
							ConsultaSQL consultasSQL=new ConsultaSQL(dbVuelos,panelContent);
							textField.setText("");
							passwordField.setText("");
							setVisible(false);
					}
					else 
						JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
		                    JOptionPane.ERROR_MESSAGE);
					
					
			}
		});
		btnConfirmar.setBounds(205, 172, 89, 23);
		contentPane.add(btnConfirmar);
	}
	
	
	private String MD5(String md5) {
		try {
				 java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
				 byte[] array = md.digest(md5.getBytes());
				 StringBuffer sb = new StringBuffer();
				 for (int i = 0; i < array.length; ++i) {
				  sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			 	 return sb.toString();
			} 	 catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	
	
}
