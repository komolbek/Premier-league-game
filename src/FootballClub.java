import java.io.Serializable;

public class FootballClub extends SportClub implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Private properties
	private String name;
	private String location;
	private int wins;
	private int draws;
	private int defeats;
	private int scoredGoals;
	private int receivedGoals;
	private int playedMatches;
	private int points;

	/**
	 * @CONSTRUCTOR
	 */
	
	public FootballClub(String name, String location) {
		this.setName(name);
		this.setLocation(location);
	}
	
	/**
	 * Name getter and Setter
	 */
	@Override
	public String getName() { 
		return this.name; 
	}
	@Override
	public void setName(String name) { 
		this.name = name; 
	}
	
	/**
	 * Location getter and Setter
	 */
	@Override
	public String getLocation() { 
		return this.location; 
	}
	@Override
	public void setLocation(String location) { 
		this.location = location; 
	}
	
	/**
	 * Wins getter and Setter
	 */
	
	public int getWins() {
		return wins;
	}

	public void addWin() {
		this.wins += 1;
	}
	
	/**
	 * Draws getter and Setter
	 */

	public int getDraws() {
		return draws;
	}

	public void addDraw() {
		this.draws += 1;
	}
	
	/**
	 * Defeats getter and Setter
	 */

	public int getDefeats() {
		return defeats;
	}

	public void addDefeat() {
		this.defeats += 1;
	}
	
	/**
	 * ScoredGoals getter and Setter
	 */

	public int getScoredGoals() {
		return scoredGoals;
	}

	public void addScoredGoals(int scoredGoals) {
		this.scoredGoals += scoredGoals;
	}
	
	/**
	 * ReceivedGoals getter and Setter
	 */

	public int getReceivedGoals() {
		return receivedGoals;
	}

	public void addReceivedGoals(int receivedGoals) {
		this.receivedGoals += receivedGoals;
	}
	
	/**
	 * PlayedMatches getter and Setter
	 */

	public int getPlayedMatches() {
		return playedMatches;
	}

	public void addPlayedMatch() {
		this.playedMatches += 1;
	}
	
	/**
	 * Points getter and Setter
	 */

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	@Override
	public String toString() {
		return "FootballClub [name = " + name 
				+ ", location = " + location 
				+ ", wins = " + wins 
				+ ", draws = " + draws 
				+ ", defeats = " + defeats 
				+ ", scoredGoals = " + scoredGoals 
				+ ", receivedGoals = " + receivedGoals
				+ ", playedMatches = " + playedMatches 
				+ ", points = " + points + "]";
	}

	
}


