import java.util.ArrayList;

public interface FileManager {
	public boolean isDataAvailable(FileDataType fileDataType);
	
	public void writeFootballClubsToFile(ArrayList<FootballClub> array);
	public ArrayList<FootballClub> readFootballClubsFromFile();
	
	public void writePlayedGamesToFile(ArrayList<PlayedGame> array);
	public ArrayList<PlayedGame> readPlayedGamesFromFile();
}
