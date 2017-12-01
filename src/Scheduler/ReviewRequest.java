package Scheduler;

import java.awt.EventQueue;
import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JButton;

public class ReviewRequest {

	private JFrame frame;
	public static Booking requestToReview = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewRequest window = new ReviewRequest();
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
	public ReviewRequest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 528);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        Test.writeObjectToFile(Test.scheduler);
		        frame.dispose();
		    }
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblReviewRequest = new JLabel("Review Request");
		lblReviewRequest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReviewRequest.setBounds(287, 28, 161, 33);
		frame.getContentPane().add(lblReviewRequest);
		
		// Intervals that were requested
		JList<Interval> slotList = new JList<>();
		slotList.setFont(new Font("Times New Roman", Font.BOLD, 18));
		slotList.setBounds(26, 90, 358, 316);
		slotList.setModel(new AbstractListModel<Interval>() {
			private static final long serialVersionUID = 1L;
			public int getSize() {
				return requestToReview.getSlots().size();
			}
			public Interval getElementAt(int index) {
				return requestToReview.getSlots().get(index);
			}
		});
		frame.getContentPane().add(slotList);
		
		// Details of the associated Space
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDetails.setBounds(505, 84, 115, 33);
		frame.getContentPane().add(lblDetails);
		
		JLabel lblLocation = new JLabel("Location: ".concat(requestToReview.getAssociatedSpace().getLocation()));
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocation.setBounds(410, 178, 289, 33);
		frame.getContentPane().add(lblLocation);
		
		JLabel lblSemester = new JLabel("Semester: ".concat(requestToReview.getAssociatedSpace().getSemester()));
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSemester.setBounds(410, 212, 289, 33);
		frame.getContentPane().add(lblSemester);
		
		JLabel lblTime = new JLabel("Time: ".concat(requestToReview.getAssociatedSpace().getTime().toString()));
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTime.setBounds(410, 145, 289, 33);
		frame.getContentPane().add(lblTime);

		JLabel lblUser = new JLabel("Requested by: ".concat(requestToReview.getUser()));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(410, 245, 289, 33);
		frame.getContentPane().add(lblUser);

		// Accept and Reject buttons
		JButton btnAccept = new JButton("Accept");
		btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!slotList.isSelectionEmpty()) {
					Test.scheduler.getPendingBookings().remove(requestToReview);
					Interval accepted = slotList.getSelectedValue();
					requestToReview.setApproved(accepted);
					requestToReview.getAssociatedSpace().addBlocked(accepted);
					Test.scheduler.getAcceptedBookings().add(requestToReview);
					frame.dispose();
					AdminPanel.main(null);
				}
				else;
			}
		});
		btnAccept.setBounds(409, 346, 115, 33);
		frame.getContentPane().add(btnAccept);
		
		JButton btnReject = new JButton("Reject");
		btnReject.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test.scheduler.getPendingBookings().remove(requestToReview);
				Test.scheduler.getRejectedBookings().add(requestToReview);
				frame.dispose();
				AdminPanel.main(null);
			}
		});
		btnReject.setBounds(569, 346, 115, 33);
		frame.getContentPane().add(btnReject);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backButton.setBounds(610, 21, 115, 33);
		backButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminPanel.main(null);
			}
		});
		frame.getContentPane().add(backButton);
	}
}
