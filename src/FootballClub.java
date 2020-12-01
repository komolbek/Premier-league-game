import java.io.Serializable;

public class FootballClub extends SportClub implements Serializable {
	
	//Private properties
	private int id;
	private int wins;
	private int draws;
	private int defeats;
	private int scoredGoals;
	private int receivedGoals;
	private int playedMatches;
	private int points;
	
	//Constructor
	public FootballClub(String name, String location) {
		this.name     = name;
		this.location = location;
	}
	
	/**
	 * 
	 * ID getter and Setter
	 */
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * Wins getter and Setter
	 */
	
	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins += wins;
	}
	
	/**
	 * 
	 * Draws getter and Setter
	 */

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws += draws;
	}
	
	/**
	 * 
	 * Defeats getter and Setter
	 */

	public int getDefeats() {
		return defeats;
	}

	public void setDefeats(int defeats) {
		this.defeats += defeats;
	}
	
	/**
	 * 
	 * ScoredGoals getter and Setter
	 */

	public int getScoredGoals() {
		return scoredGoals;
	}

	public void setScoredGoals(int scoredGoals) {
		this.scoredGoals += scoredGoals;
	}
	
	/**
	 * 
	 * ReceivedGoals getter and Setter
	 */

	public int getReceivedGoals() {
		return receivedGoals;
	}

	public void setReceivedGoals(int receivedGoals) {
		this.receivedGoals += receivedGoals;
	}
	
	/**
	 * 
	 * PlayedMatches getter and Setter
	 */

	public int getPlayedMatches() {
		return playedMatches;
	}

	public void setPlayedMatches(int playedMatches) {
		this.playedMatches += playedMatches;
	}
	
	/**
	 * 
	 * Points getter and Setter
	 */

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points += points;
	}

	@Override
	public String toString() {
		return "FootballClub [name=" + name + ", location=" + location + ", id=" + id + ", wins=" + wins + ", draws="
				+ draws + ", defeats=" + defeats + ", scoredGoals=" + scoredGoals + ", receivedGoals=" + receivedGoals
				+ ", playedMatches=" + playedMatches + ", points=" + points + "]";
	}

	
}


