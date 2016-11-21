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

	public GenerateReceipt(AfterInformationPanel pan) {
		this.ai = pan;
	}

	public void writeFile(String price, String fname, String sname, ArrayList<String> services,String comment) throws IOException {
		BufferedWriter writer = null;
		try {
			// create a temporary file
			String name = fname + " " + sname;
			File logFile = new File("src/Graphical/Reciept.txt");
			Database db = new Database();
			AfterInformationPanel ai = new AfterInformationPanel();

			// This will output the full path where the file will be written to
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(name);
			writer.newLine();
			writer.newLine();
			writer.write("Comments: ");
			writer.write(comment);
			writer.newLine();
			writer.newLine();
			writer.write("Your services:");
			writer.newLine();
			// print all patient services
			for (int i = 0; i < services.size(); i++) {
				writer.write(services.get(i));
				writer.newLine();
			}
			// display the price of all the services
			writer.newLine();
			writer.write("Service Price: ");
			writer.write("\u00a3" + price);
			writer.newLine();
			writer.write("Your Price: ");
			
			//get the patients details to see how many check-ups,hygienists and repair  
			Patient pat = ((Patient) db.selectPatient("*", "patients",
					"first_name='" + fname + "'and last_name='" + sname + "'"));
			double cost = Double.parseDouble(price);
			//obtain amount of checkups left on plan and decrement if service used
			int checks = pat.getChecks();
			System.out.println(checks);
			//decrease price by cost of each service available left from health care plan
			for (int i = 0; i < services.size(); i++) {
				System.out.println(services.get(i));
				if (checks > 0) {
					if (services.get(i).equals("Check-up")) {
						cost -= 45;
						checks--;
					}
				}
			}
			//obtain amount of hygienes left on plan and decrement if service used
			int hyg = pat.getHyg();
			for (int x = 0; x < services.size(); x++) {
				if (hyg > 0) {
					if (services.get(x).equals("Hygiene")) {
						cost -= 45;
						hyg--;

					}

				}
			}
			//obtain amount of repairs left on plan and decrement if service used
			int repa = pat.getRepa();
			for (int x = 0; x < services.size(); x++) {
				if (repa > 0) {
					if (services.get(x).equals("Amalgam filling")) {
						cost -= 90;
						repa--;
					}

				}
			}

			for (int x = 0; x < services.size(); x++) {
				if (repa > 0) {
					if (services.get(x).equals(("White composite resin filling"))) {
						cost -= 150;
						repa--;
					}

				}
			}
			for (int x = 0; x < services.size(); x++) {
				if (repa > 0) {
					if (services.get(x).equals(("Gold crown"))) {
						cost -= 500;
						repa--;
					}

				}
			}

			writer.write("\u00a3" + cost + "0");
			// update services left on database
			db.updatePatient("patients",
					"check_up ='" + checks + "', hygiene_visit ='" + hyg + "', repair ='" + repa + "'",
					"first_name='" + fname + "'and last_name='" + sname + "'");
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
