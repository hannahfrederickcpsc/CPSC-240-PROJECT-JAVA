
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameState {

    public static class IllegalSaveFormatException extends Exception {
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

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
	    inventory = new ArrayList<Item>();
    }

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

        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_LEADER);
	w.println(CURRENT_ROOM_LEADER + 
            getAdventurersCurrentRoom().getTitle());
	if(!inventory.isEmpty()){
		w.println(INVENTORY_LEADER + this.getInventoryList() );
	}
	w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    Dungeon getDungeon() {
        return dungeon;
    }

    ArrayList<Item> getInventory()
    {
	    return inventory;
    }

    void  addToInventory(Item item)
    {
	    inventory.add(item);
    }

    void removeFromInventory(Item item)
    {
	    int index = inventory.indexOf(item);
	    inventory.remove(index);
    }

    Item getItemInVicinityNamed(String name)
    {
	    return adventurersCurrentRoom.getItemNamed(name);
    }

    Item getItemFromInventoryNamed(String name)
    {
	    int index = inventory.indexOf(dungeon.getItem(name));
            return inventory.get(index);
    }

    String getInventoryList()
    {
	    String inventoryList = "";
            for (Item  item: inventory)
            {
            inventoryList += item.getPrimaryName() + ",";
            }
            inventoryList = inventoryList.substring(inventoryList.length() - 1);

	    return inventoryList;

    }
}
