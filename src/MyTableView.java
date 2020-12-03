import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 	Custom, Reusable MyJTable class
 */
public class MyTableView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private Container mainContainer;
	private JTextField searchTextField;
	
	//Labels
	private JLabel sortByLabel;
	private JLabel generateLabel;
	private JLabel searchLabel;
	
	//Buttons
	private MyButton sortByPointsButton;
	private MyButton sortByGoalSButton;
	private MyButton sortByWinsButton;
	private MyButton generateRandomGameButton;
	private MyButton searchButton;
	
	 // Constructor 
	public MyTableView(ArrayList<FootballClub> footballClubs) {
		this.setupSelf();
		this.setupTable(footballClubs);
		this.setupLabelsAndTextFiels();
		this.setupButtons("Points", "Goal Scored", "Wins", "Generate", "Search");
		this.setupContainers();
		this.setupScrollPanel();
	}
	
	private void setupSelf() {
		this.setTitle("Premier League");
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(113,247,159));
        this.setBounds(0,0,1200,600); 
        this.setVisible(true); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setupTable(ArrayList<FootballClub> footballClubs) {
		String[] columnNames = {"Club","Wins","Draws","Defeats","Goals scored","Goals received","Played matches","Points"};
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		for (int i = 0; i < footballClubs.size(); i++){
			   String name = footballClubs.get(i).getName();
			   int wins = footballClubs.get(i).getWins();
			   int draws = footballClubs.get(i).getDraws();
			   int defeats = footballClubs.get(i).getDefeats();
			   int goalScored = footballClubs.get(i).getScoredGoals();
			   int goalReceived = footballClubs.get(i).getReceivedGoals();
			   int totalMatches = footballClubs.get(i).getPlayedMatches();
			   int points = footballClubs.get(i).getPoints();

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
		this.mainContainer = this.getContentPane();
        this.mainContainer.setLayout(new BorderLayout(15,15));
        
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
		
		
	}
}
