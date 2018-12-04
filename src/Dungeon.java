import java.util.Set;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
/**
 * A dungeon represents the playable environment that is able to be
 * manually traversed by the adventurer. A dungeon is broken up into 
 * playable {@link Room} areas that allow the adventurer to traverse
 * the dungeon.
 *
 * @author Matt 
 */
public class Dungeon {
/**
*An IllegalDungeonFormatException is thrown when the syntax of a .zork file that os being read into the program is writen in a way that the program cannot read in the .zork file. This class extends Exception.
*
*@author Matt
*/

    public static class IllegalDungeonFormatException extends Exception {
/**
*IllegalDungeonFormatException is called when the syntax of a .zork file that os being read into the program is 
writen in a way that the program cannot read in the .zork file.
*@param e the error message that is displayed when IllegalDungeonFormatException is thrown.
*/
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }

    // Variables relating to both dungeon file and game state storage.
    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.zork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";
    // Variables relating to game state (.sav) storage.
    static String FILENAME_LEADER = "Dungeon file: ";
    static String ROOM_STATES_MARKER = "Room states:";
    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;
    private Hashtable<String,Item> items;
    private ArrayList<Item> itemList;
    private String filename;
    private ArrayList<NPC> allNonPlayerCharacters;
    private ArrayList<WeatherEvent> weatherEvents;
    private ArrayList<WeatherEvent> triggeredWeatherEvents;
    private int weatherEventCount;

    /**
     * Constructs a new <tt>Dungeon</tt> object given the name and 
     * room that the adventurer is first placed in. This creates
     * a dungeon with only a name and entry room. 
     *
     * @param name Name of the dungeon.
     * @param entry Room the adventurer is first placed in.
     *
     */
    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
        rooms = new Hashtable<String,Room>();
	items = new Hashtable<String,Item>();
    }

    /**
     * Constructs a new <tt>Dungeon</tt> object that reads in 
     * information to fill up the dungeon from a .zork file. 
     *
     * @param filename Name of the .zork file to be read.
     * 
     * @param initState Truth value that allows the system to know if it
     * needs to restore the game from a save file, or start from scratch
     * from a .zork file.
     *
     * @throws FileNotFoundException
     * @throws IllegalDungeonFormatException
     */
    public Dungeon(String filename, boolean initState) throws FileNotFoundException,
        IllegalDungeonFormatException{

        init();
	this.weatherEvents = new ArrayList<WeatherEvent>();
	this.allNonPlayerCharacters = new ArrayList<NPC>();
        this.filename = filename;

        Scanner s = new Scanner(new FileReader(filename));
        name = s.nextLine();

        s.nextLine();   // Throw away version indicator.

        // Throw away delimiter.
       if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                TOP_LEVEL_DELIM + "' after version indicator.");
        }

        // Throw away Items starter.
       if (!s.nextLine().equals(ITEMS_MARKER)) {
	    throw new IllegalDungeonFormatException("No '" + 
		ITEMS_MARKER + "' line where expected.");
	}
	
       try {
            //Instantiate and add items
            while (true){
                	add(new Item(s));
            }	
        } 
       catch (Item.NoItemException e) { /* end of items */}
	String NPCHeader = s.nextLine();
	
 	if(!NPCHeader.equals("NPCs:")){
		throw new IllegalDungeonFormatException("Np NPC header where expected");
	}	

       try{
	       while(true){
		       this.allNonPlayerCharacters.add(NPCFactory.instance().parse(s, this));
		      
				       
	       }

       }
	       catch(NPCFactory.NoNPCException e){}
		     

        // Throw away Rooms starter.
        if (!s.nextLine().equals(ROOMS_MARKER)) {
           throw new IllegalDungeonFormatException("No '" +
                ROOMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate and add first room (the entry).
            entry = new Room(s,this,initState);
            add(entry);

            // Instantiate and add other rooms.
            while (true) {
		    Room room = new Room(s,this,initState);
		    add(room);
            }
        } catch (Room.NoRoomException e) {  /* end of rooms */ }

         //Throw away Exits starter.
        if (!s.nextLine().equals(EXITS_MARKER)) {
           throw new IllegalDungeonFormatException("No '" +
                EXITS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate exits.
            while (true) {
                // (Note that the Exit constructor takes care of adding itself
                // to its source room.)
                Exit exit = new Exit(s, this);
            }
        } catch (Exit.NoExitException e) {  /* end of exits */ }
	if(!s.nextLine().equals("Weather Events:")){
		throw new IllegalDungeonFormatException("No 'Weather Events:' line where expected.");
	}
	try {
		while(true){
			WeatherEvent w = new WeatherEvent(s, this);
			this.weatherEvents.add(w);
		}
	} catch (WeatherEvent.NoWeatherException e) {}
        s.close();
    
}
    
    // Common object initialization tasks, regardless of which constructor
    // is used.
	/**
	 * Initializes instance variables.
	 */
    private void init() {
        rooms = new Hashtable<String,Room>();
	items = new Hashtable<String,Item>();
	itemList = new ArrayList<Item>();
	this.weatherEventCount = 0;
	this.triggeredWeatherEvents = new ArrayList<WeatherEvent>();
    }

    /*
     * Store the current (changeable) state of this dungeon to the writer
     * passed.
     */
	/**
	 * Stores the current state of the dungeon into a 
	 * save file. This ONLY stores the dungeon-specific parts 
	 * to the file.
	 *
	 * @param w PrintWriter to write to the save file.
	 *
	 * @throws IOException
	 */
    void storeState(PrintWriter w) throws IOException {
	String line = "";
        w.println(FILENAME_LEADER + getFileObject().getAbsolutePath());
	w.println("Weather Event trigger count: " + this.weatherEventCount);
	w.print("Triggered Weather Events: ");
	if(this.triggeredWeatherEvents.size() > 1){
	for(WeatherEvent e: this.triggeredWeatherEvents){
		line += e.getName() + ",";
	}
	w.print(line.substring(0,line.length() -1));
	}
	else if(this.triggeredWeatherEvents.size() == 1){
		w.print(this.triggeredWeatherEvents.get(0).getName());
	}
	w.println();
	w.println("NPC States:");
	for(NPC npc: this.allNonPlayerCharacters){
		npc.storeState(w);
	}
	w.println("===");
	

        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /*
     * Restore the (changeable) state of this dungeon to that reflected in the
     * reader passed.
     */
	/**
	 * Restores the state of the dungeon from a save file. This ONLY 
	 * restores the dungeon-specific aspects of the save file.
	 *
	 * @param s Scanner pointing to the exact starting point of the 
	 * dungeon-specific aspects in the save file.
	 *
	 * @throws GameState.IllegalSaveFormatException
	 */
    void restoreState(Scanner s) throws GameState.IllegalSaveFormatException {

        // Note: the filename has already been read at this point.
	    this.weatherEventCount = Integer.valueOf(s.nextLine().replace("Weather Event trigger count: ",""));
	    String triggeredEvents = s.nextLine().replace("Triggered Weather Events: ","");
	    if(triggeredEvents.equals("")){
	    }
	    else{
		String [] events = triggeredEvents.split(",");
		for(String f: events){
	    		this.triggeredWeatherEvents.add(getWeatherEvent(f));
			WeatherGenerator w = new WeatherGenerator(getWeatherEvent(f));
			w.execute();
		}
	    }
	    String npcStates = s.nextLine();
	    if(!npcStates.equals("NPC States:")){
		    throw new GameState.IllegalSaveFormatException("No NPC States: where expected.");
	    }
	    npcStates = s.nextLine();
	    while(!npcStates.equals(TOP_LEVEL_DELIM)){
		    NPC npc = getNPC(npcStates);
		    npc.restoreState(s,this);
		    npcStates = s.nextLine();
	    }

        
        if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
            throw new GameState.IllegalSaveFormatException("No '" +
                ROOM_STATES_MARKER + "' after dungeon filename in save file.");
        }

        String roomName = s.nextLine();
        while (!roomName.equals(TOP_LEVEL_DELIM)) {
            getRoom(roomName.substring(0,roomName.length()-1)).restoreState(s,this);
            roomName = s.nextLine();
        }
    }
	/**
	 * Returns the starting room of the dungeon.
	 *
	 * @return Entry room of the dungeon.
	 */
    public Room getEntry() { return entry; }
    	/**
	 * Returns the name of the dungeon.
	 *
	 * @return Name of the dungeon.
	 */
    public String getName() { return name; }
    	/**
	 * Returns the file name of the .zork file which hydrates the 
	 * dungeon.
	 *
	 * @return The file name of the .zork file that hydrated the
	 * dungeon.
	 */
    public String getFilename() { return filename; }
    	/**
	 * Returns the actual <tt>File</tt> object of the .zork file
	 * that hydrated the dungeon.
	 *
	 * @return <tt>File</tt> object of the .zork file that hydrated
	 * the dungeon.
	 */
    public File getFileObject()
    {
	File dungeonFile =  new File(getFilename());
	return dungeonFile;
    }
	/**
	 * Adds the given room to the dungeon.
	 *
	 * @param room Room to be added to the dungeon.
	 */
    public void add(Room room) { rooms.put(room.getTitle(),room); }
	/**
	 * Returns a room from the dungeon given the room name.
	 *
	 * @param roomTitle Name of the room to be returned.
	 *
	 * @return Room that has the name that was given.
	 */
    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }
	/**
	 * Adds an Item to the dungeons collection of Items.
	 *
	 * @param item Item to be added to the dungeon.
	 */
    public void add(Item item){
	items.put(item.getPrimaryName(),item);
    }
	/**
	 * Returns an Item from the dungeon given an Item name.
	 * If the item is non-existent, nothing is returned.
	 *
	 * @param primaryName Name of the Item to be returned.
	 *
	 * @return Item that has the given name.
	 */
    public Item getItem(String primaryName){
	Set<String> keys = items.keySet();
	for (String key: keys){
		if(items.get(key).goesBy(primaryName)){
			return items.get(key);
		}
	}
		return null;
    }
	/**
	 * Returns the master collection of Items in the dungeon.
	 *
	 * @return Master ArrayList of Items in the dungeon.
	 */
    public ArrayList<Item> getItemList(){
	Set<String> tableKeys = items.keySet();
	for(String key: tableKeys)
	{
		itemList.add(items.get(key));
	} 

	return itemList;
    }
	public void removeItem(Item item)
	{
		itemList.remove(item);
	}	

	/**
      	 * Returns a NPC given the NPC name.
	 *
	 * @param NPCName Name of the NPC wanting to be returned.
	 *
	 * @return NPC that has the given name.
	 */	 
    public NPC getNPC(String NPCName){
	    for(NPC npc: this.allNonPlayerCharacters){
		    if(npc.getProperName().equals(NPCName)){
			    return npc;
		    }
	    }
	    return null;
    	}
    	/**
	 * Returns a random <tt>WeatherEvent</tt> object.
	 *
	 * @return Random <tt>WeatherEvent</tt> object.
	 */
    public WeatherEvent getRandomWeatherEvent(){
    		if(this.weatherEventCount < this.weatherEvents.size()){
	    		Random rand = new Random();
			int randNum = rand.nextInt(this.weatherEvents.size());
			WeatherEvent event = this.weatherEvents.get(randNum);
			while(this.triggeredWeatherEvents.contains(event)){
				randNum = rand.nextInt(this.weatherEvents.size());
				event = this.weatherEvents.get(randNum);
			}
			this.triggeredWeatherEvents.add(event);
			this.weatherEventCount++;
			return event;
		}
		return null;
    	}
    	/**
	 * Returns the master collection of the <tt>WeatherEvent</tt> 
	 * objects in the dungeon.
	 *
	 * @return ArrayList of <tt>WeatherEvent</tt> objects in the 
	 * dungeon.
	 */
    public ArrayList<NPC> getWeatherEvents(){
    	return null;	
    	}
    	/**
	 * Returns a random playable room within the dungeon.
	 *
	 * @return Random playable room within the dungeon.
	 */
    public Room getRandomRoom(){
	Random rand = new Random();
	int randNum = rand.nextInt(this.rooms.size());
	Set<String> keys = rooms.keySet();
	ArrayList<String> keysList = new ArrayList<String>();
	for(String o: keys){
		keysList.add(o);
	}
	return getRoom(keysList.get(randNum));
    }
    
    public WeatherEvent getWeatherEvent(String eventName){
	    for(WeatherEvent w: this.weatherEvents){
	    	if(w.getName().equals(eventName)){
			return w;
		}
	    } 
	    return null;
    }

    public Hashtable<String,Room> getRooms(){
	    return this.rooms;
    }

	

}
