package unsw.dungeon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSave {
	private String saveName;
	private int currentLevel;
	private String timestamp;
	
	private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public GameSave(String saveName, int currentLevel, String timestamp) {
		this.saveName = saveName;
		this.currentLevel = currentLevel;
		if (timestamp == "") {
			Date date = new Date();
			this.timestamp = sdf.format(date);
		}
		else {
			this.timestamp = timestamp;
		}
	}
	
	public String getTimeStamp() {
		return this.timestamp;
	}
}
