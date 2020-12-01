import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PremierLeagueManager implements LeagueManager {
	public FootballClub[] footballClubArray;
	
	private int footballClubArrayCount = 0;
	private BufferedReader input;
	
	public PremierLeagueManager()  {
		this.footballClubArray = new FootballClub[20];
		this.input = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void showUserOptions() {
		System.out.println("\n###### Please select options below:\n\n"
				+ "PRESS 1 - Create and add football club into the Premier League\n"
				+ "PRESS 2 - Remove football club from Premier League\n"
				+ "PRESS 3 - Select football club and display its statistics\n"
				+ "PRESS 4 - Display Premier League table\n");
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
			default:
				System.out.printf("You selected %d which is incorrect value\n", selectedOption);
				break;
			}
		} catch (Exception e) {
			System.out.println("###### ERROR ###### Please enter only Numbers");
			//e.printStackTrace();
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
			
			// check if the array has any added object. 
			// If it has not add new football club as first and increment it's size
			// If it has already some objects. Use footballClubArrayObjects as array
			// size and to add football club at the end of it.
			if (footballClubArrayCount == 0) {
				if (footballClubArray[0] == null) {
					footballClubArray[0] = footballClub;
					footballClubArrayCount++;
				}
			} else {
				footballClubArray[footballClubArrayCount] = footballClub;
				footballClubArrayCount++;
			}

			System.out.printf("\n###### OUTPUT ###### Name: %s, location: %s created and added to the PL table\n\n", footballClub.name, footballClub.location);
			
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
				System.out.printf("PRESS %d - %s\n", count+1, fClub.name);
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
				System.out.printf("###### You removed %s\n", footballClubArray[selectedOption-1].name);
				this.removeElement(footballClubArray, selectedOption-1);
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
				System.out.printf("PRESS %d - %s\n", count+1, fClub.name);
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
				
				System.out.printf("\n###### %s statistics:\n", footballClubArray[selectedOption-1].name);
				for (FootballClub fClub : footballClubArray) {
					if (fClub.name == footballClubArray[selectedOption-1].name) {
						System.out.printf("Name: %s\n"
								+ "Location: %s\n"
								+ "Defeats: %d\n"
								+ "Draws: %d\n"
								+ "Played matches: %d\n"
								+ "Received goals: %d\n"
								+ "Scored goals: %d\n"
								+ "Point: %d\n",
								fClub.name, 
								fClub.location, 
								fClub.defeats, 
								fClub.draws, 
								fClub.playedMatches, 
								fClub.receivedGoals, 
								fClub.scoredGoals, 
								fClub.points);
						break;
					}
				}
				
				this.showUserOptions();
			}
		} catch (Exception e) {
			System.out.println("### ERROR ### Please enter only Numbers");
			this.showUserOptions();
		}
	}
	
	private void displayPremierLeagueTable() {
		System.out.print("\n###### Premier League table:\n");
		for (int i = 0; i < this.footballClubArray.length; i++) {
			if (this.footballClubArray[i] != null) {
				System.out.printf("%d - %s\n", i+1, this.footballClubArray[i].name);
			}	
		}
		this.showUserOptions();
	}
	
	private void addPlayedGame() {
		
	}
	
	// - HELPERS
	
	private void removeElement(Object[] arr, int removedIdx) {
	    System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
	    this.footballClubArrayCount--;
	}
}
