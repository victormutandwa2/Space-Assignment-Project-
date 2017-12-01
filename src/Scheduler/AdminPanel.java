
package Scheduler;
import java.awt.EventQueue;
import java.util.LinkedList;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class AdminPanel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel window = new AdminPanel();
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
	public AdminPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 755, 624);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        Test.writeObjectToFile(Test.scheduler);
		        frame.dispose();
		    }
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblScheduleViewer = new JLabel("Principal (Admin version)");
		lblScheduleViewer.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblScheduleViewer.setBounds(246, 46, 262, 40);
		frame.getContentPane().add(lblScheduleViewer);
		
		// Pending bookings list
		JLabel lblBookings = new JLabel("Pending Requests");
		lblBookings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBookings.setBounds(69, 177, 226, 32);
		frame.getContentPane().add(lblBookings);
		
		JList<Booking> pendingBookingsList = new JList<>();
		pendingBookingsList.setFont(new Font("Times New Roman", Font.BOLD, 18));
		pendingBookingsList.setModel(new AbstractListModel<Booking>() {
			private static final long serialVersionUID = 1L;
			public int getSize() {
				return Test.scheduler.getPendingBookings().size();
			}
			public Booking getElementAt(int index) {
				return Test.scheduler.getPendingBookings().get(index);
			}
		});
		pendingBookingsList.setBounds(69, 212, 277, 222);
		frame.getContentPane().add(pendingBookingsList);
		
		// Review pending booking
		JButton btnReview = new JButton("Review");
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pendingBookingsList.isSelectionEmpty()) {
					ReviewRequest.requestToReview = pendingBookingsList.getSelectedValue();
					ReviewRequest.main(null);
					frame.dispose();
				}
				else;
			}
		});
		btnReview.setBounds(142, 463, 117, 25);
		frame.getContentPane().add(btnReview);
		
		// Slots list
		JLabel lblAvailability = new JLabel("Slots Available");
		lblAvailability.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAvailability.setBounds(392, 185, 226, 16);
		frame.getContentPane().add(lblAvailability);
		
		JList<Space> slotsList = new JList<>();
		slotsList.setModel(new AbstractListModel<Space>() {
			private static final long serialVersionUID = 1L;
			public int getSize() {
				return Test.scheduler.getAvailableSpaces().size();
			}
			public Space getElementAt(int index) {
				if (Test.scheduler.getAvailableSpaces().size() != 0) {
				    return Test.scheduler.getAvailableSpaces().get(index);
				}
				return null;
			}
		});
		slotsList.setFont(new Font("Times New Roman", Font.BOLD, 18));
		slotsList.setBounds(372, 212, 282, 222);
		frame.getContentPane().add(slotsList);
		
		// Slots Add and Remove buttons
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSpaces.main(null);
				frame.dispose();
			}
		});
		addButton.setBounds(398, 463, 97, 25);
		frame.getContentPane().add(addButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = slotsList.getSelectedValue();
				
				LinkedList<Space> spaces = Test.scheduler.getAvailableSpaces();
				
				if (selected == null);
				else {
					for (Space space: spaces) {
				        if (space.equals(selected)) {
						    spaces.remove(space);
						    frame.repaint();
					    }
				    }
				}

			}
		});
		removeButton.setBounds(521, 463, 97, 25);
		frame.getContentPane().add(removeButton);
		
		// Logout
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.main(null);
				frame.dispose();
			}
		});
		btnLogout.setBounds(599, 57, 100, 25);
		frame.getContentPane().add(btnLogout);
	}
}
