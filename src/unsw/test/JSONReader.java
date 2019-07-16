package unsw.test;

import java.io.FileNotFoundException;

import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Invincibility;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.UnlitBomb;
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
        	Door door = new Door(x, y, json.getInt("id"));
        	//onLoad(door);
        	entity = door;
        	break;
		case "boulder":
	     	Boulder boulder = new Boulder(x, y);
//	     	onLoad(boulder);
	     	entity = boulder;
	     	break;
	    case "treasure":
	     	Treasure treasure = new Treasure(x, y);
//	     	onLoad(treasure);
	     	entity = treasure;
	     	break;
	    case "invincibility":
	     	Invincibility invincibility = new Invincibility(x, y);
//	     	onLoad(invincibility);
	     	entity = invincibility;
	     	break;
	    case "sword":
	     	Sword sword = new Sword(x, y);
//	     	onLoad(sword);
	     	entity = sword;
	     	break;
	    case "key":
	     	Key key = new Key(x, y, json.getInt("id"));
//	     	onLoad(key);
	     	entity = key;
	     	break;
	    case "bomb":
	     	System.out.println("BOMB FOUND");
	     	UnlitBomb bomb = new UnlitBomb(x,y);
//	     	onLoad(bomb);
	     	entity = bomb;
	     	break;
        }
        dungeon.addEntity(entity);
    }
}
