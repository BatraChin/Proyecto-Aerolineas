import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Empleado {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empleado window = new Empleado();
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
	public Empleado() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//esto hay que comentarlo aca
		
		JRadioButton rdbtnIda = new JRadioButton("Ida");
		rdbtnIda.setBounds(57, 39, 109, 23);
		frame.getContentPane().add(rdbtnIda);
		
		JRadioButton rdbtnIdaYVuelta = new JRadioButton("Ida y Vuelta");
		rdbtnIdaYVuelta.setBounds(181, 39, 109, 23);
		frame.getContentPane().add(rdbtnIdaYVuelta);
		
		JComboBox origenBox = new JComboBox();
		origenBox.setBounds(57, 92, 30, 22);
		frame.getContentPane().add(origenBox);
		
		JComboBox DestinoBox = new JComboBox();
		DestinoBox.setBounds(243, 92, 30, 22);
		frame.getContentPane().add(DestinoBox);
		
		JButton btnBuscarVuelos = new JButton("Buscar Vuelos");
		btnBuscarVuelos.setBounds(142, 196, 109, 23);
		frame.getContentPane().add(btnBuscarVuelos);
	}
}
