package Graphical;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

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

		serviceComboBox1.addItem("No Service");
		serviceComboBox1.addItem("Hygiene");
		serviceComboBox1.addItem("Check-up");
		serviceComboBox1.addItem("Amalgam filling");
		serviceComboBox1.setMaximumSize(new Dimension(200,30));
		
		serviceComboBox2.addItem("No Service");
		serviceComboBox2.addItem("Hygiene");
		serviceComboBox2.addItem("Check-up");
		serviceComboBox2.addItem("Amalgam filling");
		serviceComboBox2.setMaximumSize(new Dimension(200,30));
		
		serviceComboBox3.addItem("No Service");
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
				System.out.println(firstNameText.getText());
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
		
		class submit implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GenerateReceipt g = new GenerateReceipt(AfterInformationPanel.this);
				try {
					ArrayList<String> services = new ArrayList<String>();
					String fname = firstNameText.getText();
					String sname = lastNameText.getText();
					String service1  = serviceComboBox1.getSelectedItem().toString();
					String service2  = serviceComboBox2.getSelectedItem().toString();
					String service3  = serviceComboBox3.getSelectedItem().toString();
					System.out.println(service3);
					int price = 0;
					if(!service1.equals("No Service")){
						services.add(service1);
						
					}
					if(!service2.equals("No Service")){
						services.add(service2);
						
					}
					if(!service3.equals("No Service")){
						services.add(service3);
						
					}
					if(service1.equals("Hygiene")|| service2.equals("Hygiene")|| service3.equals("Hygiene")){
						 price +=45;	
					}
					if(service1.equals("Check-up")|| service2.equals("Check-up")|| service3.equals("Check-up")){
						 price +=45;	
					}
					if(service1.equals("Amalgam filling")|| service2.equals("Amalgam filling")|| service3.equals("Amalgam filling")){
						 price +=90;	
					}
					String cost = Integer.toString(price);
					g.writeFile(cost, fname,sname, services);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		submit.addActionListener(new submit());
	
	
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
