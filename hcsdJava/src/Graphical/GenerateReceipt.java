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
	
	public void openFile(){
		try{
			x = new Formatter("Reciept.txt");
			
		}
		catch (Exception e){
			System.out.println("Error");
		}
		
		
	}
	
	public void addReciept(){
		x.format("%s", "Hello");
	}
	
	public void closeFile(){
		x.close();
	}
	
	public void writeFile(String price, String name, ArrayList<String> services) throws IOException{
        BufferedWriter writer = null;
        try {
            //create a temporary file
            
            File logFile = new File("src/Graphical/Reciept.txt");
            Database db = new Database();
            //AfterInformationPanel ai = new AfterInformationPanel();

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
            
            
            Patient pat = ((Patient) db.selectPatient("*", "patients", "first_name='Daniel' and last_name='Kintish'"));
            double c1 = pat.getAmountPaid().doubleValue();
           double cost = Double.parseDouble(price);
            if(c1-cost< 0){
            	writer.write("\u00a3"+price);
            }else{
            writer.write("\u00a3"+"0");
            }
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


