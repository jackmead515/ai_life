package frame;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {

	private JFrame frame;
	private JTextField emailField;
	private JTextField passwordField;
	
	private class Panel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			try {
				g.drawImage(ImageIO.read(getClass().getResource("images/login_background.jpg").openStream()), 0, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 511, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(6, 6, 500, 500);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.repaint();
		
		passwordField = new JTextField();
		passwordField.setText("Password...");
		passwordField.setBounds(150, 238, 200, 26);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setText("Email...");
		emailField.setBounds(150, 200, 200, 26);
		panel.add(emailField);
		emailField.setColumns(10);
	}
}
