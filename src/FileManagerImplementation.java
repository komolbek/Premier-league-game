import java.io.File;
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
		this.dataFilePath = "src/footballClub";
	}
	
	@Override
	public void writeDataToFile(ArrayList<FootballClub> array) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(this.dataFilePath, false);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeInt(array.size());
			for (FootballClub footballClub : array) {
				if (footballClub != null) {
					System.out.println(footballClub.toString());
					objectOutputStream.writeObject(footballClub);;
				}
			}
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
	}

	@Override
	public ArrayList<FootballClub> readDataFromFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(this.dataFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			int footballClubsCount = objectInputStream.readInt();
			ArrayList<FootballClub> tempArrayList = new ArrayList<FootballClub>();
			System.out.println();
			for (int i = 0; i < footballClubsCount; i++) {
				tempArrayList.add((FootballClub) objectInputStream.readObject());
				System.out.println(tempArrayList.get(i).toString());
			}
			objectInputStream.close();
			fileInputStream.close();
			return tempArrayList;
		} catch (IOException | ClassNotFoundException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
		return new ArrayList<FootballClub>();
	}
	
	@Override
	public boolean isDataAvailable() {
		File file = new File(this.dataFilePath);
		if (file.exists() || file.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
