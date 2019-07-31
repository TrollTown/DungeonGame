package unsw.dungeon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveManager {
	private ArrayList<GameSave> saves;
	
	public SaveManager() {
		this.saves = this.loadAllSaves();
	}
	
	public void save(String saveName, int currentLevel, int saveSlot) {
		GameSave newGameSave = new GameSave(saveName, currentLevel, "");
		this.saves.add(newGameSave);
		String saveFilePath = "/saves/" + Integer.toString(saveSlot) + ".dat";
		try {
			FileWriter fw = new FileWriter(saveFilePath, false);
			fw.write(saveName + "\n");
			fw.write(Integer.toString(saveSlot) + "\n");
			fw.write(newGameSave.getTimeStamp());
			fw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
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
	
	public ArrayList<GameSave> getSavesList(){
		return this.saves;
	}
}
