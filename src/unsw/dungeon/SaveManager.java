package unsw.dungeon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Responsible for managing saves
 * @author Edward Webb and William Shen
 *
 */
public class SaveManager {
	private ArrayList<GameSave> saves;
	
	/**
	 * Constructor for SaveManager
	 */
	public SaveManager() {
		this.saves = this.loadAllSaves();
	}
	
	/**
	 * Saves a dungeon
	 * @param saveName The name of the save
	 * @param currentLevel The current level
	 * @param saveSlot Which slot to put the save in
	 */
	public void save(String saveName, int currentLevel, int saveSlot) {
		GameSave newGameSave = new GameSave(saveName, currentLevel, "");
		this.saves.set(saveSlot, newGameSave);
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		String saveFilePath = s + "/saves/save" + Integer.toString(saveSlot) + ".dat";
		try {
			FileWriter fw = new FileWriter(saveFilePath, false);
			fw.write(saveName + "\n");
			fw.write(Integer.toString(currentLevel) + "\n");
			fw.write(newGameSave.getTimeStamp());
			fw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Load a save
	 * @param saveFile The save file
	 * @return The actual game save
	 */
	private GameSave load(String saveFile) {
		ArrayList<String> fileContents = new ArrayList<String>();
		BufferedReader reader;
    	try {
    		System.out.println(saveFile);
    		reader = new BufferedReader(new FileReader(saveFile));
    		String line = reader.readLine();
    		while (line != null) {
    			fileContents.add(line);
    			line = reader.readLine();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	String saveName = fileContents.get(0);
		int currentLevel = Integer.parseInt(fileContents.get(1));
		String timestamp = fileContents.get(2);
		GameSave loadedSave = new GameSave(saveName, currentLevel, timestamp);
		return loadedSave;
	}
	
	/**
	 * Loads the list of saves
	 * @return The list of saves
	 */
	private ArrayList<GameSave> loadAllSaves() {
		ArrayList<GameSave> saves = new ArrayList<GameSave>();
		final File folder = new File("saves");
		System.out.println(folder.getAbsolutePath());
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile()) {
				GameSave saveFile = this.load(fileEntry.getAbsolutePath());
				saves.add(saveFile);
			}
		}
		return saves;
	}
	
	/**
	 * Gets the list of saves
	 * @return The list of saves
	 */
	public ArrayList<GameSave> getSavesList(){
		return this.saves;
	}
}
