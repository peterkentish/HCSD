package Graphical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.JFrame;

import Database.Database;
import Database.Patient;

public class GenerateReceipt {

	private Formatter x;
	public AfterInformationPanel ai = new AfterInformationPanel();

	public void openFile() {
		try {
			x = new Formatter("Reciept.txt");

		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	public void addReciept() {
		x.format("%s", "Hello");
	}

	public void closeFile() {
		x.close();
	}

	public GenerateReceipt(AfterInformationPanel pan){
		this.ai = pan;
	}

	public void writeFile(String price, String fname, String sname, ArrayList<String> services) throws IOException{
        BufferedWriter writer = null;
        try {
            //create a temporary file
            String name = fname +" "+ sname;
            File logFile = new File("src/Graphical/Reciept.txt");
            Database db = new Database();
            AfterInformationPanel ai = new AfterInformationPanel();

            // This will output the full path where the file will be written to...
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(name);
            writer.newLine();
            writer.newLine();
            writer.write("Your services:");
            writer.newLine();
            for(int i = 0; i< services.size();i++){
            	writer.write(services.get(i));
            	writer.newLine();
            }
            writer.newLine();
            writer.write("Service Price: ");
            writer.write("\u00a3"+price);
            writer.newLine();
            writer.write("Your Price: ");
            
            
            Patient pat = ((Patient) db.selectPatient("*", "patients", "first_name='"+fname +"'and last_name='"+sname+"'"));
           double cost = Double.parseDouble(price);
           int checks = pat.getChecks();
           System.out.println(checks);
           for(int i = 0; i< services.size();i++){
        	   if (checks > 0){
        		   if(services.get(i).equals("Check-Up")){
        			   cost -= 45;
        			   checks--;
        		   }
        	   }
           }
           int hyg = pat.getHyg();	   
           for(int x = 0; x< services.size();x++){
        	   if (hyg > 0){
        		   if(services.get(x).equals("Hygiene")){
        			   cost -= 45;
        			   hyg--;
        			   
        		   }
        	   
        	   }
           }
           int repa = pat.getRepa();
           for(int x = 0; x< services.size();x++){
        	   if (repa > 0){
        		   if(services.get(x).equals("Amalgam filling")){
        			   cost -= 90;
        			   repa--;
        		   }
        	   
        	   }
           }
           	
           
           
            
            writer.write("\u00a3"+cost);
          
            
            db.updatePatient("patients","check_up ='"+checks+"', hygiene_visit ='"+ hyg+ "', repair ='"+repa+"'" , "first_name='"+fname +"'and last_name='"+sname+"'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }

	}
}

		
	
