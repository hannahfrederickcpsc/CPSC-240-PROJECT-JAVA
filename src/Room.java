
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * A <tt>Room</tt> represents a specific area within the playable dungeon that can hold
 * <tt>Item</tt>, <tt>Exit</tt>, and <tt>NPC</tt> objects. Each <tt>Room</tt> can be 
 * accessed by an <tt>Exit</tt> by the adventurer.
 *
 * @author zorkaholics
 */
public class Room {

    class NoRoomException extends Exception {}

    private String title;
    private String desc;
    private boolean beenHere;
    private ArrayList<Exit> exits;
    private ArrayList<Item> contents;
    private ArrayList<NPC> nonPlayerCharacters;

    Room(String title) {
        init();
        this.title = title;
    }

    /** Constructs a new <tt>Room</tt> object given a Scanner object positioned at 
      * the beginning of a "room" file entry, read and return a Room object 
      * representing it. 
      *  @throws NoRoomException The reader object is not positioned at the
      *  start of a room entry. A side effect of this is the reader's cursor
      *  is now positioned one line past where it was.
      *  @throws IllegalDungeonFormatException A structural problem with the
      *  dungeon file itself, detected when trying to read this room.
      *
      * @param s scanner that is pointing to the spot in the file that 
      * is ready to read in room information.
      *
      * @param d the playable <tt>Dungeon</tt>.
      *
      * @param initState the truth value letting the program know if it 
      * is starting from the beginning or if it is starting from a 
      * save point.
      *
      */
    Room(Scanner s, Dungeon d, boolean initState) throws NoRoomException,
        Dungeon.IllegalDungeonFormatException {
	if(initState==true){
        	init();
        	title = s.nextLine();
        	desc = "";
        	if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
        	    throw new NoRoomException();
        	}
        
        	String lineOfDesc = s.nextLine();
	
		//adding contents	
		if(lineOfDesc.startsWith("Contents:")){
			String contentLine = lineOfDesc.replace("Contents: ","");
			String [] contentSplit = contentLine.split(",");
			for (int i = 0; i < contentSplit.length; ++i){
				this.add(d.getItem(contentSplit[i]));
				}
				lineOfDesc = s.nextLine();
			}
        
		while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) &&
        	    !lineOfDesc.equals(Dungeon.TOP_LEVEL_DELIM)) {
        	    desc += lineOfDesc + "\n";
        	    lineOfDesc = s.nextLine();
        	}

        	// throw away delimiter
        	if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
        	    throw new Dungeon.IllegalDungeonFormatException("No '" +
        	        Dungeon.SECOND_LEVEL_DELIM + "' after room.");
        	}
	}
	else{
		init();
                title = s.nextLine();
                desc = "";
                if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
                    throw new NoRoomException();
                }
                String lineOfDesc = s.nextLine();
                //throw away contents
                if(lineOfDesc.startsWith("Contents:")){
                                lineOfDesc = s.nextLine();
                }
                while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) &&
                    !lineOfDesc.equals(Dungeon.TOP_LEVEL_DELIM)) {
                    desc += lineOfDesc + "\n";
                    lineOfDesc = s.nextLine();
                }
                // throw away delimiter
                if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
                    throw new Dungeon.IllegalDungeonFormatException("No '" +
                        Dungeon.SECOND_LEVEL_DELIM + "' after room.");
		}
	}
    }

    // Common object initialization tasks.
    /**
     * Initilizes the room to default values.  
     */
    private void init() {
        exits = new ArrayList<Exit>();
        contents = new ArrayList<Item>();
	beenHere = false;
    }
    /**
     * Returns the title of the room.
     *
     * @return title of the room.
     */
    String getTitle() { return title; }
    /**
     * Sets the description of the room to the given description.
     *
     * @param desc room description that is going to be set.
     */
    void setDesc(String desc) { this.desc = desc; }

    /*
     * Store the current (changeable) state of this room to the writer
     * passed.
     */
    /**
     * Returns the FULL description of the room no matter if the adventurer has been
     * to the room or not.
     *
     * @return full description of the room.
     */
   String getDesc()
   {
	   beenHere = false;
	   return this.describe() + "\n";
   }

    /**
     * Stores the current state of the room into a save file to be 
     * regenerated to this exact state when the game is started 
     * from said file. This only stores the room part of the save file and nothing
     * more.
     *
     * @param w the <tt>PrintWriter</tt> that is ready to write to a save file.
     */
    void storeState(PrintWriter w) throws IOException {
        // At this point, nothing to save for this room if the user hasn't
        // visited it.
        if (!contents.isEmpty() || beenHere) {
            w.println(title + ":");
            w.println("beenHere=" + beenHere);
	    if (!contents.isEmpty()){
		    w.print("Contents: ");
		    String contentLine = "";
		    for(Item item: contents){
			    contentLine += item.getPrimaryName() + ",";
		    }
		    String realContentLine = contentLine.substring(0, contentLine.length() - 1);
		    w.println(realContentLine);
	    }

            w.println(Dungeon.SECOND_LEVEL_DELIM);
        }
    }
  
     /**
      * Restores the current state of the room from a save file to exactly how it was 
      * when the game was saved. This only restores the room part of the save file
      * and nothing more.
      *
      * @param s the scanner that has already read through the save file until it 
      * is ready to restore the room in said file.
      *
      * @param d the playable <tt>Dungeon</tt>.
      */
    void restoreState(Scanner s, Dungeon d) throws GameState.IllegalSaveFormatException {

        String line = s.nextLine();
        if (!line.startsWith("beenHere")) {
            throw new GameState.IllegalSaveFormatException("No beenHere.");
        }
        beenHere = Boolean.valueOf(line.substring(line.indexOf("=")+1));
	line = s.nextLine();
	if(line.startsWith("Contents:")){
		String replaceLine = line.replace("Contents: ","");
		String [] lineSplit = replaceLine.split(",");
		for(String itemName: lineSplit){
			this.add(d.getItem(itemName));
		}
		line = s.nextLine();
	}
    }
    /**
     * Returns a string correctly describing the room. If the user has been to 
     * the room previously, the description is returned leaving out the 
     * deescriptive part of the room.
     *
     * @return the correct description of the room.
     */
    public String describe() {
        String description;
        if (beenHere) {
            description = title + "\n";
        } else {
            description = title + "\n" + desc;
        }
	for (Item item: contents){
		description += "There is a(n) " + item.getPrimaryName() + " here.\n";
	}
        for (Exit exit : exits) {
            description += "\n" + exit.describe();
        }
        beenHere = true;
        return description;
    }
    /**
     * Returns a <tt>Room</tt> object that is allowed to be entered from this room
     * through the given direction. If there is no exit corresponding to the given
     * direction, no <tt>Room</tt> object will be returned.
     *
     * @param dir direction the aventurer wishes to move in.
     *
     * @return <tt>Room</tt> object that is able to be accessed through the given
     * direction.
     */
    public Room leaveBy(String dir) {
        for (Exit exit : exits) {
            if (exit.getDir().equals(dir)) {
                return exit.getDest();
            }
        }
        return null;
    }
    /**
     * Adds an <tt>Exit</tt> object to this rooms collection of 
     * {@link Exit}'s.
     *
     * @param exit exit to be added to the room.
     * 
     */
    void addExit(Exit exit) {
        exits.add(exit);
    }
    /**
     * Adds an <tt>Item</tt> object to this rooms collection of {@link Item}'s.
     *
     * @param item item to be added to this room.
     */

    void add(Item item){
	contents.add(item);
    }
    /**
     * Removes an <tt>Item</tt> object from this rooms collection of {@link Item}'s.
     *
     * @param item item to be removed from this room.
     */
    void remove(Item item){
	contents.remove(item);
    }
	
    /**
     * Returns an <tt>Item</tt> object from this room given the Item name.
     *
     * @param name name of the item wanting to be received.
     *
     * @return The <tt>Item</tt> object in this room that goes by the given name.
     */
    Item getItemNamed(String name){
   	for (Item item:contents)
	{
		if(item.getPrimaryName().equals(name))
		{
			return item;
		}
	}
	return null;
    }
    /**
     * Returns the <tt>ArrayList</tt> object containing all {@link Item}'s in this
     * room. 
     *
     * @return <tt>ArrayList</tt> object containing all {@link Item}'s in this room.
     */
    public ArrayList<Item> getContents()
    {
	return contents;
    }
	public String toString(){
	return title;
}
    /**
     * Returns the <tt>ArrayList</tt> object containing all {@link NPC}'s in this 
     * room. 
     *
     * @return <tt>ArrayList</tt> object containing all the {@link NPC}'s in this room.
     */
    public ArrayList<NPC> getNonPlayerCharacters(){}

}
