package Scheduler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JCheckBox;

public class AddSpaces {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSpaces window = new AddSpaces();
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
	public AddSpaces() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 512, 518);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        Test.writeObjectToFile(Test.scheduler);
		        frame.dispose();
		    }
		});
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddASpace = new JLabel("Add A Space");
		lblAddASpace.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblAddASpace.setBounds(159, 0, 208, 36);
		frame.getContentPane().add(lblAddASpace);
		
		// Location drop-down
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(41, 48, 56, 16);
		frame.getContentPane().add(lblLocation);
		
		JComboBox<String> locationComboBox = new JComboBox<>();
		locationComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] {"UC", "Gym", "Science", "Education", "Library"}
		));
		locationComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		locationComboBox.setBounds(120, 48, 103, 22);
		frame.getContentPane().add(locationComboBox);
		
		// Semester radiobuttons
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(41, 100, 56, 16);
		frame.getContentPane().add(lblSemester);
		
		JRadioButton rdbtnSpring = new JRadioButton("Spring");
		rdbtnSpring.setBounds(109, 96, 127, 25);
		rdbtnSpring.setSelected(true);
		frame.getContentPane().add(rdbtnSpring);
		
		JRadioButton rdbtnWinter = new JRadioButton("Winter");
		rdbtnWinter.setBounds(240, 96, 127, 25);
		frame.getContentPane().add(rdbtnWinter);
		
		JRadioButton rdbtnFall = new JRadioButton("Fall");
		rdbtnFall.setBounds(371, 96, 127, 25);
		frame.getContentPane().add(rdbtnFall);
		
		ButtonGroup rgroup = new ButtonGroup();
		rgroup.add(rdbtnFall);
		rgroup.add(rdbtnWinter);
		rgroup.add(rdbtnSpring);
		
		// Start and End comboboxes
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(41, 156, 56, 16);
		frame.getContentPane().add(lblStart);
		
		JComboBox<String> startComboBox = new JComboBox<String>();
		startComboBox.setModel(new DefaultComboBoxModel<String>(Test.times));
		startComboBox.setBounds(109, 153, 57, 22);
		frame.getContentPane().add(startComboBox);  
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setBounds(178, 156, 56, 16);
		frame.getContentPane().add(lblEnd);
		
		JComboBox<String> endComboBox = new JComboBox<>();
		endComboBox.setModel(new DefaultComboBoxModel<String>(Test.times));
		endComboBox.setBounds(209, 153, 57, 22);
		frame.getContentPane().add(endComboBox);
		
		// Day checkboxes
		JLabel dayLabel = new JLabel("Day");
		dayLabel.setBounds(41, 207, 56, 16);
		frame.getContentPane().add(dayLabel);
		
		JCheckBox chckbxSunday = new JCheckBox("Sunday");
		chckbxSunday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxSunday.setBounds(41, 236, 112, 22);
		frame.getContentPane().add(chckbxSunday);
		
		JCheckBox chckbxMonday = new JCheckBox("Monday");
		chckbxMonday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxMonday.setBounds(41, 270, 125, 22);
		frame.getContentPane().add(chckbxMonday);
		
		JCheckBox chckbxTuesday = new JCheckBox("Tuesday");
		chckbxTuesday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxTuesday.setBounds(41, 304, 125, 22);
		frame.getContentPane().add(chckbxTuesday);
		
		JCheckBox chckbxWednesday = new JCheckBox("Wednesday");
		chckbxWednesday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxWednesday.setBounds(41, 337, 147, 22);
		frame.getContentPane().add(chckbxWednesday);
		
		JCheckBox chckbxThursday = new JCheckBox("Thursday");
		chckbxThursday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxThursday.setBounds(240, 236, 147, 22);
		frame.getContentPane().add(chckbxThursday);
		
		JCheckBox chckbxFriday = new JCheckBox("Friday");
		chckbxFriday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxFriday.setBounds(240, 270, 147, 22);
		frame.getContentPane().add(chckbxFriday);
		
		JCheckBox chckbxSaturday = new JCheckBox("Saturday");
		chckbxSaturday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxSaturday.setBounds(240, 304, 147, 22);
		frame.getContentPane().add(chckbxSaturday);
		
		// Submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(120, 378, 97, 25);
		frame.getContentPane().add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String location = locationComboBox.getSelectedItem().toString();
				String start = startComboBox.getSelectedItem().toString();
				String end = endComboBox.getSelectedItem().toString();
				
				String semester = "";
				if (rdbtnWinter.isSelected()) { semester = "Winter"; }
				else if (rdbtnFall.isSelected()) { semester = "Fall"; }
				else if (rdbtnSpring.isSelected()){ semester = "Spring"; }
				
				Interval time = new Interval(start, end);
				if (chckbxSunday.isSelected())
					time.addDay("Sunday");
				if (chckbxMonday.isSelected())
					time.addDay("Monday");
				if (chckbxTuesday.isSelected())
					time.addDay("Tuesday");
				if (chckbxWednesday.isSelected())
					time.addDay("Wednesday");
				if (chckbxThursday.isSelected())
					time.addDay("Thursday");
				if (chckbxFriday.isSelected())
					time.addDay("Friday");
				if (chckbxSaturday.isSelected())
					time.addDay("Saturday");
				
				String militaryStart = Test.convertToMilitaryTime(start).substring(0, 2);
				String militaryEnd = Test.convertToMilitaryTime(end).substring(0, 2);
				if (Integer.parseInt(militaryStart) >= Integer.parseInt(militaryEnd)) {
                	JOptionPane.showMessageDialog(frame,
                    "Start time cannot be more than or equal to end time!",
                    "Inane error", JOptionPane.ERROR_MESSAGE);
                }
				else if (time.getDays().size() == 0) {
					JOptionPane.showMessageDialog(frame,
		            "Please select a day!",
		            "Inane error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Space space = new Space(location, time, semester);
					Test.scheduler.getAvailableSpaces().add(space);
					
					AdminPanel.main(null);
					frame.dispose();
				}
			}
		});
		
		// Cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(273, 378, 97, 25);
		cancelButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminPanel.main(null);
			}
		});
		frame.getContentPane().add(cancelButton);
	}
}
