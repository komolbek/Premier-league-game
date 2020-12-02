import java.util.ArrayList;

public interface FileManager {
	public void writeDataToFile(ArrayList<FootballClub> array, String file);
	public void readTrainsFromFile(ArrayList<FootballClub> array, String file);
}
