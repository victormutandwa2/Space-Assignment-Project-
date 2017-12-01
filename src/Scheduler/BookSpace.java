package Scheduler;

import java.util.LinkedList;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;

public class BookSpace {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSpace window = new BookSpace();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookSpace() { 
		initialize();
    }
	
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        Test.writeObjectToFile(Test.scheduler);
		        frame.dispose();
		    }
		});
		frame.setBounds(100, 100, 811, 836);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMakeABooking = new JLabel("Make a booking");
		lblMakeABooking.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMakeABooking.setBounds(306, 28, 198, 33);
		frame.getContentPane().add(lblMakeABooking);
		
		// Start and End dropdowns
		JLabel lblStart = new JLabel("Start:");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStart.setBounds(50, 613, 63, 33);
		frame.getContentPane().add(lblStart);
		
		JComboBox<String> startComboBox = new JComboBox<>();
		startComboBox.setBounds(113, 607, 95, 39);
		frame.getContentPane().add(startComboBox);
		
		JLabel lblEnd = new JLabel("End:");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnd.setBounds(242, 613, 63, 33);
		frame.getContentPane().add(lblEnd);
		
		JComboBox<String> endComboBox = new JComboBox<>();
		endComboBox.setBounds(296, 607, 95, 39);
		frame.getContentPane().add(endComboBox);
		
		// Day dropdown
		JLabel lblDay = new JLabel("Day:");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDay.setBounds(50, 544, 95, 33);
		frame.getContentPane().add(lblDay);
		
		JComboBox<String> dayComboBox = new JComboBox<>();
		dayComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dayComboBox.setBounds(113, 541, 184, 39);
		frame.getContentPane().add(dayComboBox);
		
		// Availability
		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAvailability.setBounds(135, 140, 155, 33);
		frame.getContentPane().add(lblAvailability);
		
		JList<Space> availabilityList = new JList<>();
		availabilityList.setFont(new Font("Times New Roman", Font.BOLD, 18));
		availabilityList.setBounds(26, 182, 345, 275);
		DefaultListModel<Space> spaceModel = new DefaultListModel<>();
		for (Space space: Test.scheduler.getAvailableSpaces()) { spaceModel.addElement(space); }
		availabilityList.setModel(spaceModel);
		availabilityList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent l) {
			    Space space = (Space) availabilityList.getSelectedValue();
			    Interval interval = space.getTime();
			    
			    startComboBox.setModel(new DefaultComboBoxModel<String>(
			    		Test.getTimes(interval.getStart(), interval.getEnd())
			    ));
			    endComboBox.setModel(new DefaultComboBoxModel<String>(
			    		Test.getTimes(interval.getStart(), interval.getEnd())
			    ));
		
			    LinkedList<String> selectedDays = interval.getDays();
			    String[] days = new String[selectedDays.size()];
				for (int i = 0; i < selectedDays.size(); i++)
					days[i] = selectedDays.get(i);
				dayComboBox.setModel(new DefaultComboBoxModel<String>(days));

			    frame.repaint();
			}
		});
		frame.getContentPane().add(availabilityList);
		
		// Location dropdown
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocation.setBounds(35, 81, 95, 33);
		frame.getContentPane().add(lblLocation);
		
		JComboBox<String> locationComboBox = new JComboBox<>();
		locationComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		locationComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] {"All", "UC", "Gym", "Science", "Education", "Library"}
		));
		locationComboBox.setBounds(133, 78, 184, 39);
		locationComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				spaceModel.clear();
				String location = locationComboBox.getSelectedItem().toString();
				if (location.equals("All")) {
					for (Space space: Test.scheduler.getAvailableSpaces()) {
						spaceModel.addElement(space);
					}
				}
				else {
					for (Space space: Test.scheduler.getAvailableSpaces()) {
			    	    if (space.getLocation().equals(location))
			    		    spaceModel.addElement(space);
			        }
				}
			    frame.repaint();
			}
		});
		frame.getContentPane().add(locationComboBox);
		
		// Slots list
		JLabel lblSlots = new JLabel("Slots (max. 5)");
		lblSlots.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSlots.setBounds(506, 140, 155, 33);
		frame.getContentPane().add(lblSlots);
		
		JList<Interval> slotList = new JList<>();
		slotList.setFont(new Font("Times New Roman", Font.BOLD, 18));
		slotList.setBounds(408, 182, 345, 275);
		DefaultListModel<Interval> slot_model = new DefaultListModel<>();
		slotList.setModel(slot_model);
		frame.getContentPane().add(slotList);
		
		// Name checkbox
		JLabel lblEnterYourName = new JLabel("Enter your name:");
		lblEnterYourName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterYourName.setBounds(50, 480, 158, 33);
		frame.getContentPane().add(lblEnterYourName);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(219, 480, 172, 33);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		// Add button
		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!availabilityList.isSelectionEmpty()) {
					Space space = availabilityList.getSelectedValue();
                    String start = startComboBox.getSelectedItem().toString();
                    String end = endComboBox.getSelectedItem().toString();
                    String day = dayComboBox.getSelectedItem().toString();
                    
                    if (Integer.parseInt(start.substring(0, 1)) >= Integer.parseInt(end.substring(0, 1))) {
                    	JOptionPane.showMessageDialog(frame,
                        "Start time cannot be more than or equal to end time!",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                    	Interval conflict = resolveConflict(start, end, day, space);
                        if (conflict == null) {
                        	Interval interval = new Interval(start, end);
                            LinkedList<String> singleDayList = new LinkedList<>();
                            singleDayList.add(day);
                            interval.setDays(singleDayList);
                            
                            if (slot_model.getSize() == 5) {
                            	JOptionPane.showMessageDialog(frame,
                                "You can only add a maximum of 5 slots per booking!",
                                "Inane error", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                slot_model.addElement(interval);
                            }
                        }
                        else {
                        	JOptionPane.showMessageDialog(frame,
                            "Your booking conflicts with this time slot: ".concat(conflict.toString()),
                            "Inane error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
				}
				else;
				frame.repaint();
			}
		});
		addButton.setBounds(115, 679, 171, 41);
		frame.getContentPane().add(addButton);
		
		// Submit Request button
		JButton btnSubmitRequest = new JButton("Submit Request");
		btnSubmitRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSubmitRequest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                LinkedList<Interval> slots = new LinkedList<>();
          
                for (int i=0; i < slot_model.size(); i++) {
                	slots.add(slot_model.get(i)); 
                }
                
                if (!availabilityList.isSelectionEmpty()) {
                	String user = nameTextField.getText();
                	if (user.length() == 0 ) {
                		JOptionPane.showMessageDialog(frame,
                        "Please enter your name!",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
                	}
                	else {
                		Space space = (Space) availabilityList.getSelectedValue();
                    	space.setUser(user);
                        Booking booking = new Booking(user, space);
                        booking.setSlots(slots);
                    
                        Test.scheduler.getPendingBookings().add(booking);
                        
                        if (!Test.scheduler.userExists(user))
                            Test.scheduler.getUsers().add(user);
                        
                        frame.dispose();
                        UserPanel.main(null);
                	}
                }
			}
		});
		btnSubmitRequest.setBounds(490, 679, 171, 41);
		frame.getContentPane().add(btnSubmitRequest);
		
		// Back button
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backButton.setBounds(617, 21, 136, 41);
		backButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserPanel.main(null);
			}
		});
		frame.getContentPane().add(backButton);
	}
	
	private Interval resolveConflict(String start, String end, String day, Space space) {
		LinkedList<Interval> blocked = space.getBlocked();
		
		if (blocked.isEmpty())
			return null;
		
		for (Interval interval: blocked) {
			if (interval.getDays().getFirst().equals(day)) {
				if (interval.getStart().equals(start))
					return interval;
				if (interval.getEnd().equals(end))
					return interval;
			}
		}
		
		return null;
	}
}
