import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PremierLeagueManager implements LeagueManager {
	SportClub[] footballClubs;
	BufferedReader input;
	
	public PremierLeagueManager()  {
		this.footballClubs = new FootballClub[20];
		this.input = new BufferedReader(new InputStreamReader(System.in));
		this.showUserOptions();
	}
	
	private void showUserOptions() {
		System.out.println("Please select options below\n\n"
				+ "PRESS 1 - Create and add football club into the Premier League\n"
				+ "PRESS 2 - Remove football club from Premier League\n"
				+ "PRESS 3 - Select football club and display its statistics\n"
				+ "PRESS 4 - Display Premier League table\n");
		
		int selectedOption;
		
		try {
			selectedOption = Integer.parseInt(input.readLine());
			
			switch (selectedOption) {
			case 1: {
				System.out.printf("You selected %d\n", selectedOption);
				this.createFootballClub();
				break;
			}
			case 2: {
				System.out.printf("You selected %d\n", selectedOption);
				break;
			}
			case 3: {
				System.out.printf("You selected %d\n", selectedOption);
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
			System.out.println("Please enter only Numbers");
			e.printStackTrace();
			this.showUserOptions();
		}
	}
	
	private void createFootballClub() {
		String name;
		String location;
		
		try {
			System.out.println("Please add Football club name");
			name = input.readLine();
			
			System.out.println("Please add Football club's location");
			location = input.readLine();
			
			FootballClub footballClub = new FootballClub(name, location);
			for (int i = 0; i < footballClubs.length; i++) {
				switch (i) {
				case 0: {
					footballClubs[0] = footballClub;
					break;
				}
				default:
					footballClubs[i - 1] = footballClub;
					break;
				}
			}
			
			System.out.printf("\nFootball club %s in %s is created and added to the Premier League\n\n", footballClub.name, footballClub.location);
			
			this.showUserOptions();
		} catch (Exception e) {
			System.out.println("Please use only letters");
		}
	}
	
	private void deleteFootballClub() {
		
	}
	
	private void displayFootballClubStatistic() {
		
	}
	
	private void displayPremierLeagueTable() {
		for (int i = 0; i < this.footballClubs.length; i++) {
			if (this.footballClubs[i] != null) {
				System.out.println(this.footballClubs[i].name);
			}	
		}
	}
	
	private void addPlayedGame() {
		
	}
}
