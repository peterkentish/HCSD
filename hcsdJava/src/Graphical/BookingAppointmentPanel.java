package Graphical;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Database;
import Database.Patient;

public class BookingAppointmentPanel extends JPanel implements ActionListener {
	ArrayList<String> timesString = new ArrayList<String>();
	List<java.sql.Time> times = new ArrayList<>();
	private JTextField firstNameText,lastNameText,birthDateText,postcodeText;
	JComboBox<java.sql.Time> timesComboBox = new JComboBox<>();
	String firstName;
	private String lastName;
	private String birthDate;
	private String staffMember;
	private String postcode;
	private Time appointmentStart,appointmentEnd;
	private Integer patientID =0; 
	private JLabel idLabel = new JLabel(patientID.toString());
	
	public BookingAppointmentPanel(){
		populateTimesOfDay();
		firstNameText= new JTextField(20);
		lastNameText= new JTextField(20);
		birthDateText= new JTextField(20);
		postcodeText = new JTextField(20);
		
		for (int i=0;i<times.size();i++){
			timesComboBox.addItem(times.get(i));
		}

		Dimension textDim = new Dimension(600,40); 
		firstNameText.setMaximumSize(textDim);
		lastNameText.setMaximumSize(textDim);
		birthDateText.setMaximumSize(textDim);
		postcodeText.setMaximumSize(textDim);
		timesComboBox.setMaximumSize(textDim);
		
		this.setLayout(new BoxLayout(this,1));
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Surname: ");
		JLabel birthDateLabel = new JLabel("Date of Birth: ");
		JLabel postcodeLabel = new JLabel("Postcode: ");
		idLabel = new JLabel(patientID.toString());
		idLabel.setVisible(false);
		idLabel.setFont(new Font("Arial",0,25));
	
		this.add(firstNameLabel);
		this.add(firstNameText);
		this.add(lastNameLabel);
		this.add(lastNameText);
		this.add(birthDateLabel);
		this.add(birthDateText);
		this.add(postcodeLabel);
		this.add(postcodeText);
		JButton submit = new JButton("GET PATIENT ID");
		this.add(submit);
		submit.addActionListener(this);
		submit.setMaximumSize(new Dimension(200,40));
		this.add(idLabel);
		
		this.add(timesComboBox);
		


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
		Database db = new Database();
		Patient pat = (Patient) db.selectPatient("*", "patients", "first_name='"+getFirstNameText()+"' and last_name='"+getLastNameText()+"' and birth_date='"
				+getBirthDateText()+"' and postcode ='"+getPostcodeText()+"'");
		idLabel.setText(pat.getPatientID()+"");
		idLabel.setVisible(true);
		
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
	public String getPostcodeText() {
		return postcodeText.getText();
	}
	
}
