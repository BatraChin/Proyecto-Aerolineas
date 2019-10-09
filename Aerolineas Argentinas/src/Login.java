import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class Login extends JFrame {

	private JPanel contentPane;
	private ButtonGroup grupoSeleccion;
	private JPanel panelSeleccion;
	private JRadioButton rdbtnConsutlassql;
	private JRadioButton rdbtnReservas;
	private JPanel panel;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnIngreso;
	private VuelosConexion dbVuelos;
	private String password;
	private JFrame panelContent;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Login() {
		dbVuelos = new VuelosConexion();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelSeleccion = new JPanel();
		panelSeleccion.setBounds(20, 14, 301, 51);
		contentPane.add(panelSeleccion);
		panelSeleccion.setLayout(null);
		
		rdbtnConsutlassql = new JRadioButton("ConsutlasSql");
		rdbtnConsutlassql.setBounds(10, 8, 127, 25);
		panelSeleccion.add(rdbtnConsutlassql);
		
		rdbtnReservas = new JRadioButton("Reservas");
		rdbtnReservas.setBounds(164, 8, 127, 25);
		panelSeleccion.add(rdbtnReservas);
		
		grupoSeleccion = new ButtonGroup();
		grupoSeleccion.add(rdbtnReservas);
		grupoSeleccion.add(rdbtnConsutlassql);
		
		panel = new JPanel();
		panel.setBounds(22, 97, 301, 132);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 56, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 57, 110, 22);
		panel.add(passwordField);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsuario.setBounds(15, 32, 68, 16);
		panel.add(lblUsuario);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPassword.setBounds(170, 33, 88, 16);
		panel.add(lblPassword);
		
		panelContent = this;
		btnIngreso = new JButton("Ingreso");
		
		btnIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				password= "";
				char[] p=passwordField.getPassword();
	            for(char s:p)
	               password=password+s;
	            String aux=MD5(password);
	            password=aux;
			 
				if (rdbtnReservas.isSelected())
				{
				    if (dbVuelos.conectarBDempleado(Integer.parseInt(textField.getText()), password))
					{
							ReservasGUI reservas = new ReservasGUI(dbVuelos, panelContent, Integer.parseInt(textField.getText()));
							textField.setText("");
							passwordField.setText("");
							setVisible(false);
					}
					else 
						JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
		                    JOptionPane.ERROR_MESSAGE);
				}
				
				
				if (rdbtnConsutlassql.isSelected())
				{
					System.out.println(passwordField.getText());
					if (dbVuelos.conectarBD("admin", "admin"))
					{

						System.out.println(passwordField.getText());
							SQLGUI consultasSQL=new SQLGUI(dbVuelos,panelContent);
							textField.setText("");
							passwordField.setText("");
							setVisible(false);
					}
					else 
						JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
		                    JOptionPane.ERROR_MESSAGE);
				}
					
			}

		}
		
		);
		
		
		btnIngreso.setBounds(97, 103, 97, 25);
		panel.add(btnIngreso);
		
	
	
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
