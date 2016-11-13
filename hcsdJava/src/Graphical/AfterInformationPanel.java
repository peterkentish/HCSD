package Graphical;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AfterInformationPanel extends JPanel {
	
	
	private JComboBox<String> serviceComboBox1 = new JComboBox<String>();
	private JComboBox<String> serviceComboBox2 = new JComboBox<String>();
	private JComboBox<String> serviceComboBox3 = new JComboBox<String>();
	private JTextField firstNameText,lastNameText,commentText,streetAddressText,postcodeText,contactNoText;
	
	String firstName;
	private String lastName;
	private String birthDate;
	private String steetAddress;
	private String postcode;
	private String contactNo;
	public AfterInformationPanel(){
		JPanel panel = new JPanel();
		firstNameText= new JTextField(20);
		lastNameText= new JTextField(20);
		JRadioButton yesButton   = new JRadioButton("Seen Patient", true);
		JRadioButton noButton   = new JRadioButton("Not seen Patient");
		commentText= new JTextField(20);

		
		Dimension textDim = new Dimension(600,40); 
		firstNameText.setMaximumSize(textDim);
		lastNameText.setMaximumSize(textDim);
		commentText.setMaximumSize(textDim);
		
	    ButtonGroup bgroup = new ButtonGroup();
    	bgroup.add(yesButton);
        bgroup.add(noButton);

		
		this.setLayout(new BoxLayout(this,1));

		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Surname: ");
		JLabel commentLabel = new JLabel("Comments: ");
		JLabel serviceLabel1 = new JLabel("Service: ");
		JLabel serviceLabel2 = new JLabel("Service: ");
		JLabel serviceLabel3 = new JLabel("Service: ");


		serviceComboBox1.addItem("Hygiene");
		serviceComboBox1.addItem("Check-up");
		serviceComboBox1.addItem("Amalgam filling");
		serviceComboBox1.setMaximumSize(new Dimension(200,30));
		
		serviceComboBox2.addItem("Hygiene");
		serviceComboBox2.addItem("Check-up");
		serviceComboBox2.addItem("Amalgam filling");
		serviceComboBox2.setMaximumSize(new Dimension(200,30));
		
		serviceComboBox3.addItem("Hygiene");
		serviceComboBox3.addItem("Check-up");
		serviceComboBox3.addItem("Amalgam filling");
		serviceComboBox3.setMaximumSize(new Dimension(200,30));

					
				
			



		this.add(firstNameLabel);
		this.add(firstNameText);
		this.add(lastNameLabel);
		this.add(lastNameText);
		this.add(yesButton);
		this.add(noButton);
		this.add(commentLabel);
		this.add(commentText);
		this.add(serviceLabel1);
		this.add(serviceComboBox1);
		this.add(serviceLabel2);
		this.add(serviceComboBox2);
		serviceLabel2.setVisible(false);
		serviceComboBox2.setVisible(false);
		JButton button1 = new JButton("Add service");
		this.add(button1);
		JButton button2 = new JButton("Add service");
		this.add(button2);
		button2.setVisible(false);
		
		this.add(serviceLabel3);
		this.add(serviceComboBox3);
		serviceLabel3.setVisible(false);
		serviceComboBox3.setVisible(false);


		
		class button1 implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				serviceLabel2.setVisible(true);
				serviceComboBox2.setVisible(true);
				button1.setVisible(false);
				button2.setVisible(true);

				
				
		
			}
		}
		
		button1.addActionListener(new button1());
		
		
		class button2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				serviceLabel3.setVisible(true);
				serviceComboBox3.setVisible(true);
				button2.setVisible(false);


				
				
		
			}
		}
		
		button2.addActionListener(new button2());
		JButton submit = new JButton("ADD PATIENT");
		this.add(submit);

	}
	


	
	public void actionPerformed(ActionEvent e) {
		firstName = getFirstNameText();
	}
	public String getserviceComboBox() {
		return (String) serviceComboBox1.getSelectedItem();
	}
	public String getFirstNameText() {
		return firstNameText.getText();
	}
	public String getLastNameText() {
		return lastNameText.getText();
	}
	
	

}
