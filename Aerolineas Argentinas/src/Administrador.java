import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Administrador {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrador window = new Administrador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Administrador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 566, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIngreseLaConsulta = new JLabel("Ingrese la consulta deseada");
		lblIngreseLaConsulta.setBounds(22, 24, 142, 14);
		frame.getContentPane().add(lblIngreseLaConsulta);
		
		textField = new JTextField();
		textField.setBounds(22, 49, 197, 61);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setBounds(22, 132, 89, 23);
		frame.getContentPane().add(btnEjecutar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(121, 132, 89, 23);
		frame.getContentPane().add(btnLimpiar);
	}
}
