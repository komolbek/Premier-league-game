import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
		premierLeagueManager.showUserOptions();
		
		Collections.sort(premierLeagueManager.getfootballClubs(), new Comparator<FootballClub>() {
	        public int compare(FootballClub p1, FootballClub p2) {
	           return Integer.valueOf(p2.getPoints()).compareTo(p1.getPoints());
	        }
		});
		
		// Custom created JTable object. Custom classes are efficient to be reused 
		MyTableView myTableView = new MyTableView(premierLeagueManager.getfootballClubs());
		myTableView.revalidate();
	}
}
