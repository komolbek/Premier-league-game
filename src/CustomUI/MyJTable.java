package CustomUI;

//Packages to import 
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;

public class MyJTable extends JFrame {
	
	// Table property
	JTable table;
	
	 // Constructor 
	public MyJTable() {
		this.setTitle("Premier League");
		
		String[][] data = {
				{ "Chelsea", "0", "0" ,"0", "0", "0", "0", "0" },
				{ "Barca  ", "0", "0", "0", "0", "0", "0", "0" }
		};
		
		// Column Names 
		String[] columnNames = {
				"Club", 
				"Matches Played", 
				"Wins", 
				"Defeats", 
				"Loses", 
				"Goals scored", 
				"Goals received", 
				"Points"
				};
		
		// Initialising the JTable
		table = new JTable(data, columnNames);
		table.setBounds(30, 40, 200, 300); 
		
		// adding JTable table to JScrollPane 
        JScrollPane scrollPanel = new JScrollPane(table); 
        this.add(scrollPanel); 
        // Self (JFrame) size
        this.setSize(500, 200);
        // Self (JFrame) Visible = true 
        this.setVisible(true); 
	}

}
