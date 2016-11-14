package Graphical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.JFrame;

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
            writer.write("Price: ");
            
            writer.write("£"+price);
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


