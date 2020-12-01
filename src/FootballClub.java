import java.io.Serializable;

public class FootballClub extends SportClub implements Serializable {
	private static final long serialVersionUID = 1L;

	int wins = 0;
	int draws = 0;
	int defeats = 0;
	int scoredGoals = 0;
	int receivedGoals = 0;
	int playedMatches = 0;
	int points = 0;
	
	public FootballClub(String name, String location) {
		this.name     = name;
		this.location = location;
	}
}


