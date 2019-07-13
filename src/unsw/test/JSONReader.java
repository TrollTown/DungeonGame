package unsw.test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

public class JSONReader {
	private JSONObject json;
	
	public JSONReader (String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}
	
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");
		
		Dungeon dungeon = new Dungeon(width, height);
		
		JSONArray jsonEntities = json.getJSONArray("entities");
		
		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}
		return dungeon;
	}
	
	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            //onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            //onLoad(wall);
            entity = wall;
            break;
        case "door":
        	Door door = new Door(x, y);
        	//onLoad(door);
        	entity = door;
        	break;
        }
        dungeon.addEntity(entity);
    }
}	
