import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;

import javax.swing.JLabel;
import javax.swing.JFileChooser;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setTitle("QR Reader | Dise\u00F1o Detallado de Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JFileChooser fileChooser = new JFileChooser();
		
		final JLabel lblImagenQR = new JLabel(" ");
		lblImagenQR.setBounds(23, 34, 100, 100);
		contentPane.add(lblImagenQR);
		
		JLabel lblContenido = new JLabel("Contenido:");
		lblContenido.setBounds(141, 34, 66, 23);
		contentPane.add(lblContenido);
		
		final JLabel lblContent = new JLabel("");
		lblContent.setBounds(213, 38, 500, 14);
		contentPane.add(lblContent);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String path = lblImagenQR.getIcon().toString();
					String[] obj = new String[1];
					obj[0] = path;
					Resource qr = new QRReader();
					QRObserver observer = new QRObserver();
					qr.setObserver(observer);
					qr.reciveAction(obj, 0);
					QRCode code = (QRCode)observer.getQRCode();
			    	String content = "Texto: "+code.getText()+" Tipo: "+code.getType();
					lblContent.setText(content);
					
				}
				catch (Exception ex) 
				{
					lblContent.setText("Error. Input inv�lido");
				}

			}
		});
		btnProcesar.setBounds(23, 174, 89, 23);
		contentPane.add(btnProcesar);
		
		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try
				{
					fileChooser.showOpenDialog(null);
					String path = fileChooser.getSelectedFile().getPath();
					ImageIcon icon = new ImageIcon(path);
					lblImagenQR.setIcon(icon);
					lblImagenQR.setSize(100, 100);
				}
				catch (Exception ex) {
					lblImagenQR.setText("Imagen no encontrada");
					ex.printStackTrace();
				}
			}
		});
		btnExaminar.setBounds(23, 143, 89, 23);
		contentPane.add(btnExaminar);
		
	}
}
