import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class PremierLeagueManager implements LeagueManager {
	
	/** PRIVATE_PROPERTIES */
	/**
	 * @footballClubs is a list of unique object of FootballClub class
	 */
	private ArrayList<FootballClub> footballClubs;
	/**
	 * @playedGames is a list of unique objects of PlayedGame class
	 */
	private ArrayList<PlayedGame> playedGames;
	/**
	 * @input is a user console input reader. 
	 * It is used in PremierLeagueManager class's
	 * several methods, that's why I moved it to
	 * the properties level to reused it.
	 */
	private BufferedReader input;
	/**
	 * @fileManager 
	 */
	private FileManager fileManager;
	/**
	 * @DisplayPremierLeagueTableType is a private helper 
	 * enum providing BY_NAME, BY_STATISCTICS options to 
	 * display table in console
	 */
	private enum DisplayPremierLeagueTableType { BY_NAME, BY_STATISTICS }
	
	/** CONSTRUCTOR */

	public PremierLeagueManager() {
		this.footballClubs = new  ArrayList<FootballClub>();
		this.playedGames = new  ArrayList<PlayedGame>();
		this.input = new BufferedReader(new InputStreamReader(System.in));
		this.fileManager = new FileManagerImplementation();
		
		if (this.fileManager.isDataAvailable(FileDataType.FOOTBALL_CLUBS)) {
			this.footballClubs = this.fileManager.readFootballClubsFromFile();
		}
		
		if (this.fileManager.isDataAvailable(FileDataType.PLAYED_GAMES)) {
			this.playedGames = this.fileManager.readPlayedGamesFromFile();
		}
	}
	/** PUBLIC_METHODS */
	/** 
	 * @showUserOptions prints possible user interaction option in the console
	 * and when user adds particular number (int) method will call particular
	 * method using @swift_case pattern
	 */
	public void showUserOptions() {
		System.out.println("\n\n###### Please select options below:\n\n"
				+ "PRESS 1 - Create and add football club into the Premier League\n"
				+ "PRESS 2 - Remove football club from Premier League\n"
				+ "PRESS 3 - Select football club and display its statistics\n"
				+ "PRESS 4 - Display Premier League table\n" 
				+ "PRESS 5 - Add played game\n"
				+ "PRESS 6 - Save all changes\n"
				+ "PRESS 7 - START Grapical Interface\n");
		int selectedOption;

		try {
			System.out.print("Select: ");
			selectedOption = Integer.parseInt(input.readLine());
			System.out.printf("You selected %d\n", selectedOption);
			
			switch (selectedOption) {
			case 1: { this.createFootballClub(); break; }
			case 2: { this.deleteFootballClub(); break; }
			case 3: { this.displayfootballClubStatistics(); break; }
			case 4: { this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_STATISTICS); break; }
			case 5: { this.addPlayedGame(); break; }
			case 6: { 
				if ((footballClubs.size() > 4) || (playedGames.size() > 3)) {
					this.fileManager.writeFootballClubsToFile(this.footballClubs);
					this.footballClubs = this.fileManager.readFootballClubsFromFile();
					
					this.fileManager.writePlayedGamesToFile(this.playedGames);
					this.playedGames = this.fileManager.readPlayedGamesFromFile();
				} else {
					System.out.print("\n###### PLEASE add at least 5 CLUBS or 4 played games");
				}
				
				this.showUserOptions();
				break;
				}
			case 7: { 
				if ((footballClubs.size() > 4) || (playedGames.size() > 3)) {
					return;
				} else {
					System.out.print("\n###### PLEASE add at least 5 CLUBS or 4 played games");
					this.showUserOptions();
					break;
				}
				}
			default:
				System.out.printf("You selected %d which out of range value\n", selectedOption);
				this.showUserOptions();
				break;
			}
		} catch (Exception e) {
			System.out.printf("###### ERROR ###### PLEASE ENTER ONLY NUMBERS 1-6. %s\n", e.getLocalizedMessage());
			this.showUserOptions();
		}
	}
	/** 
	 * @createFootballClub creates object with its name and location using console input 
	 */
	private void createFootballClub() {
		String name;
		String location;

		try {
			System.out.print("\n###### Please add Football club NAME: ");
			name = input.readLine();

			System.out.print("###### Please add Football club's LOCATION: ");
			location = input.readLine();
			
			FootballClub footballClub = new FootballClub(name, location);
			this.footballClubs.add(footballClub);
			
			System.out.printf("\n###### SUCCESS ###### Name: %s is added to the table",
					footballClub.getName().toUpperCase());
		} catch (Exception e) {
			System.out.printf("Please use only letters. %s\n", e.getLocalizedMessage());
		}
		this.showUserOptions();
	}
	/** 
	 * @deleteFootballClub removes selected football club from the arrayList and prints removed object
	 */
	private void deleteFootballClub() {
		System.out.print("\n###### Please select number from list below:\n");
		
		if (footballClubs.size() > 0) {
			int selectedOption;
			
			this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_NAME);
			
			try {
				System.out.print("Select: ");
				selectedOption = Integer.parseInt(input.readLine());

				System.out.printf("\n###### You removed %s\n", footballClubs.get(selectedOption - 1).getName());
				this.footballClubs.remove(selectedOption - 1);
				this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_STATISTICS);
			} catch (Exception e) {
				System.out.printf("### ERROR ### Please enter only Numbers and no empty space. %s", e.getLocalizedMessage());
			}
		} else {
			System.out.print("\n###### OOPS ###### The Premier League table is EMPTY now\n");
		}
		this.showUserOptions();
	}
	/** 
	 * @displayfootballClubStatistics shows the selected football club's statistics
	 */
	private void displayfootballClubStatistics() {
		if (footballClubs.size() > 0) {
			System.out.print("\n###### Please select number from list below:\n");
			int selectedOption;
			
			this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_NAME);
			
			try {
				System.out.print("Select: ");
				selectedOption = Integer.parseInt(input.readLine());

				System.out.printf("\n###### %s statistics:\n", footballClubs.get(selectedOption - 1).getName().toUpperCase());
				
				for (FootballClub fClub : footballClubs) {
					if (fClub.getName() == footballClubs.get(selectedOption - 1).getName()) {
						System.out.printf(fClub.toString());
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("### ERROR ### Please enter only Numbers");
			}
		} else {
			System.out.print("\n###### The Premier League table is empty now\n");
		}
		this.showUserOptions();
	}
	/** 
	 * @displayPremierLeagueTable prints particular football club's statistics 
	 * or statistics of all created football clubs  
	 */
	private void displayPremierLeagueTable(DisplayPremierLeagueTableType type) {
		if (footballClubs.size() > 0) {
			System.out.print("\n###### Premier League table:\n");
			
			switch (type) {
			case BY_NAME: {
				for (int i = 0; i < this.footballClubs.size(); i++) {
					if (this.footballClubs.get(i) != null) {
						System.out.printf("PRESS %d - %s\n", i + 1, this.footballClubs.get(i).getName());
					}
				}
				return;
			}
			case BY_STATISTICS: {
				for (int i = 0; i < this.footballClubs.size(); i++) {
					if (this.footballClubs.get(i) != null) {
						System.out.printf("%s\n", this.footballClubs.get(i).toString());
					}
				}
				break;
			}
			}
		} else {
			System.out.print("\n###### Premier League table is empty:");
		}
		this.showUserOptions();
	}
	/** 
	 * @addPlayedGame asks user to select first football club and its score
	 * and also ask to select second football club and its score. Then updates 
	 * Selected clubs statistics and creates the played match with result and date
	 */ 
	private void addPlayedGame() {
		if (footballClubs.size() > 1) {
			System.out.print("\n###### Please select number:\n");
			
			this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_NAME);
			
			FootballClub footballClub_1, footballClub_2;
			int footballClub_1_Goals, footballClub_2_Goals;
			String gameDate;

			try {
				
				// Get first football club index from user input and get fClub object from list
				System.out.print("\nSelect FIRST football club: ");
				footballClub_1 = this.footballClubs.get(Integer.parseInt(input.readLine()) - 1);
				
				// Get first football club scored goals from user input
				System.out.print("Add FIRST football club scored GOALS: ");
				footballClub_1_Goals = Integer.parseInt(input.readLine());

				// Get second football club index from user input and get fClub object from list
				System.out.print("Select SECOND football club: ");
				footballClub_2 = this.footballClubs.get(Integer.parseInt(input.readLine()) - 1);
				
				// Get second football club scored goals from user input
				System.out.print("Add SECOND football club scored GOALS: ");
				footballClub_2_Goals = Integer.parseInt(input.readLine());
				
				// Get date from user input
				System.out.print("Select game played date EXACTLY in format 23/12/2019: ");
				gameDate = input.readLine();
				
				//Create played game with date and all needed data
				PlayedGame playedGame = new PlayedGame(gameDate, 
						footballClub_1, 
						footballClub_1_Goals, 
						footballClub_2, 
						footballClub_2_Goals);
				
				this.playedGames.add(playedGame);
				
				if ((footballClub_1 != footballClub_2) && (footballClub_1_Goals >= 0 && footballClub_2_Goals >= 0)) {
					
					// Add played matches to the fClubs 
					footballClub_1.addPlayedMatch();
					footballClub_2.addPlayedMatch();
					
					// Add scored goals
					footballClub_1.addScoredGoals(footballClub_1_Goals);
					footballClub_2.addScoredGoals(footballClub_2_Goals);
					
					// Add received goals 
					footballClub_1.addReceivedGoals(footballClub_2_Goals);
					footballClub_2.addReceivedGoals(footballClub_1_Goals);

					// Check If fClub1 and fClub2 scored same amount of goals
					if (footballClub_1_Goals == footballClub_2_Goals) {
						
						// Add draws
						footballClub_1.addDraw();
						footballClub_2.addDraw();
						
						// Add one point
						footballClub_1.addPoints(1);
						footballClub_2.addPoints(1);
						
					// Check if firstFClub scored more than secondFClub
					} else if (footballClub_1_Goals > footballClub_2_Goals) {
						
						// Add win to fClub_1
						footballClub_1.addWin();
						
						// Add defeat to fClub_2
						footballClub_2.addDefeat();
						
						// Add three points
						footballClub_1.addPoints(3);
						
					// Check if firstFClub scored less than secondFClub
					} else if (footballClub_1_Goals < footballClub_2_Goals) {
						
						// Add win to fClub_2
						footballClub_2.addWin();
						
						// Add defeat to fClub_1
						footballClub_1.addDefeat();
						
						// Add three points
						footballClub_2.addPoints(3);
					}
				} else {
					
					// if the clubs are the same
					System.out.printf("\n### WARNING ### You can not select two same football clubs");
					this.showUserOptions();
				}
				
				System.out.printf("\n###### SUCCESS ######: %s %d - %d %s", footballClub_1.getName(),
						footballClub_1_Goals, footballClub_2_Goals, footballClub_2.getName());
			} catch (Exception e) {
				System.out.printf("### ERROR ### Please enter only Numbers. %s\n", e.getLocalizedMessage());
			}
		} else {
			System.out.print("\n###### The PL table is EPMTY or NOT ENOUGH clubs");
		}
		this.showUserOptions();
	}
	/** PUBLIC_METHODS*/
	/**
	 * To use Football clubs in the GUI part we can 
	 * use this @getfootballClubs getter method that 
	 * returns a list of objects of FootballClub class 
	 */
	@Override
	public ArrayList<FootballClub> getfootballClubs() {
		return this.footballClubs;
	}
	/**
	 * To use PlayedGames in the GUI part we can 
	 * use this @getPlayedGames getter method that 
	 * returns a list of objects of PlayedGames class 
	 */
	@Override
	public ArrayList<PlayedGame> getPlayedGames() {
		return this.playedGames;
	}
}
