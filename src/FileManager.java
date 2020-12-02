import java.util.ArrayList;

public interface FileManager {
	public void writeDataToFile(ArrayList<FootballClub> array);
	public boolean isDataAvailable();
	public ArrayList<FootballClub> readDataFromFile();
}
