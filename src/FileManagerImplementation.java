import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			FileOutputStream fileOutputStream = new FileOutputStream(this.dataFilePath, true);
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
	public void readTrainsFromFile(ArrayList<FootballClub> array) throws FileNotFoundException {
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
			array = tempArrayList;
			objectInputStream.close();
			fileInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
	}
}
