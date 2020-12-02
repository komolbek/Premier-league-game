import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class PremierLeagueManager implements LeagueManager {
	
	/** PRIVATE_PROPERTIES */
	
	/**
	 * @footballClubs is a list of unique object of FootballClub class
	 */
	
	private ArrayList<FootballClub> footballClubs;
	
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
	
	private enum DisplayPremierLeagueTableType {
		BY_NAME,
		BY_STATISTICS
	}
	
	/** CONSTRUCTOR */

	public PremierLeagueManager() {
		this.footballClubs = new  ArrayList<FootballClub>();
		this.input = new BufferedReader(new InputStreamReader(System.in));
		this.fileManager = new FileManagerImplementation();
		
		try {
			this.fileManager.readTrainsFromFile(this.footballClubs);
		} catch (FileNotFoundException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
	}
	
	/** PUBLIC_METHODS */
	
	/** 
	 * @showUserOptions
	 */

	public void showUserOptions() {
		System.out.println("\n\n###### Please select options below:\n\n"
				+ "PRESS 1 - Create and add football club into the Premier League\n"
				+ "PRESS 2 - Remove football club from Premier League\n"
				+ "PRESS 3 - Select football club and display its statistics\n"
				+ "PRESS 4 - Display Premier League table\n" 
				+ "PRESS 5 - Add played match\n"
				+ "PRESS 6 - START Grapical Interface\n");
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
				this.fileManager.writeDataToFile(this.footballClubs);
				this.fileManager.readTrainsFromFile(this.footballClubs);
				break;
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
	 * @createFootballClub
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
					footballClub.name.toUpperCase());
		} catch (Exception e) {
			System.out.printf("Please use only letters. %s\n", e.getLocalizedMessage());
		}
		this.showUserOptions();
	}

	/** 
	 * @deleteFootballClub
	 */
	
	private void deleteFootballClub() {
		System.out.print("\n###### Please select number from list below:\n");
		
		if (footballClubs.size() > 0) {
			int selectedOption;
			
			this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_NAME);
			
			try {
				System.out.print("Select: ");
				selectedOption = Integer.parseInt(input.readLine());

				System.out.printf("\n###### You removed %s\n", footballClubs.get(selectedOption - 1).name.toUpperCase());
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
	 * @displayfootballClubStatistics
	 */

	private void displayfootballClubStatistics() {
		if (footballClubs.size() > 0) {
			System.out.print("\n###### Please select number from list below:\n");
			int selectedOption;
			
			this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_NAME);
			
			try {
				System.out.print("Select: ");
				selectedOption = Integer.parseInt(input.readLine());

				System.out.printf("\n###### %s statistics:\n", footballClubs.get(selectedOption - 1).name.toUpperCase());
				
				for (FootballClub fClub : footballClubs) {
					if (fClub.name == footballClubs.get(selectedOption - 1).name) {
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
	 * @displayPremierLeagueTable
	 */

	private void displayPremierLeagueTable(DisplayPremierLeagueTableType type) {
		if (footballClubs.size() > 0) {
			System.out.print("\n###### Premier League table:\n");
			
			switch (type) {
			case BY_NAME: {
				for (int i = 0; i < this.footballClubs.size(); i++) {
					if (this.footballClubs.get(i) != null) {
						System.out.printf("PRESS %d - %s\n", i + 1, this.footballClubs.get(i).name);
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
	 * @addPlayedGame
	 */

	private void addPlayedGame() {
		if (footballClubs.size() > 0) {
			System.out.print("\n###### Please select number:\n");
			
			this.displayPremierLeagueTable(DisplayPremierLeagueTableType.BY_NAME);
			
			int firstSelectClub, firstClubScoredGoals, secondSelectClub, secondClubScoredGoals, count = 0;

			try {
				System.out.print("\nSelect FIRST football club: ");
				firstSelectClub = Integer.parseInt(input.readLine());
				System.out.print("Add FIRST football club scored GOALS: ");
				firstClubScoredGoals = Integer.parseInt(input.readLine());

				System.out.print("Select SECOND football club: ");
				secondSelectClub = Integer.parseInt(input.readLine());
				System.out.print("Add SECOND football club scored GOALS: ");
				secondClubScoredGoals = Integer.parseInt(input.readLine());

				for (FootballClub fClub : footballClubs) {
					if (fClub != null) {
						if (fClub.name == footballClubs.get(firstSelectClub - 1).name) {
							fClub.setPlayedMatches(1);
							fClub.setScoredGoals(firstClubScoredGoals);
							fClub.setReceivedGoals(secondClubScoredGoals);

							if (firstClubScoredGoals > secondClubScoredGoals) {
								fClub.setWins(1);
								fClub.setPoints(3);
							} else if (firstClubScoredGoals == secondClubScoredGoals) {
								fClub.setDraws(1);
								fClub.setPoints(1);
							} else {
								fClub.setDefeats(1);
							}
							count++;
						} else if (fClub.name == footballClubs.get(secondSelectClub - 1).name) {
							fClub.setPlayedMatches(1);
							fClub.setScoredGoals(secondClubScoredGoals);
							fClub.setReceivedGoals(firstClubScoredGoals);

							if (firstClubScoredGoals < secondClubScoredGoals) {
								fClub.setWins(1);
								fClub.setPoints(3);
							} else if (firstClubScoredGoals == secondClubScoredGoals) {
								fClub.setDraws(1);
								fClub.setPoints(1);
							} else {
								fClub.setDefeats(1);
							}
							count++;
						}
					} else if (count == 2) {
						break;
					}
				}
				
				System.out.printf("\n###### SUCCESS ######: %s %d - %d %s", footballClubs.get(firstSelectClub - 1).name,
						firstClubScoredGoals, secondClubScoredGoals, footballClubs.get(secondSelectClub - 1).name);
			} catch (Exception e) {
				System.out.printf("### ERROR ### Please enter only Numbers. %s\n", e.getLocalizedMessage());
			}
		} else {
			System.out.print("\n###### Premier League table is empty:");
		}
		this.showUserOptions();
	}
	
	/** PUBLIC_METHODS*/
	
	/**
	 * To see how many Football clubs the Premier League
	 * manager manages we can use this @getfootballClubs
	 * @getter method that returns a list of objects of 
	 * FootballClub class 
	 */
	
	public ArrayList<FootballClub> getfootballClubs() {
		return footballClubs;
	}
}
