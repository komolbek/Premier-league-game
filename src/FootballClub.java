import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FootballClub extends SportClub implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Private properties
	private int wins;
	private int draws;
	private int defeats;
	private int scoredGoals;
	private int receivedGoals;
	private int playedMatches;
	private int points;
	private Date createDate;

	//Constructor
	public FootballClub(String name, String location) {
		this.name     = name;
		this.location = location;
	}
	
	/**
	 * CreateDate getter and Setter
	 */
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String dateInString) {
		Date date;
		try {
			date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dateInString);
			this.createDate = date;
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Name getter and Setter
	 */
	
	@Override
	public String getName() { return super.getName(); }
	
	@Override
	public void setName(String name) { super.setName(name); }
	
	/**
	 * Location getter and Setter
	 */
	
	@Override
	public String getLocation() { return super.getLocation(); }
	
	@Override
	public void setLocation(String location) { super.setLocation(location); }
	
	/**
	 * Wins getter and Setter
	 */
	
	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins += wins;
	}
	
	/**
	 * Draws getter and Setter
	 */

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws += draws;
	}
	
	/**
	 * Defeats getter and Setter
	 */

	public int getDefeats() {
		return defeats;
	}

	public void setDefeats(int defeats) {
		this.defeats += defeats;
	}
	
	/**
	 * ScoredGoals getter and Setter
	 */

	public int getScoredGoals() {
		return scoredGoals;
	}

	public void setScoredGoals(int scoredGoals) {
		this.scoredGoals += scoredGoals;
	}
	
	/**
	 * ReceivedGoals getter and Setter
	 */

	public int getReceivedGoals() {
		return receivedGoals;
	}

	public void setReceivedGoals(int receivedGoals) {
		this.receivedGoals += receivedGoals;
	}
	
	/**
	 * PlayedMatches getter and Setter
	 */

	public int getPlayedMatches() {
		return playedMatches;
	}

	public void setPlayedMatches(int playedMatches) {
		this.playedMatches += playedMatches;
	}
	
	/**
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


