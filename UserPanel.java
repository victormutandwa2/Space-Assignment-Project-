package Scheduler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UserPanel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPanel window = new UserPanel();
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
	public UserPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 920, 572);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        Test.writeObjectToFile(Test.scheduler);
		        frame.dispose();
		    }
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblScheduleViewer = new JLabel("Manage Booking (Coach Version)");
		lblScheduleViewer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblScheduleViewer.setBounds(325, 28, 264, 40);
		frame.getContentPane().add(lblScheduleViewer);
		
		// Accepted, Pending and Rejected bookings
        JLabel lblAcceptedRequests = new JLabel("Accepted Requests");
		lblAcceptedRequests.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAcceptedRequests.setBounds(81, 162, 160, 30);
		frame.getContentPane().add(lblAcceptedRequests);
		
		JList<Booking> acceptedList = new JList<Booking>();
		DefaultListModel<Booking> acceptedModel = new DefaultListModel<>();
		acceptedList.setModel(acceptedModel);
		acceptedList.setBounds(26, 201, 264, 198);
		frame.getContentPane().add(acceptedList);
		
		JLabel lblPendingRequests = new JLabel("Pending Requests");
		lblPendingRequests.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPendingRequests.setBounds(362, 161, 160, 33);
		frame.getContentPane().add(lblPendingRequests);
		
		JList<Booking> pendingList = new JList<>();
		pendingList.setBounds(308, 201, 270, 198);
		DefaultListModel<Booking> pendingModel = new DefaultListModel<>();
		pendingList.setModel(pendingModel);
		frame.getContentPane().add(pendingList);
		
		JLabel lblRejectedRequests = new JLabel("Rejected Requests");
		lblRejectedRequests.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRejectedRequests.setBounds(650, 161, 160, 33);
		frame.getContentPane().add(lblRejectedRequests);
		
		JList<Booking> rejectedList = new JList<>();
		rejectedList.setBounds(598, 201, 264, 198);
		DefaultListModel<Booking> rejectedModel = new DefaultListModel<>();
		rejectedList.setModel(rejectedModel);
		frame.getContentPane().add(rejectedList);
		
		// Select a member by name
		JLabel lblSelectMember = new JLabel("Select Member:");
		lblSelectMember.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectMember.setBounds(12, 11, 160, 30);
		frame.getContentPane().add(lblSelectMember);
		
		JComboBox<String> memberComboBox = new JComboBox<>();
		memberComboBox.setBounds(12, 51, 235, 30);
		String[] users = new String[Test.scheduler.getUsers().size()];
		for (int i = 0; i < users.length; i++)
			users[i] = Test.scheduler.getUsers().get(i);
		memberComboBox.setModel(new DefaultComboBoxModel<String>(users));
		if (memberComboBox.getSelectedItem() != null) {
			String user = memberComboBox.getSelectedItem().toString();
			for (Booking booking: Test.scheduler.getAcceptedBookings()) {
				if (booking.getUser().equals(user))
					acceptedModel.addElement(booking);
			}
			
			for (Booking booking: Test.scheduler.getPendingBookings()) {
				if (booking.getUser().equals(user))
					pendingModel.addElement(booking);
			}
			
			for (Booking booking: Test.scheduler.getRejectedBookings()) {
				if (booking.getUser().equals(user))
					rejectedModel.addElement(booking);
			}
		}
		memberComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				acceptedModel.clear();
			    pendingModel.clear();
			    rejectedModel.clear();
				String user = memberComboBox.getSelectedItem().toString();
				for (Booking booking: Test.scheduler.getAcceptedBookings()) {
					if (booking.getUser().equals(user))
						acceptedModel.addElement(booking);
				}
				
				for (Booking booking: Test.scheduler.getPendingBookings()) {
					if (booking.getUser().equals(user))
						pendingModel.addElement(booking);
				}
				
				for (Booking booking: Test.scheduler.getRejectedBookings()) {
					if (booking.getUser().equals(user))
						rejectedModel.addElement(booking);
				}
			    frame.repaint();
			}
		});
		frame.getContentPane().add(memberComboBox);
		
		// Logout and Book buttons
		JButton btnBook = new JButton("Book");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSpace.main(null);
				frame.dispose();
			}
		});
		btnBook.setBounds(223, 427, 147, 40);
		frame.getContentPane().add(btnBook);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.main(null);
				frame.dispose();
			}
		});
		btnLogout.setBounds(740, 28, 122, 35);
		frame.getContentPane().add(btnLogout);
		
		// View button - allows Coach to click a request and view its details
		JButton viewButton = new JButton("View");
		viewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking request = null;
				if (!acceptedList.isSelectionEmpty())
					request = acceptedList.getSelectedValue();
				else if (!pendingList.isSelectionEmpty())
					request = pendingList.getSelectedValue();
				else if (!rejectedList.isSelectionEmpty())
					request = rejectedList.getSelectedValue();
				
				if (request == null);
				else {
					ViewRequest.requestToView = request;
					ViewRequest.main(null);
				}
			}
		});
		viewButton.setBounds(476, 424, 147, 40);
		frame.getContentPane().add(viewButton);
	}
}
