import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManagerImplementation implements FileManager {
	
	/**
	 * @footballClubsFilePath is a string file path
	 */
	
	private String footballClubsFilePath;
	
	/**
	 * @playedGamesFilePath is a string file path
	 */
	
	private String playedGamesFilePath;
	
	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	
	/**
	 * @CONSTRUCTOR
	 */
	
	public FileManagerImplementation() {
		// Assign file path to footballClubsFilePath
		this.footballClubsFilePath = "src/footballClub";
		
		// Assign file path to playedGamesFilePath
		this.playedGamesFilePath = "src/playedGames";
	}
	
	/**
	 * @FootballClubs write / read to / from file methods
	 */
	
	@Override
	public void writeFootballClubsToFile(ArrayList<FootballClub> array) {
		try {
			// Try to create object of fileOutputStream using file path contained in footballClubsFilePath  
			this.fileOutputStream = new FileOutputStream(this.footballClubsFilePath, false);
			
			// Try to create objectOutputStream using created fileOutputStream
			this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			// Save number of objects that should be written in the file, to make file reading comfortable
			this.objectOutputStream.writeInt(array.size());
			
			// Save each object separately with own data, using for loop iteration
			for (FootballClub footballClub : array) {
				
				// Check if the object is not null
				if (footballClub != null) {
					
					// Save / Write object into the file
					objectOutputStream.writeObject(footballClub);;
				}
			}
			
			// Let user to know in console that all objects are written / saved into the file
			System.out.println("###### SUCCESS ###### Football Clubs data loaded to the file");
			
			// Close objectOutputStream sockets preventing from future fail
			objectOutputStream.close();
			
			// Close fileOutputStream sockets preventing from future fail
			fileOutputStream.close();
			
			// If error occurs while reading data catch and handle it
		} catch (IOException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
	}

	@Override
	public ArrayList<FootballClub> readFootballClubsFromFile() {
		try {
			// Try to create object of fileInputStream using file path contained in footballClubsFilePath  
			FileInputStream fileInputStream = new FileInputStream(this.footballClubsFilePath);
			
			// Try to create objectInputStream using created fileOutputStream
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			
			// Read number of object in the file and assign to footballClubsCount variable
			int footballClubsCount = objectInputStream.readInt();
			
			// Define temporary arrayList type of FootballClub
			ArrayList<FootballClub> tempArrayList = new ArrayList<FootballClub>();
			
			// make a line break
			System.out.println();
			
			// Depending on footballClubsCount iterate file object 
			for (int i = 0; i < footballClubsCount; i++) {
				
				// Add read objects to the created tempArrayList
				tempArrayList.add((FootballClub) objectInputStream.readObject());
			}
			
			// Check if there is any data read from the file
			if (tempArrayList.size() > 1) {
				
				// Let user know in console that data is successfully read
				System.out.println("###### SUCCESS ###### Football Clubs data loaded from the file");
			}
			
			// Close objectInputStream sockets preventing from future fail
			objectInputStream.close();
			
			// Close fileInputStream sockets preventing from future fail
			fileInputStream.close();
			
			// Return tempArrayList with read data
			return tempArrayList;
			
			// If error occurs while reading data catch and handle it
		} catch (IOException | ClassNotFoundException e) {
			System.out.printf("### ERROR ### %s", e.getLocalizedMessage());
		}
		
		// If some error happens or no data, return an empty arrayList by default
		return new ArrayList<FootballClub>();
	}
	
	//TODO: implement write and read of played matches 
	
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
			System.out.println("###### SUCCESS ###### Played games loaded to the file");
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
				System.out.println("###### SUCCESS ###### Played games loaded from the file");
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
