import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManagerImplementation implements FileManager {
	
	/**
	 * @dataFilePath 
	 */
	
	private String dataFilePath;
	
	public FileManagerImplementation() {
		this.dataFilePath = "src/footballClubList.txt";
	}
	
	@Override
	public void writeDataToFile(ArrayList<FootballClub> array) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(this.dataFilePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeInt(array.size());
			for (FootballClub footballClub : array) {
				if (footballClub != null) {
					objectOutputStream.writeObject(footballClub);
				}
			}
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readTrainsFromFile(ArrayList<FootballClub> array) {
		try {
			FileInputStream fileInputStream = new FileInputStream(this.dataFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			int trainCount = objectInputStream.readInt();
			for (int i = 0; i < trainCount; i++) {
				array.add((FootballClub) objectInputStream.readObject());
				System.out.println(array.get(i).toString());
			}
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
