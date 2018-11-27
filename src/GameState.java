import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
/** 
*Gamestate keeps a continuous record of the current state of the game, such as the players current room, their inventory, and the commands that can be played on each item..
*
*@author Isabella
*/
public class GameState {
/** 
*IllegalSaveFormatException is called if when reading in a file it is not in the correct format and cannot be read into the program. Extends Exception.
*
*@author Isabella
*/
    public static class IllegalSaveFormatException extends Exception {
/**
*IllegalSaveFormatException is called if when reading in a file it is not in the correct format and cannot be read into the program. 
*@param e the string to be displayed when an IllegalSaveFormatException is thrown.
*/
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "zork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork III save data";

    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String ADVENTURER_LEADER = "Adventurer: ";
    static String INVENTORY_LEADER = "Inventory: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private Room adventurersCurrentRoom;
    private ArrayList<Item> inventory;
    private ArrayList<String> verbs;
    private int adventurersHealth;
    private int adventurersScore;
    private int adventurersMoves;
    private ArrayList<NPC> allNonPlayerCharacters;
    private int adventurersHunger;
/**
*Returns the game's one instance of the GameState object. When called, if GameState has not been instantiated, the method will create the new object. If the object has alrady been instantiated, it will return the one GameState object.
*@return the game's one GameState object.
*/
    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }
/**
*Constructs GameState by instantiating GameState's inventory and verbs fields.
*/
    private GameState() {
	    inventory = new ArrayList<Item>();
	    verbs = new ArrayList<String>();
	    this.adventurersHealth = 100;
	    this.adventurersScore = 0;
    }
/**
*Restores the game's state by reading in information from a specified file and instantiating GameState's fields. This method is able to throw FileNotFoundException, IllegalSaveFormatException, and IllegalDungeonFormatException. The exceptions IllegalSaveFormatException and IllegalDungeonFormatException are thrown depending on the extention of the file you specify to restore GameState. 
*@param filename the string of the name of the file GameState is being initialized to.
*@throws FileNotFoundException
*@throws IllegalSaveFormatException
*@throws IllegalDungeonFormatException
*/
    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()),false);
        dungeon.restoreState(s);
	String adventurerLine = s.nextLine();
	String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
        currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
	this.adventurersScore = Integer.valueOf(s.nextLine().replace("Score: ", ""));
	this.adventurersHealth = Integer.valueOf(s.nextLine().replace("Health: ", ""));
	if(s.hasNext()){
	String lastLine = s.nextLine().replace("Inventory: ","");
	String [] inventorySplit = lastLine.split(",");
	for (String items: inventorySplit){
		this.addToInventory(dungeon.getItem(items));
		}
	}
    }
/**
*Saves the game's data to a default .sav file. 
*       
*@throws IOException
*/
    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

/**
*Saves the game's data to a .sav file chosen by the user.
*@param saveName a string that refers to the desired name of the .sav file. 
*@throws IOException
*/
    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_LEADER);
	w.println(CURRENT_ROOM_LEADER + 
        getAdventurersCurrentRoom().getTitle());
	w.println("Score: " + this.adventurersScore);
	w.println("Health: " + this.adventurersHealth);
	if(!inventory.isEmpty()){
		w.println(INVENTORY_LEADER + this.getInventoryList() );
	}
	w.close();
    }

/**
*Initializes GameState by initializing the Dungeon and the adventurers current room.
*         
*@param dungeon a dungeon object that initializes GameState.
*/
    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }
/**
*Returns the room that the adventurer is currently in.
*         
*@return the room that the adventurer is currently in.
*/
    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }
/**
*Sets the adventuter's current room to the paramater.
*         
*@param room the romm that you want the adventurer's current room the be.
*/
    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }
/**
*Returns the Dungeon object GameState was initialized to.
*         
*@return the dungeon object that GameState was initialized to.
*/
    Dungeon getDungeon() {
        return dungeon;
    }
/**
*Returns an array list of strings of all of the SpecificCommandItem commands for each item.
*         
*@return an array list of strings that contains all of the verb commands that can be played on every item..
*/
    public ArrayList<String> getVerbs(){
   	for(Item item: dungeon.getItemList()){
		for(String verb: item.getVerbs())
		{
			verbs.add(verb);
		}
	}
	return verbs;
    }
/**
*Returns all of the items in your inventory.
*         
*@return an array list of items that are in your inventory
*/
    ArrayList<Item> getInventory()
    {
	    return inventory;
    }
/**
*Adds the specific item to your inventory
*         
*@param item the name of a specific item object.
*/

    void  addToInventory(Item item)
    {
            inventory.add(item);
    }

/**
*Removes a specific item from your inventory.
*         
*@param item the name of a specific item object.
*/
    void removeFromInventory(Item item){
            int index = inventory.indexOf(item);
            inventory.remove(index);
    }
/**
*Returns the item named in the adventurer's current room.
*         
*@param name the name of a specific item.
*/
    Item getItemInVicinityNamed(String name)
    {
            if(adventurersCurrentRoom.getContents().contains(dungeon.getItem(name)))
            {
                return adventurersCurrentRoom.getItemNamed(name);
            }
                return null;
    }
/**
*Returns the item object named from the adventurer's inventory.
*         
*@param name the name of a specific item.
*/

    Item getItemFromInventoryNamed(String name)
    {
            if (inventory.contains(dungeon.getItem(name)))
            {
                int index = inventory.indexOf(dungeon.getItem(name));
                return inventory.get(index);
            }
            return null;
    }
/**
*Returns a list of all of the items in your inventory.
*         
*@return a string of all items in your inventory.
*/
    String getInventoryList()
    {
            String inventoryList = "";
            if(!inventory.isEmpty()){
            for (Item  item: inventory)
            {
            inventoryList += item.getPrimaryName() + ",";
            }
            inventoryList = inventoryList.substring(0,inventoryList.length() - 1);
            }
            return inventoryList;
    }
/**
*Returns an array list of all NonPlayerCharacters.
*@returns an array list of all NonPlayerCharacters.
*/  
    public ArrayList<NPC> getAllNonPlayerCharacters(){
	    return null;
    	}
/**
*Returns the room that a specific NonPlayerCharacter is in currently.
*@param NPCName the name of the specific NonPlayerCharacter.
*@return the room object that the NonPlayerCharcter is in currently.
*/ 
    public Room getNonPlayerCharacterCurrRoom(String NPCName){
 		return null;
    	}
/**
*Sets the NonPlayerCharacter's room to the specified room.         
*@param room the room you want to set the NonPlayerCharacter's room to.
*/
    public void setNonPlayerCharacterCurrRoom(Room room){
    	}
/**
*Returns the NPC object of a specified NonPlayerCharacter in the adventurer's current room.
*@param NPCName the name of the specific NonPlayerCharacter.
*@return the NPC object in the adventurer's current room.
*/
    public NPC getNPCInVicinity(String NPCName){
    		return null;
    	}
/**
*Returns the adventurer's current score.
*@return the integer of the adventurer's current score.
*/
    public int getAdventurersScore(){
    		return this.adventurersScore;
    	}
/**
*Returns the adventurer's current  health.
*@return the integer of the adventurer's current health.
*/
    public int getAdventurersHealth(){
    		return this.adventurersHealth;
    	}
/**
*Returns the output of the program when the adventurer dies.
*@return the string of the output of the program when the adventurer dies.
*/
    public String die(){
    		return null;
    	}
/**
*Returns the output of the program when the specified NonPlayerCharacter dies.
*@param NPCName the specified NonPlayerCharacter.
*@return the string of the output of the program when the adventurer dies.
*/
    public String die(String NPCName){
    		return null;
    	}
/**
*Changes the adventurer's current score by a specified amount.
*@param numChange the intger to increment or decrement the adventurer's score by.
*/
    public void changeScore(int numChange){
    		this.adventurersScore += numChange;
    	}
/**
*Changes the adventurer's current health by a specified amount.
*@param numChange the intger to increment or decrement the adventurer's health by.
*/
    public void changeHealth(int numChange){
    		this.adventurersHealth += numChange;
    	}
/**
*Changes the adventurer's current hunger by a specified amount.
*@param numChange the intger to increment or decrement the adventurer's hunger by.
*/
    public void changeHunger(int numChange){
    		this.adventurersHunger += numChange;
    	}
/**
*Increments the adventurer's moves by one.
*/
    public void incrementMoves(){
        this.adventurersMoves++;
    }
/**
*Returns the adventurer's current number of moves.
*@return the integer of the adventurer's current number of moves.
*/
    public int getMoves(){
    		return this.adventurersMoves;
    	}
/**
*Returns the adventurer's current amount of health points.
*@return the integer of the adventurer's current amount of health points.
*/
    public int getHungerPoints(){
    		return this.adventurersHunger;
    	}
}
