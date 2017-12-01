package Scheduler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class ViewRequest {

	private JFrame frame;
	public static Booking requestToView = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRequest window = new ViewRequest();
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
	public ViewRequest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 565);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { frame.dispose(); }
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblBookingDetails = new JLabel("Booking Details");
		lblBookingDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookingDetails.setBounds(143, 13, 150, 33);
		frame.getContentPane().add(lblBookingDetails);
		
		JLabel lblRequestedBy = new JLabel("Requested by: ".concat(requestToView.getUser()));
		lblRequestedBy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRequestedBy.setBounds(26, 58, 366, 33);
		frame.getContentPane().add(lblRequestedBy);
		
		JLabel lblSpace = new JLabel("Space: ".concat(requestToView.getAssociatedSpace().toString()));
		lblSpace.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSpace.setBounds(26, 92, 366, 33);
		frame.getContentPane().add(lblSpace);
		
		JLabel lblRequestedSlots = new JLabel("Requested Slots:");
		lblRequestedSlots.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRequestedSlots.setBounds(130, 168, 177, 33);
		frame.getContentPane().add(lblRequestedSlots);
		
		JList<Interval> requestedSlotList = new JList<>();
		requestedSlotList.setBounds(26, 202, 366, 196);
		DefaultListModel<Interval> slotModel = new DefaultListModel<>();
		for (Interval interval: requestToView.getSlots())
			slotModel.addElement(interval);
		requestedSlotList.setModel(slotModel);
		frame.getContentPane().add(requestedSlotList);
		
		String lblApprovedText = "Not Approved";
		if (requestToView.isApproved())
			lblApprovedText = requestToView.getApproved().toString();
		JLabel lblApproved = new JLabel("Approved: ".concat(lblApprovedText));
		lblApproved.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApproved.setBounds(26, 125, 366, 33);
		frame.getContentPane().add(lblApproved);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClose.setBounds(143, 416, 120, 33);
		btnClose.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) { frame.dispose();}
		});
		frame.getContentPane().add(btnClose);
	}
}
