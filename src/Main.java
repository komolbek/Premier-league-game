
public class Main {
	public static void main(String[] args) {
		
		// Create PremierLeagueManager objects
		PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
		
		// Start console logic showing options to the use
		premierLeagueManager.showUserOptions();
	
		// After user finished in console part, create MyTableView object  
		// with list of FootballClubs and PlayedGames and assign it to 
		// myTableView parameters  
		MyTableView myTableView = new MyTableView(
				premierLeagueManager.getfootballClubs(), 
				premierLeagueManager.getPlayedGames()
				);
		
		// Redraw GUI components when any changes happen
		myTableView.revalidate();
	}
}
