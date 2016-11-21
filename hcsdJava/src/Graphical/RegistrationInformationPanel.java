package Graphical;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Database;
import Database.Patient;

/*
 * This class takes all inputs from window and inserts patient record into the database 
 */
public class RegistrationInformationPanel extends JPanel implements ActionListener {
	// initiate variables
	private JComboBox<String> titleComboBox = new JComboBox<String>();
	private JComboBox<String> healthcareComboBox = new JComboBox<String>();
	private JTextField firstNameText,lastNameText,birthDateText,streetAddressText,postcodeText,contactNoText, amountPaidText;
	
	private int checks, hyg, repa;
	
	String title;
	String firstName;
	private String lastName;
	private String birthDate;
	private String streetAddress;
	private String postcode;
	private String contactNo;
	private String healthCare;
	private BigDecimal amountPaid;
	JLabel success = new JLabel("Registration Successful");
	JLabel failure = new JLabel ("Registration Failed, please check user details");
	public RegistrationInformationPanel(){
		firstNameText= new JTextField(20);
		lastNameText= new JTextField(20);
		birthDateText= new JTextField(20);
		streetAddressText= new JTextField(20);
		postcodeText= new JTextField(20);
		contactNoText= new JTextField(20);
		
		
		Dimension textDim = new Dimension(600,40); 
		
		firstNameText.setMaximumSize(textDim);
		lastNameText.setMaximumSize(textDim);
		birthDateText.setMaximumSize(textDim);
		streetAddressText.setMaximumSize(textDim);
		postcodeText.setMaximumSize(textDim);
		contactNoText.setMaximumSize(textDim);
		
		
		this.setLayout(new BoxLayout(this,1));
		JLabel titleLabel = new JLabel("Title: ");
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Surname: ");
		JLabel birthDateLabel = new JLabel("Date of Birth: ");
		JLabel streetAddressLabel = new JLabel("First line of address: ");
		JLabel postcodeLabel = new JLabel("Postcode: ");
		JLabel contactNoLabel = new JLabel("ContactNo: ");
		JLabel healthcareLabel = new JLabel("Healthcare plan: ");
		//displaying items on window
		
		titleComboBox.addItem("Mr");
		titleComboBox.addItem("Mrs");
		titleComboBox.addItem("Miss");
		titleComboBox.addItem("Dr");
		titleComboBox.setMaximumSize(new Dimension(200,30));
		
		healthcareComboBox.addItem("No Plan");
		healthcareComboBox.addItem("NHS Free Plan");
		healthcareComboBox.addItem("Maintenance Plan");
		healthcareComboBox.addItem("Oral Health Plan ");
		healthcareComboBox.addItem("Dental Repair Plan");
		healthcareComboBox.setMaximumSize(new Dimension(200,30));
		this.add(titleLabel);
		this.add(titleComboBox);
		this.add(firstNameLabel);
		this.add(firstNameText);
		this.add(lastNameLabel);
		this.add(lastNameText);
		this.add(birthDateLabel);
		this.add(birthDateText);
		this.add(streetAddressLabel);
		this.add(streetAddressText);
		this.add(postcodeLabel);
		this.add(postcodeText);
		this.add(contactNoLabel);
		this.add(contactNoText);
		this.add(healthcareLabel);
		this.add(healthcareComboBox);
		JButton submit = new JButton("ADD PATIENT");
		this.add(submit);
		submit.addActionListener(this);
		submit.setMaximumSize(new Dimension(200,40));
		this.add(success);
		this.add(failure);
		
		success.setVisible(false);
		failure.setVisible(false);
		
	}
	@Override
	//inserting items to database
	public void actionPerformed(ActionEvent e) {
		Database db = new Database();
		title= this.getTitleComboBox();
		firstName = this.getFirstNameText();
		lastName=this.getLastNameText();
		birthDate = this.getBirthDateText();
		streetAddress = this.getStreetAddressText();
		postcode = this.getPostcodeText();
		contactNo = this.getContactNoText();
		healthCare = this.getHealthcareComboBox();
		amountPaid = this.getAmountPaid();
		checks = this.getChecks();
		hyg = this.getHyg();
		repa = this.getRepa();
		int startCount = db.getPatientCount();
		System.out.println(startCount);
		db.addPatient(new Patient(title, firstName, lastName, birthDate, streetAddress, postcode, contactNo,healthCare,checks,hyg,repa));
		if (db.getPatientCount()== startCount+1){
			this.getSuccess().setVisible(true);
		}else 
			this.getFailure().setVisible(true);
	}
	
	public String getTitleComboBox() {
		return (String) titleComboBox.getSelectedItem();
	}
	public String getHealthcareComboBox() {
		return (String) healthcareComboBox.getSelectedItem();
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
	public String getStreetAddressText() {
		return streetAddressText.getText();
	}
	public String getPostcodeText() {
		return postcodeText.getText();
	}
	
	public String getContactNoText() {
		return contactNoText.getText();
	}
	
	public int getChecks(){
		if (!getHealthcareComboBox().equals("No Plan")){
			return 2;
			
		}else{ 
			return 0;
		}	
	}
	
	public int getHyg(){
		if (getHealthcareComboBox().equals("No Plan")){
			return 0;
			
		}else if (getHealthcareComboBox().equals("NHS Free Plan") || getHealthcareComboBox().equals("Maintenance Plan")){ 
			return 2;
		}	else{
			return 4;
		}
	}
	
	public int getRepa(){
		if (!getHealthcareComboBox().equals("Dental Repair Plan")){
			return 0;
			
		}else{ 
			return 2;
		}	
	}
	
	
	
	public JLabel getSuccess() {
		return success;
	}
	public JLabel getFailure() {
		return failure;
	}
	public void setSuccess(JLabel success) {
		this.success = success;
	}
	public void setFailure(JLabel failure) {
		this.failure = failure;
	}
	public BigDecimal getAmountPaid() {
		BigDecimal bd = null;
		if (getHealthcareComboBox().equals("NHS Free Plan")){
			 bd = new BigDecimal(600);
			
		}else if(getHealthcareComboBox().equals("Maintenance Plan")){
			bd = new BigDecimal(180);
		}else if(getHealthcareComboBox().equals("Dental Repair Plan")){
			 bd = new BigDecimal(400);
		}else if(getHealthcareComboBox().equals("Oral Health Plan ")){
			 bd = new BigDecimal(400);
		}
		
		return bd;
	}

}
