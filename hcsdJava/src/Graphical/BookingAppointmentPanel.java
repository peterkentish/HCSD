package Graphical;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingAppointmentPanel extends JPanel implements ActionListener {
	ArrayList<String> timesString = new ArrayList<String>();
	List<java.sql.Time> times = new ArrayList<>();
	private JTextField firstNameText,lastNameText,birthDateText;
	
	String firstName;
	private String lastName;
	private String birthDate;
	private String staffMember;
	private Time appointmentStart,appointmentEnd;
	
	public BookingAppointmentPanel(){
		populateTimesOfDay();
		firstNameText= new JTextField(20);
		lastNameText= new JTextField(20);
		birthDateText= new JTextField(20);
		
		
		Dimension textDim = new Dimension(600,40); 
		firstNameText.setMaximumSize(textDim);
		lastNameText.setMaximumSize(textDim);
		birthDateText.setMaximumSize(textDim);

		
		this.setLayout(new BoxLayout(this,1));
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Surname: ");
		JLabel birthDateLabel = new JLabel("Date of Birth: ");

	
		this.add(firstNameLabel);
		this.add(firstNameText);
		this.add(lastNameLabel);
		this.add(lastNameText);
		this.add(birthDateLabel);
		this.add(birthDateText);


	}
	public void populateTimesOfDay(){
		java.sql.Time startTime = new java.sql.Time(9, 0, 0);
		java.sql.Time endTime = new java.sql.Time(17, 0, 0);

		times.add(startTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		while (cal.getTime().before(endTime)) {
		    cal.add(Calendar.MINUTE, 20);
		    times.add(new java.sql.Time(cal.getTimeInMillis()));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for (java.sql.Time time : times) {
		    timesString.add(sdf.format(time));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		firstName = getFirstNameText();
	}
	
	public String getFirstNameText() {
		return firstNameText.getText();
	}
	public String getLastNameText() {
		return lastNameText.getText();
	}
	public String getBirthDateText() {
		return birthDateText.getText();
	}
	
}
