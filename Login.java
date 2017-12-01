package Scheduler;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import Scheduler.AdminPanel;
import Scheduler.UserPanel;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 298);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        Test.writeObjectToFile(Test.scheduler);
		        frame.dispose();
		    }
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblSchedulingSystem = new JLabel("Scheduling System ");
		lblSchedulingSystem.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblSchedulingSystem.setBounds(197, 18, 347, 56);
		frame.getContentPane().add(lblSchedulingSystem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 87, 567, 9);
		frame.getContentPane().add(separator);
		
		JButton btnCoachView = new JButton("Coach View");
		btnCoachView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPanel.main(null);
				frame.dispose();
			}
		});
		btnCoachView.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCoachView.setBounds(101, 124, 143, 38);
		frame.getContentPane().add(btnCoachView);
		
		JButton btnPrincipalView = new JButton("Principal View");
		btnPrincipalView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPanel.main(null);
				frame.dispose();
			}
		});
		btnPrincipalView.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrincipalView.setBounds(305, 124, 150, 38);
		frame.getContentPane().add(btnPrincipalView);
	}
}
