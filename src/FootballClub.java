import java.io.Serializable;

public class FootballClub extends SportClub implements Serializable {
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
	
	@Override
	public String toString() {
		return new StringBuffer("Name: ").append(this.name)
				.append("Location : ").append(this.location)
				.append("Wins : ").append(this.wins)
				.append("Draws: ").append(this.draws)
				.append("Defeats: ").append(this.defeats)
				.append("Scored goals: ").append(this.scoredGoals)
				.append("Received goals").append(this.receivedGoals)
				.append("Played matches: ").append(this.playedMatches)
				.append("Points: ").append(this.points)
				.toString();
	}
}


