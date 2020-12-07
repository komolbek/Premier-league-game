import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Custom, Reusable MyJTable class
 */
public class MyTableView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private FileManager fileManager;

	private ArrayList<FootballClub> footballClubs;
	private ArrayList<PlayedGame> playedGames;

	private JTable table;
	private JTable playedGamesTable;
	
	private DefaultTableModel tableModel;
	private DefaultTableModel playedGamesTableModel;
	
	private Container mainContainer;
	private JTextField searchTextField;

	/**
	 * @SortBy is used in GUI table providing finite
	 * three types of displaying football clubs when 
	 * user presses sorting buttons. And date view.
	 */
	
	private enum SortBy {
		BY_POINTS, BY_GOALS, BY_WINS, BY_DATE_CREATED
	}

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
	private MyButton saveButton;

	/**
	 * @CONSTRUCTOR
	 */
	public MyTableView(ArrayList<FootballClub> footballClubs, ArrayList<PlayedGame> playedGames) {
		this.fileManager = new FileManagerImplementation();
		
		// Assign footballClubs to local variable to use within the class 
		this.footballClubs = footballClubs;
		
		// Assign playedGames to local variable to use within the class
		this.playedGames = playedGames;
		
		/** Sort footballClub objects before they are displayed in table by their @points property */
		this.sortTableDataBy(SortBy.BY_POINTS);
		
		// Setup self GUI properties like frame, size, layout etc
		this.setupSelf();
		
		/** Assign mainContainer with ContentPane suing @self.getContentPane() */
		this.mainContainer = this.getContentPane();
		
		// Define Vertical and Horizontal distance between main GUI panels
		this.mainContainer.setLayout(new BorderLayout(15, 15));
		
		// Setup FootballClubsTable object,like create object, set its columns, data, size etc
		this.setupTable();
		
		// Setup FootballClubsScrollPanel object, add table, set size etc
		this.setupScrollPanel();
		
		// Setup PlayedMatchesTable object,like create object, set its columns, data, size etc
		this.setupPlayedMatchesTable();
		
		// Setup PlayedMatchesScrollPanel object, add table, set size etc
		this.setupPlayedMatchesTableScrollPanel();
		
		// Setup the rest GUI components
		this.setupLabelsAndTextFiels();
		this.setupButtons("Points", "Goal Scored", "Wins", "Generate", "Search");
		this.setupContainers();
	}

	/**
	 * @sortTableDataBy sorts data in tables by 
	 * @SortBy cases. After sorting it updates 
	 * table data.
	 */

	private void sortTableDataBy(SortBy sortBy) {
		switch (sortBy) {
		case BY_POINTS: {
			Collections.sort(this.footballClubs, new Comparator<FootballClub>() {
				public int compare(FootballClub p1, FootballClub p2) {
					return Integer.valueOf(p2.getPoints()).compareTo(p1.getPoints());
				}
			});

			if (this.table != null || this.tableModel != null) {
				this.updateTable();
			}

			break;
		}
		case BY_DATE_CREATED: { 
			// TODO: implement
		}
		case BY_GOALS: {
			Collections.sort(this.footballClubs, new Comparator<FootballClub>() {
				public int compare(FootballClub p1, FootballClub p2) {
					return Integer.valueOf(p2.getScoredGoals()).compareTo(p1.getScoredGoals());
				}
			});
			if (this.table != null || this.tableModel != null) {
				this.updateTable();
			}
			break;
		}
		case BY_WINS: {
			Collections.sort(this.footballClubs, new Comparator<FootballClub>() {
				public int compare(FootballClub p1, FootballClub p2) {
					return Integer.valueOf(p2.getWins()).compareTo(p1.getWins());
				}
			});

			if (this.table != null || this.tableModel != null) {
				this.updateTable();
			}

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + sortBy);
		}
	}

	private void generateRandomGame() {
		// Create Random object
		Random random = new Random();

		// Get random int to get first football club object from the list of football clubs
		int footballClub_1 = random.nextInt(this.footballClubs.size());
		
		// Get random int that is going to be first football club's goals
		int footballClub_1_Goals = random.nextInt(6);

		
		// Get random int to get second football club object from the list of football clubs
		int footballClub_2 = random.nextInt(this.footballClubs.size());
		
		// Get random int that is going to be second football club's goals
		int footballClub_2_Goals = random.nextInt(6);

		// Counter to break the loop after it processes two football club's data
		int count = 0;

		// 1 - Check if two football clubs are not identical
		// 2 - Check if football clubs goal is not less than 0
		if ((footballClub_1 != footballClub_2) && (footballClub_1_Goals >= 0 && footballClub_2_Goals >= 0)) {
			
			this.generateRandomGameDate(
					this.footballClubs.get(footballClub_1), 
					footballClub_1_Goals, 
					this.footballClubs.get(footballClub_2), 
					footballClub_2_Goals
					);
			
			/** Print for @debug purposes */
			// System.out.printf("club1 %d: (%d - %d) :%d club2 \n", footballClub1, goalsOfFootballClub1,
			// 		goalsOfFootballClub2, footballClub2);

			for (FootballClub fClub : footballClubs) { // FIXME: optimise this approach
				if (fClub != null) {
					
					// Check If fClub object matches either fClub1 or fClub2 
					if ((fClub == footballClubs.get(footballClub_1)) || (fClub == footballClubs.get(footballClub_2))) {
						
						// Add played matches of the fClub 
						fClub.setPlayedMatches(1);

						// Check If fClub1 and fClub2 scored same amount of goals
						if (footballClub_1_Goals == footballClub_2_Goals) {
							
							// Add draws
							fClub.setDraws(1);
							
							// Add one point
							fClub.setPoints(1);
						}
						count++;
					}
					
					// Check if fClub object matches fClub1 
					if (fClub == footballClubs.get(footballClub_1)) {
						
						// Add scored goals
						fClub.setScoredGoals(footballClub_1_Goals);
						
						// Add received goals 
						fClub.setReceivedGoals(footballClub_2_Goals);

						// Check if firstFClub scored more than secondFClub
						if (footballClub_1_Goals > footballClub_2_Goals) {
							
							// Add wins
							fClub.setWins(1);
							
							// Add three points
							fClub.setPoints(3);
						} else if (footballClub_1_Goals < footballClub_2_Goals) {
							
							// Add defeats
							fClub.setDefeats(1);
						}
						
						// Check if fClub object matches fClub2
					} else if (fClub == footballClubs.get(footballClub_2)) {
						
						// Add scored goals
						fClub.setScoredGoals(footballClub_2_Goals);
						
						// Add received goals 
						fClub.setReceivedGoals(footballClub_1_Goals);

						// Check if firstFClub scored less than secondFClub
						if (footballClub_1_Goals < footballClub_2_Goals) {
							
							// Add wins
							fClub.setWins(1);
							
							// Add three points
							fClub.setPoints(3);
						} else if (footballClub_1_Goals > footballClub_2_Goals) {
							
							// Add defeats
							fClub.setDefeats(1);
						}
						
					}
				} else if (count == 2) {
					break;
				}
			}

			this.updateTable();
		} else {
			
			// if the clubs are the same or the scored goals are 
			// less than 0 then generate random game again 
			this.generateRandomGame();
		}
	}
	
	private void generateRandomGameDate(FootballClub fClub1, int fClub1Goals, FootballClub fClub2, int fClub2Goals) {
		Random random = new Random();
		int minDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		
		PlayedGame playedGame = new PlayedGame(randomDate, fClub1, fClub1Goals, fClub2, fClub2Goals);
		this.playedGames.add(playedGame);
		this.updatePlayedGameTable();
	}

	/**
	 * GUI_COMPONENTS_SETUP_METHODS
	 */

	private void setupSelf() {
		this.setTitle("Premier League");
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(113, 247, 159));
		this.setBounds(0, 0, 1200, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @updateTable updates Football clubs table row data 
	 */

	private void updateTable() {
		
		// Clear table row's data
		if (tableModel.getRowCount() > 0) {
			for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
				tableModel.removeRow(i);
			}
		}

		// Add  table new row's data
		for (int i = 0; i < this.footballClubs.size(); i++) {
			String name = this.footballClubs.get(i).getName();
			int wins = this.footballClubs.get(i).getWins();
			int draws = this.footballClubs.get(i).getDraws();
			int defeats = this.footballClubs.get(i).getDefeats();
			int goalScored = this.footballClubs.get(i).getScoredGoals();
			int goalReceived = this.footballClubs.get(i).getReceivedGoals();
			int totalMatches = this.footballClubs.get(i).getPlayedMatches();
			int points = this.footballClubs.get(i).getPoints();

			Object[] data = { name, wins, draws, defeats, goalScored, goalReceived, totalMatches, points };

			// Add new row with data into the tableModel 
			tableModel.addRow(data);
		}

		// Redraw needed GUI components after update
		table.repaint();
		table.revalidate();
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * @updatePlayedGameTable updates Played games table row data 
	 */

	private void updatePlayedGameTable() { // FIXME: change algorithms
		
		// Clear table row's data
		if (playedGamesTableModel.getRowCount() > 0) {
			for (int i = playedGamesTableModel.getRowCount() - 1; i > -1; i--) {
				playedGamesTableModel.removeRow(i);
			}
		}

		// Add  table new row's data
		for (int i = 0; i < this.playedGames.size(); i++) {
			String gameResult = this.playedGames.get(i).getGameResult();
			String playedDate = this.playedGames.get(i).getGameDate();
		
			Object[] data = { gameResult, playedDate};
		
			// Add row with data into the tableModel
			playedGamesTableModel.addRow(data);
		}

		// Redraw needed GUI components after update
		playedGamesTable.repaint();
		playedGamesTable.revalidate();
		this.repaint();
		this.revalidate();
	}

	private void setupTable() {
		// Create football clubs table column names 
		String[] columnNames = { "Club", "Wins", "Draws", "Defeats", "Goals scored", "Goals received", "Played matches",
				"Points" };

		// TableModel object to contain rows and columns
		this.tableModel = new DefaultTableModel(columnNames, 0);

		// Create tableModel row data using footballClubs objects data
		for (int i = 0; i < this.footballClubs.size(); i++) {
			String name = this.footballClubs.get(i).getName();
			int wins = this.footballClubs.get(i).getWins();
			int draws = this.footballClubs.get(i).getDraws();
			int defeats = this.footballClubs.get(i).getDefeats();
			int goalScored = this.footballClubs.get(i).getScoredGoals();
			int goalReceived = this.footballClubs.get(i).getReceivedGoals();
			int totalMatches = this.footballClubs.get(i).getPlayedMatches();
			int points = this.footballClubs.get(i).getPoints();

			Object[] data = { name, wins, draws, defeats, goalScored, goalReceived, totalMatches, points };

			// Add row with data into the tableModel
			tableModel.addRow(data);
		}

		// Add tableModel into the table
		table = new JTable(tableModel);
	}

	private void setupScrollPanel() {
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(0, 0, 900, 400);
		this.mainContainer.add(scrollPanel);
	}
	
	private void setupPlayedMatchesTable() {
		// Create Played games table column names 
		String[] columnNames = { "Played games", "Date" };

		// TableModel object to contain rows and columns
		this.playedGamesTableModel = new DefaultTableModel(columnNames, 0);
		
		// Create tableModel row data using footballClubs objects data
		for (int i = 0; i < this.playedGames.size(); i++) {
			String gameResult = this.playedGames.get(i).getGameResult();
			String playedDate = this.playedGames.get(i).getGameDate();
		
			Object[] data = { gameResult, playedDate};
		
			// Add row with data into the tableModel
			playedGamesTableModel.addRow(data);
		}
		
		// Add tableModel into the table
		playedGamesTable = new JTable(playedGamesTableModel);
	}
	
	private void setupPlayedMatchesTableScrollPanel() {
		JScrollPane scrollPanel = new JScrollPane(playedGamesTable);
		scrollPanel.setBounds(0, 0, 250, 400);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout(1));
		rightPanel.add(scrollPanel);
		this.mainContainer.add(rightPanel, BorderLayout.EAST);
	}

	private void setupLabelsAndTextFiels() {
		this.sortByLabel = new JLabel("Sort by:");
		this.generateLabel = new JLabel("Generate random game:");
		this.searchLabel = new JLabel("Search played games by date:");
		this.searchTextField = new JTextField();
		this.searchTextField.setColumns(20);
		this.searchTextField.setText("ex: 10 May 2020");
		this.searchTextField.setForeground(Color.GRAY);
		
		// By default shows default text. But when user click text
		// field it will clear and wait for user input. When user 
		// leaves text field, and focus lost, return default text
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
		topJPanel.add(this.saveButton);
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
	
	

	private void setupButtons(String byPointsTitle, String byGoalsTitle, String byWinsTitle, String generateTitle,
			String searchTitle) {
		this.sortByPointsButton = new MyButton(byPointsTitle, this);
		this.sortByGoalSButton = new MyButton(byGoalsTitle, this);
		this.sortByWinsButton = new MyButton(byWinsTitle, this);
		this.generateRandomGameButton = new MyButton(generateTitle, this);
		this.searchButton = new MyButton(searchTitle, this);
		this.saveButton = new MyButton("Save changes", this, Color.GREEN);
	}

	/**
	 * ACTION_LISTENER
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.sortByPointsButton) {
			this.sortTableDataBy(SortBy.BY_POINTS);
		} else if (e.getSource() == this.sortByGoalSButton) {
			this.sortTableDataBy(SortBy.BY_GOALS);
		} else if (e.getSource() == this.sortByWinsButton) {
			this.sortTableDataBy(SortBy.BY_WINS);
		} else if (e.getSource() == this.generateRandomGameButton) {
			this.generateRandomGame();
		} else if (e.getSource() == this.saveButton) {
			this.fileManager.writeFootballClubsToFile(this.footballClubs);
			this.fileManager.writePlayedGamesToFile(this.playedGames);
		}
	}
}
