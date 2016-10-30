package Graphical;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Database.Database;

public class GuiQuery extends JFrame {
	
	private JPanel contentPane;
	String[] query = { "First Name", "Surname"};
	
	
	
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			
				try {
					GuiQuery frame = new GuiQuery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	
	
	
	public GuiQuery() {
		setTitle("Query");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel queryLabel = new JLabel("Enter Query");
		queryLabel.setVerticalAlignment(SwingConstants.TOP);
		queryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(queryLabel);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox queryList = new JComboBox(query);
		panel.add(queryList);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setTabSize(20);
		textArea.setRows(1);
		panel.add(textArea);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					Database data = new Database();
					JTextArea textArea_1 = new JTextArea();
					contentPane.add(textArea_1, BorderLayout.CENTER);
					String typeQuery = queryList.getSelectedItem().toString();
					String whatQuery = textArea.getText().toUpperCase();
					if(typeQuery.equals("First Name")){
						textArea_1.setText(data.selectPatient("*", "patients", "first_name='"+ whatQuery +"'").toString());
					}else{
						textArea_1.setText(data.selectPatient("*", "patients", "last_name='"+ whatQuery +"'").toString());
					}
					
					//textArea_1.setText(data.selectPatient("*", "patient_table").toString());
					

					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(searchButton);


		

	}

}
	
	
	


