import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayedGame implements Serializable {
	/**
	 *  Unique object ID 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date gameDate;
	private String gameResult;
	
	public PlayedGame(String playedAt, FootballClub fClub1, int fClub1Goals, FootballClub fClub2, int fClub2Goals) {
		this.setGameDate(playedAt);
		this.setGameResult(fClub1, fClub1Goals, fClub2, fClub2Goals);
	}
	/**
	 * @GameDate getter and Setter
	 */
	public String getGameDate() {
		// Parse data format to string
		return new SimpleDateFormat("dd/MM/yyyy").format(this.gameDate);
	}
	
	private void setGameDate(String gameDate) {
		Date date;
		try {
			// Parse string to data format
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
		this.gameResult = fClub1.getName() + " " + fClub1Goals 
				+ "-" + fClub2Goals + " " + fClub2.getName();
	}
}
