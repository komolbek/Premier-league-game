import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PremierLeagueManager implements LeagueManager {
	private ArrayList<FootballClub> footballClubArray = new ArrayList<>();
	private int footballClubArrayCount = 0;
	private BufferedReader input;
	private Boolean isFileCreated = false;

	public PremierLeagueManager() {
		this.footballClubArray = new  ArrayList<FootballClub>();
		this.input = new BufferedReader(new InputStreamReader(System.in));
	}

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

			switch (selectedOption) {
			case 1: {
				System.out.printf("You selected %d\n", selectedOption);
				this.createFootballClub();
				break;
			}
			case 2: {
				System.out.printf("You selected %d\n", selectedOption);
				this.deleteFootballClub();
				break;
			}
			case 3: {
				System.out.printf("You selected %d\n", selectedOption);
				this.displayfootballClubStatistics();
				break;
			}
			case 4: {
				System.out.printf("You selected %d\n", selectedOption);
				this.displayPremierLeagueTable();
				break;
			}
			case 5: {
				System.out.printf("You selected %d\n", selectedOption);
				this.addPlayedGame();
				break;
			}
			case 6: {
				System.out.printf("You selected %d\n", selectedOption);
				this.wrtieChangesToFile();
//				this.footballClubArray = this.readDataFromFile();
//				if (footballClubArray.size() > 0) {
//					for (FootballClub fClub : footballClubArray) {
//						System.out.println(fClub.name);
//					}
//				} else {
//					System.out.print("FAIL while reading file");
//				}
				return;
			}
			default:
				System.out.printf("You selected %d which is incorrect value\n", selectedOption);
				break;
			}
		} catch (Exception e) {
			System.out.println("###### ERROR ###### Please enter only Numbers");
			 System.out.print(e.getLocalizedMessage());
			this.showUserOptions();
		}
	}

	// Create new football club object. Give it's name and location
	// and add it to the footballClubArray.
	private void createFootballClub() {
		String name;
		String location;

		try {
			System.out.println("\n###### Please add Football club name.");
			System.out.print("Name: ");
			name = input.readLine();

			System.out.println("\n###### Please add Football club's location.");
			System.out.print("Location: ");
			location = input.readLine();

			FootballClub footballClub = new FootballClub(name, location);

			footballClubArray.add(footballClub);

			System.out.printf("\n###### SUCCESS ###### Name: %s, location: %s created and added to the PL table",
					footballClub.name, footballClub.location);

			this.showUserOptions();
		} catch (Exception e) {
			System.out.println("Please use only letters");
		}
	}

	// Delete football club from Premier League table
	private void deleteFootballClub() {
		int count = 0;

		// Before selecting, I need to show available football club in PL table
		System.out.print("\n###### Please select number or PRESS - q to quit:\n");
		for (FootballClub fClub : footballClubArray) {
			if (fClub != null) {
				System.out.printf("PRESS %d - %s\n", count + 1, fClub.name);
				count++;
			} else if (count == 0) {
				System.out.print("\n###### The Premier League table is empty now\n");
				this.showUserOptions();
				break;
			}
		}

		int selectedOption;
		// Try to get user input
		try {
			System.out.print("Select: ");
			selectedOption = Integer.parseInt(input.readLine());

			if (String.valueOf(selectedOption) == "q") {
				System.out.print("\n###### You cancelled operation\n");
				this.showUserOptions();
			} else {
				System.out.printf("###### You removed %s\n", footballClubArray.get(selectedOption - 1).name);
				this.footballClubArray.remove(selectedOption - 1);
				this.displayPremierLeagueTable();
				this.showUserOptions();
			}
		} catch (Exception e) {
			System.out.println("### ERROR ### Please enter only Numbers");
			this.showUserOptions();
		}
	}

	private void displayfootballClubStatistics() {
		int count = 0;

		// Before selecting, I need to show available football club in PL table
		System.out.print("\n###### Please select number or PRESS - q to quit:\n");
		for (FootballClub fClub : footballClubArray) {
			if (fClub != null) {
				System.out.printf("PRESS %d - %s\n", count + 1, fClub.name);
				count++;
			} else if (count == 0) {
				System.out.print("\n###### The Premier League table is empty now\n");
				this.showUserOptions();
				return;
			}
		}

		int selectedOption;
		// Try to get user input
		try {
			System.out.print("Select: ");
			selectedOption = Integer.parseInt(input.readLine());

			if (String.valueOf(selectedOption) == "q") {
				System.out.print("\n###### You cancelled operation\n");
			} else {

				System.out.printf("\n###### %s statistics:\n", footballClubArray.get(selectedOption - 1).name);
				for (FootballClub fClub : footballClubArray) {
					if (fClub.name == footballClubArray.get(selectedOption - 1).name) {
						System.out.printf(
								"Name: %s\n" + "Location: %s\n" + "Defeats: %d\n" + "Draws: %d\n"
										+ "Played matches: %d\n" + "Received goals: %d\n" + "Scored goals: %d\n"
										+ "Points: %d\n",
								fClub.name, fClub.location, fClub.defeats, fClub.draws, fClub.playedMatches,
								fClub.receivedGoals, fClub.scoredGoals, fClub.points);
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("### ERROR ### Please enter only Numbers");
		}
		this.showUserOptions();
	}

	private void displayPremierLeagueTable() {
		int count = 0;

		if (footballClubArray.size() == 0) {
			System.out.print("\n###### Premier League table is empty:");
		} else {
			System.out.print("\n###### Premier League table:\n");
			for (FootballClub fClub : footballClubArray) {
				if (this.footballClubArray.get(count) != null) {
					System.out.printf(
							"\n%d - " + "%s, " + "Wins: %d " + "Defeats: %d, " + "Draws: %d, " + "Played matches: %d, "
									+ "Received goals: %d, " + "Scored goals: %d, " + "Points: %d",
							count + 1, fClub.name, fClub.wins, fClub.defeats, fClub.draws, fClub.playedMatches,
							fClub.receivedGoals, fClub.scoredGoals, fClub.points);
				}
				count++;
			}
		}
		this.showUserOptions();
	}

	private void addPlayedGame() {
		int count = 0;

		// Before selecting, I need to show available football club in PL table
		System.out.print("\n###### Please select number:\n");
		if (footballClubArrayCount < 2) {
			System.out.print("\n###### NOT ENOUGH football clubs\n");
			this.showUserOptions();
		} else {
			for (FootballClub fClub : footballClubArray) {
				if (fClub != null) {
					System.out.printf("PRESS %d - %s\n", count + 1, fClub.name);
					count++;
				}
			}
		}

		int firstSelectClub;
		int firstClubScoredGoals;
		int secondSelectClub;
		int secondClubScoredGoals;

		try {
			System.out.print("Select FIRST football club: ");
			firstSelectClub = Integer.parseInt(input.readLine());
			System.out.print("Add FIRST football club scored GOALS: ");
			firstClubScoredGoals = Integer.parseInt(input.readLine());

			System.out.print("Select SECOND football club: ");
			secondSelectClub = Integer.parseInt(input.readLine());
			System.out.print("Add SECOND football club scored GOALS: ");
			secondClubScoredGoals = Integer.parseInt(input.readLine());

			count = 0;
			for (FootballClub fClub : footballClubArray) {
				if (fClub != null) {
					if (fClub.name == footballClubArray.get(firstSelectClub - 1).name) {
						fClub.playedMatches++;

						fClub.scoredGoals += firstClubScoredGoals;
						fClub.receivedGoals += secondClubScoredGoals;

						if (firstClubScoredGoals > secondClubScoredGoals) {
							fClub.wins += 1;
							fClub.points += 3;
						} else if (firstClubScoredGoals == secondClubScoredGoals) {
							fClub.draws += 1;
							fClub.points += 1;
						} else {
							fClub.defeats += 1;
						}
						count++;
					} else if (fClub.name == footballClubArray.get(secondSelectClub - 1).name) {
						fClub.playedMatches++;

						fClub.scoredGoals += secondClubScoredGoals;
						fClub.receivedGoals += firstClubScoredGoals;

						if (firstClubScoredGoals < secondClubScoredGoals) {
							fClub.wins += 1;
							fClub.points += 3;
						} else if (firstClubScoredGoals == secondClubScoredGoals) {
							fClub.draws += 1;
							fClub.points += 1;
						} else {
							fClub.defeats += 1;
						}
						count++;
					} else if (count == 2) {
						break;
					}
				}
			}
			System.out.printf("\n###### SUCCESS ######: %s %d - %d %s", footballClubArray.get(firstSelectClub - 1).name,
					firstClubScoredGoals, secondClubScoredGoals, footballClubArray.get(secondSelectClub - 1).name);
			this.showUserOptions();
		} catch (Exception e) {
			System.out.println("### ERROR ### Please enter only Numbers");
			System.out.println(e.getLocalizedMessage());
			this.showUserOptions();
		}
	}
	
	private void wrtieChangesToFile() {
		if (this.isFileCreated) {
			readDataFromFile();
		} else {
			try {
				  //Write FootballClubs array from file.
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/footballClubList.txt"));
				for (FootballClub fClub : footballClubArray) {
					if (fClub != null) {
						os.writeObject(fClub);
					}
				}
				this.isFileCreated = true;
				os.close();
			    return;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	public void readDataFromFile() {
	    //Read FootballClubs array from file.
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("src/footballClubList.txt"));
		    ArrayList<FootballClub> readObject = (ArrayList<FootballClub>) is.readObject();
			this.footballClubArray = readObject;
		    System.out.printf("##### DONE ##### %s", this.footballClubArray.toString());
		    is.close();
//		    return this.footballClubArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
//		return new ArrayList<FootballClub>();
	}
}
