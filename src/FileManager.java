/**
 * @author Kamol
 */
import java.util.ArrayList;

/**
 * @FileManager is an interface that 
 * provides an API to interact with file 
 * system/
 */
public interface FileManager {
	
	/**
	 * This method takes the file type as parameter
	 * and the checks is there any files and data in 
	 * it. 
	 */
	public boolean isDataAvailable(FileDataType fileDataType);
	
	/**
	 * This method writes Football clubs data to the file. 
	 */
	public void writeFootballClubsToFile(ArrayList<FootballClub> array);
	
	/**
	 * This method reads Football clubs data from the file. 
	 */
	public ArrayList<FootballClub> readFootballClubsFromFile();
	
	/**
	 * This method writes Played Game data to the file. 
	 */
	public void writePlayedGamesToFile(ArrayList<PlayedGame> array);
	
	/**
	 * This method reads Played Game data from the file. 
	 */
	public ArrayList<PlayedGame> readPlayedGamesFromFile();
}
