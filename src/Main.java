
public class Main {
	public static void main(String[] args) {
		PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
		premierLeagueManager.showUserOptions();
	
		// Custom created JTable object. Custom classes are efficient to be reused 
		MyTableView myTableView = new MyTableView(
				premierLeagueManager.getfootballClubs(), 
				premierLeagueManager.getPlayedGames()
				);
		
		myTableView.revalidate();
	}
}
