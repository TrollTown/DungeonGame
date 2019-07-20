package unsw.test;

import java.io.FileNotFoundException;

import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.AndGoal;
import unsw.dungeon.Boulder;
import unsw.dungeon.BoulderGoal;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.EnemyGoal;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Invincibility;
import unsw.dungeon.Key;
import unsw.dungeon.OrGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.TreasureGoal;
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
		JSONObject goals = json.getJSONObject("goal-condition");
        loadGoals(dungeon, goals);
		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}
		return dungeon;
	}
	 private void loadGoals(Dungeon dungeon, JSONObject goals) {
	    	String mainGoal = goals.getString("goal");
	    	switch (mainGoal) {
	    	case "exit":
	    		dungeon.setGoal(new ExitGoal());
	    		break;
	    	case "enemies":
	    		dungeon.setGoal(new EnemyGoal());
	    		break;
	    	case "treasure":
	    		dungeon.setGoal(new TreasureGoal());
	    		break;
	    	case "boulders":
	    		dungeon.setGoal(new BoulderGoal());
	    		break;
	    	case "AND":
	    		dungeon.setGoal(new AndGoal(goals.getJSONArray("subgoals")));
	    		break;
	    	case "OR":
	    		dungeon.setGoal(new OrGoal(goals.getJSONArray("subgoals")));
	    		break;
	    	}
	    	System.out.println(dungeon.getGoal());
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
		case "exit":
	    	Exit exit = new Exit(x, y);
//	    	onLoad(exit);
	    	entity = exit;
	    	break;
        case "switch":
        	FloorSwitch floorswitch = new FloorSwitch(x, y);
//        	onLoad(floorswitch);
        	entity = floorswitch;
        }
        dungeon.addEntity(entity);
    }
}
