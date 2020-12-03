import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 	Custom, Reusable MyJTable class
 */
public class MyTableView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<FootballClub> footballClubs;
	
	private JTable table;
	private DefaultTableModel tableModel;
	private Container mainContainer;
	private JTextField searchTextField;
	
	private enum SortBy { BY_POINTS, BY_GOALS, BY_WINS, BY_DATE_CREATED }
	
	/**
	  * @LABELS
	  */
	private JLabel sortByLabel;
	private JLabel generateLabel;
	private JLabel searchLabel;
	
	/**
	  * @BUTTONS
	  */
	private MyButton sortByPointsButton;
	private MyButton sortByGoalSButton;
	private MyButton sortByWinsButton;
	private MyButton generateRandomGameButton;
	private MyButton searchButton;
	
	 /**
	  * @CONSTRUCTOR
	  */
	public MyTableView(ArrayList<FootballClub> footballClubs) {
		this.setFootballClubs(footballClubs);
		this.sortTableDataBy(SortBy.BY_POINTS);
		this.setupSelf();
		this.mainContainer = this.getContentPane();
        this.mainContainer.setLayout(new BorderLayout(15,15));
		this.setupTable();
		this.setupScrollPanel();
		this.setupLabelsAndTextFiels();
		this.setupButtons("Points", "Goal Scored", "Wins", "Generate", "Search");
		this.setupContainers();
	}
	
	/**
	 * DATA_LOGIC_METHODS
	 */
	
	private void setFootballClubs(ArrayList<FootballClub> footballClubs) {
		this.footballClubs = footballClubs;
	}
	
	private void sortTableDataBy(SortBy sortBy) {
		switch (sortBy) {
		case BY_POINTS: {
			Collections.sort(this.footballClubs, new Comparator<FootballClub>() {
		        public int compare(FootballClub p1, FootballClub p2) {
		           return Integer.valueOf(p2.getPoints()).compareTo(p1.getPoints());
		        }
			});
			
			if (this.table != null || this.tableModel != null) { this.updateTable(); }
			
			break;
		}
		case BY_DATE_CREATED: {
					
			
		}
		case BY_GOALS: {
			Collections.sort(this.footballClubs, new Comparator<FootballClub>() {
		        public int compare(FootballClub p1, FootballClub p2) {
		           return Integer.valueOf(p2.getScoredGoals()).compareTo(p1.getScoredGoals());
		        }
			});
			if (this.tableModel != null || this.tableModel != null) { this.updateTable(); }
			break;
		}
		case BY_WINS: {
			Collections.sort(this.footballClubs, new Comparator<FootballClub>() {
		        public int compare(FootballClub p1, FootballClub p2) {
		           return Integer.valueOf(p2.getWins()).compareTo(p1.getWins());
		        }
			});
			
			if (this.table != null || this.tableModel != null) { this.updateTable(); }
			
			break;
		}	
		default:
			throw new IllegalArgumentException("Unexpected value: " + sortBy);
		}
	}
	
	/**
	 * GUI_COMPONENTS_SETUP_METHODS
	 */
	
	private void setupSelf() {
		this.setTitle("Premier League");
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(113,247,159));
        this.setBounds(0,0,1200,600); 
        this.setVisible(true); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void updateTable() {
		if (tableModel.getRowCount() > 0) {
		    for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
		    	tableModel.removeRow(i);
		    }
		}
		
		for (int i = 0; i < this.footballClubs.size(); i++){
		   String name = this.footballClubs.get(i).getName();
		   int wins = this.footballClubs.get(i).getWins();
		   int draws = this.footballClubs.get(i).getDraws();
		   int defeats = this.footballClubs.get(i).getDefeats();
		   int goalScored = this.footballClubs.get(i).getScoredGoals();
		   int goalReceived = this.footballClubs.get(i).getReceivedGoals();
		   int totalMatches = this.footballClubs.get(i).getPlayedMatches();
		   int points = this.footballClubs.get(i).getPoints();

		   Object[] data = { name, wins, draws, defeats, goalScored, goalReceived, totalMatches, points };

		   tableModel.addRow(data);
		}
		
		table.repaint();
		table.revalidate();
		this.repaint();
		this.revalidate();
	}
	
	private void setupTable() {
		String[] columnNames = {"Club","Wins","Draws","Defeats","Goals scored","Goals received","Played matches","Points"};
		
		this.tableModel = new DefaultTableModel(columnNames, 0);
		
		for (int i = 0; i < this.footballClubs.size(); i++){
		   String name = this.footballClubs.get(i).getName();
		   int wins = this.footballClubs.get(i).getWins();
		   int draws = this.footballClubs.get(i).getDraws();
		   int defeats = this.footballClubs.get(i).getDefeats();
		   int goalScored = this.footballClubs.get(i).getScoredGoals();
		   int goalReceived = this.footballClubs.get(i).getReceivedGoals();
		   int totalMatches = this.footballClubs.get(i).getPlayedMatches();
		   int points = this.footballClubs.get(i).getPoints();

		   Object[] data = { name, wins, draws, defeats, goalScored, goalReceived, totalMatches, points };

		   tableModel.addRow(data);
		}
		
		table = new JTable(tableModel);
	}
	
	private void setupScrollPanel() {
        JScrollPane scrollPanel = new JScrollPane(table); 
        scrollPanel.setBounds(0, 0, 1200, 400); 
        this.mainContainer.add(scrollPanel);
	}
	
	private void setupLabelsAndTextFiels() {
		this.sortByLabel = new JLabel("Sort by:"); 
		this.generateLabel = new JLabel("Generate random game:");  
		this.searchLabel = new JLabel("Search played games by date:");  
		this.searchTextField = new JTextField();
		this.searchTextField.setColumns(20);
		this.searchTextField.setText("ex: 10 May 2020");
		this.searchTextField.setForeground(Color.GRAY);
		this.searchTextField.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
	        	searchTextField.setText("");
	        	searchTextField.setForeground(Color.BLACK);
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
	        	searchTextField.setForeground(Color.GRAY);
	        	searchTextField.setText("ex: 10 May 2020");
		    }
		    });
	}
	
	private void setupContainers() {
        JPanel topJPanel = new JPanel();
        topJPanel.setLayout(new FlowLayout(6));
        topJPanel.add(this.searchLabel);
        topJPanel.add(this.searchTextField);
        topJPanel.add(this.searchButton);
        this.mainContainer.add(topJPanel, BorderLayout.NORTH);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(6));
        bottomPanel.add(this.sortByLabel);
        bottomPanel.add(this.sortByPointsButton);
        bottomPanel.add(this.sortByGoalSButton);
        bottomPanel.add(this.sortByWinsButton);
        bottomPanel.add(this.generateLabel);
        bottomPanel.add(this.generateRandomGameButton);
        this.mainContainer.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void setupButtons(String byPointsTitle, String byGoalsTitle, String byWinsTitle, String generateTitle, String searchTitle) {
		this.sortByPointsButton = new MyButton(byPointsTitle, this);
		this.sortByGoalSButton = new MyButton(byGoalsTitle, this);
		this.sortByWinsButton = new MyButton(byWinsTitle, this);
		this.generateRandomGameButton = new MyButton(generateTitle, this);
		this.searchButton = new MyButton(searchTitle, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.sortByPointsButton) {
			this.sortTableDataBy(SortBy.BY_POINTS);
		} else if (e.getSource() == this.sortByGoalSButton) {
			this.sortTableDataBy(SortBy.BY_GOALS);
		} else if (e.getSource() == this.sortByWinsButton) {
			this.sortTableDataBy(SortBy.BY_WINS);
		}
	}
}
