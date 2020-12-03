import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PlayedGame {
	private Date gameDate;
	private String gameResult;
	
	public PlayedGame(String playedAt, FootballClub fClub1, String fClub1Goals, FootballClub fClub2, String fClub2Goals) {
		this.setGameDate(playedAt);
		
	}
	/**
	 * @GameDate getter and Setter
	 */
	public Date getGameDate() {
		return gameDate;
	}
	
	public void setGameDate(String gameDate) {
		Date date;
		try {
			date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(gameDate);
			this.gameDate = date;
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	/**
	 * @GameResult getter and Setter
	 */
	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(FootballClub fClub1, String fClub1Goals, FootballClub fClub2, String fClub2Goals) {
		this.gameResult = fClub1.name + " " + fClub1Goals + "-" + fClub2Goals + " " + fClub2.name;
	}
}
