import java.util.ArrayList;

/**
 * Basic methods interface 
 * Implemented in PremierLeagueManager class
 */
public interface LeagueManager {
	
	/**
	 * return ArrayList of FootballClub objects
	 */
	public ArrayList<FootballClub> getfootballClubs();
	
	/**
	 * return ArrayList of PlayedGame objects
	 */
	public ArrayList<PlayedGame> getPlayedGames();
}
