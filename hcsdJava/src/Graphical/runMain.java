package Graphical;
import Database.Database;
import javax.swing.JFrame;

public class RunMain {
	static Database db = new Database();
	public static void main(String[] args){
		
		MainMenu run = new MainMenu();
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		run.setSize(1000,1000);
		run.setVisible(true);
		System.out.println(db.selectPatient("*","patient_table","patient_first_name='Daniel'"));

	}
	


}
