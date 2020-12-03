import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayedGame {
	private Date gameDate;
	private String gameResult;
	
	public PlayedGame(String playedAt, FootballClub fClub1, int fClub1Goals, FootballClub fClub2, int fClub2Goals) {
		this.setGameDate(playedAt);
		this.setGameResult(fClub1, fClub1Goals, fClub2, fClub2Goals);
	}
	/**
	 * @GameDate getter and Setter
	 */
	public Date getGameDate() {
		return gameDate;
	}
	
	private void setGameDate(String gameDate) {
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

	private void setGameResult(FootballClub fClub1, int fClub1Goals, FootballClub fClub2, int fClub2Goals) {
		this.gameResult = fClub1.name + " " + fClub1Goals 
				+ "-" + fClub2Goals + " " + fClub2.name 
				+ " in " + String.valueOf(this.gameDate);
	}
}
