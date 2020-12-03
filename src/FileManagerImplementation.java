import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManagerImplementation implements FileManager {
	
	/**
	 * @footballClubsFilePath 
	 */
	
	private String footballClubsFilePath;
	
	/**
	 * @playedGamesFilePath
	 */
	
	private String playedGamesFilePath;
	
	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	
	/**
	 * @pCONSTRUCTOR
	 */
	
	public FileManagerImplementation() {
		this.footballClubsFilePath = "src/footballClub";
		this.playedGamesFilePath = "src/playedGames";
	}
	
	/**
	 * @FootballClubs write / read to / from file methods
	 */
	
	@Override
	public void writeFootballClubsToFile(ArrayList<FootballClub> array) {
		try {
			this.fileOutputStream = new FileOutputStream(this.footballClubsFilePath, false);
			this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
			this.objectOutputStream.writeInt(array.size());
			for (FootballClub footballClub : array) {
				if (footballClub != null) {
					objectOutputStream.writeObject(footballClub);;
				}
			}
			System.out.println("###### SUCCESS ###### Data loaded to the file");
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
	}

	@Override
	public ArrayList<FootballClub> readFootballClubsFromFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(this.footballClubsFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			int footballClubsCount = objectInputStream.readInt();
			ArrayList<FootballClub> tempArrayList = new ArrayList<FootballClub>();
			System.out.println();
			for (int i = 0; i < footballClubsCount; i++) {
				tempArrayList.add((FootballClub) objectInputStream.readObject());
			}
			if (tempArrayList.size() > 1) {
				System.out.println("###### SUCCESS ###### Data loaded from the file");
			}
			objectInputStream.close();
			fileInputStream.close();
			return tempArrayList;
		} catch (IOException | ClassNotFoundException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
		return new ArrayList<FootballClub>();
	}
	
	/**
	 * @PlayedGames write / read to / from file methods
	 */
	
	@Override
	public void writePlayedGamesToFile(ArrayList<PlayedGame> array) {
		try {
			this.fileOutputStream = new FileOutputStream(this.playedGamesFilePath, false);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
			this.objectOutputStream.writeInt(array.size());
			for (PlayedGame playedGame : array) {
				if (playedGame != null) {
					objectOutputStream.writeObject(playedGame);;
				}
			}
			System.out.println("###### SUCCESS ###### Data loaded to the file");
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
	}
	
	@Override
	public ArrayList<PlayedGame> readPlayedGamesFromFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(this.playedGamesFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			int footballClubsCount = objectInputStream.readInt();
			ArrayList<PlayedGame> tempArrayList = new ArrayList<PlayedGame>();
			System.out.println();
			for (int i = 0; i < footballClubsCount; i++) {
				tempArrayList.add((PlayedGame) objectInputStream.readObject());
			}
			if (tempArrayList.size() > 1) {
				System.out.println("###### SUCCESS ###### Data loaded from the file");
			}
			objectInputStream.close();
			fileInputStream.close();
			return tempArrayList;
		} catch (IOException | ClassNotFoundException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
		return new ArrayList<PlayedGame>();
	}
	
	/**
	 * Check if the file exists and has data in it. Returning boolean.
	 */
	
	@Override
	public boolean isDataAvailable(FileDataType fileDataType) {
		File file;
		switch (fileDataType) {
		case FOOTBALL_CLUBS: {
			file = new File(this.footballClubsFilePath);
			if (file.exists() || file.length() > 0) {
				return true;
			} else {
				return false;
			}
		}
		case PLAYED_GAMES: {
			file = new File(this.playedGamesFilePath);
			if (file.exists() || file.length() > 0) {
				return true;
			} else {
				return false;
			}
		}
		}
		
		return false;
	}
}
