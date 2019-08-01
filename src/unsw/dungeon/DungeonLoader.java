package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Edward Webb and William Shen
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    /**
     * The Dungeon Loader constructor
     * @param filename The file name of the dungeon
     * @throws FileNotFoundException
     */
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
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
        System.out.println("Initiating Dungeon");
        new DungeonCompletionObserver(dungeon);
        dungeon.initiateEnemyAI();
        return dungeon;
    }
    
    /**
     * Loads the goals from a JSON object of goals
     * @param dungeon The dungeon
     * @param goals The goals in JSON format
     */
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
    }
    /**
     * Loads an entity given the json object
     * @param dungeon The dungeon
     * @param json The json object
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "door":
        	Door door = new Door(x, y, json.getInt("id"));
        	onLoad(door);
        	entity = door;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "invincibility":
        	Invincibility invincibility = new Invincibility(x, y);
        	onLoad(invincibility);
        	entity = invincibility;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "key":
        	Key key = new Key(x, y, json.getInt("id"));
        	onLoad(key);
        	entity = key;
        	break;
        case "bomb":
        	UnlitBomb bomb = new UnlitBomb(x,y);
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x,y);
        	dungeon.addEnemy(enemy);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "switch":
        	FloorSwitch floorswitch = new FloorSwitch(x, y);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        // TODO Handle other possible entities
        }
        
        dungeon.addEntity(entity);
    }
    

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

	public abstract void onLoad(Door door);
	
	public abstract void onLoad(Boulder boulder);
	
	public abstract void onLoad(Treasure treasure);
	
	public abstract void onLoad(Invincibility invincibility);
	
	public abstract void onLoad(Sword sword);
	
	public abstract void onLoad(Key key);
	
	public abstract void onLoad(UnlitBomb bomb);
	
	public abstract void onLoad(Enemy enemy);
	
	public abstract void onLoad(Exit exit);
	
	public abstract void onLoad(FloorSwitch floorswitch);

}
