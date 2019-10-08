import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaLoginAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pwdContrasea;
	private static ventanaLoginAdmin frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ventanaLoginAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaLoginAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
		txtUsuario.setText("usuario");
		txtUsuario.setBounds(137, 52, 96, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		pwdContrasea = new JPasswordField();
		pwdContrasea.setText("contrase\u00F1a");
		pwdContrasea.setBounds(147, 92, 86, 20);
		contentPane.add(pwdContrasea);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnCancelar.setBounds(21, 172, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnConfirmar.setBounds(205, 172, 89, 23);
		contentPane.add(btnConfirmar);
	}
}
