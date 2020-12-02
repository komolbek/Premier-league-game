import java.util.ArrayList;
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 	Custom, Reusable MyJTable class
 */
public class MyTableView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Table property
	JTable table;
	
	 // Constructor 
	public MyTableView(ArrayList<FootballClub> footballClubs) {
		this.setTitle("Premier League");
		
		// Column Names 
		String[] columnNames = {
				"Club", 
				"Location", 
				"Wins", 
				"Draws", 
				"Defeats",
				"Goals scored",
				"Goals received",
				"Played matches",
				"Points"
				};
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		for (int i = 0; i < footballClubs.size(); i++){
			   String name = footballClubs.get(i).getName();
			   String location = footballClubs.get(i).getLocation();
			   int wins = footballClubs.get(i).getWins();
			   int draws = footballClubs.get(i).getDraws();
			   int defeats = footballClubs.get(i).getDefeats();
			   int goalScored = footballClubs.get(i).getScoredGoals();
			   int goalReceived = footballClubs.get(i).getReceivedGoals();
			   int totalMatches = footballClubs.get(i).getPlayedMatches();
			   int points = footballClubs.get(i).getPoints();

			   Object[] data = { name, location, wins, draws, defeats, goalScored, goalReceived, totalMatches, points };

			   tableModel.addRow(data);
			}
		
		// Initialising the JTable
		table = new JTable(tableModel);
		table.setBounds(30, 40, 1200, 600); 
		
		// adding JTable table to JScrollPane 
        JScrollPane scrollPanel = new JScrollPane(table); 
        this.add(scrollPanel); 
        
        // Self (JFrame) size
        this.setSize(1200, 700);
        
        // Self (JFrame) Visible = true 
        this.setVisible(true); 
	}
}
