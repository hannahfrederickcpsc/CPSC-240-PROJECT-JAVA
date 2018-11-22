import java.util.Scanner;

/** An <tt>Exit</tt> represents an exit out of one {@link Room} and into the next <tt>Room</tt> by a specific direction (n,s,e,w,u,d) in a certain {@link Dungeon}. If an exit takes the user from one room into another room, then there may not be an exit to take the user from the latter room to the former room.
    @author Hannah
*/
public class Exit {

    /** Specifies what happens when the scanner object is not positioned at the start of an exit entry, this happens when the scanner has reached the end of the exits section of a dungeon file. 
     
    */
    class NoExitException extends Exception {}

    private String dir;
    private Room src, dest;
    
    /** Constucts a new <tt>Exit</tt> object with the direction from the one room into the next room, the <tt>Room</tt> object for the former room, and the <tt>Room</tt> object for the latter room.
        @param dir the direction that goes to the next room through that exit.
	@param src the room that the exit takes the user from with that direction.
	@param dest the room that the exit takes the user into with that direction.

    */
    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }

    /** Constructs a new <tt>Exit</tt> object with a scanner object positioned at the beginning of an exit entry of a dungeon file. Each exit entry in a dungeon file lists the two rooms connected by the exit, first the current room and then the subsequent room, and the direction from the former room to the latter room.
	@param s the scanner object that reads an exit entry of a dungeon file to instantiate an exit object in that dungeon.
	@param d the dungeon object that contains this exit object, so the room objects connected to the exit can be obtained.
        @throws NoExitException if the scanner object is not positioned at the start of an exit entry, so the scanner object is positioned at the end of the exits section of the dungeon file; the program stops instantiating exit objects at this point.
        @throws Dungeon.IllegalDungeonFormatException if the dungeon file is not formatted the way that the exit constructor expected it to be, so the exit entry did not end with the exit delimiter; the program stops instantiating exit objects at this point.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
    }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        
        // I'm an Exit object. Add me as an exit to my source Room.
        src.addExit(this);

        //throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }

    /** Initializes the exit constructor.
     
    */
    // Common object initialization tasks.
    private void init() {
    }

    /** Describes an exit from the current room to another room that is connected by that exit with the direction from the former room to the latter room.
	@return the description of the exit with the direction to the subsequent room through that exit.
    */
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }

    /** Returns the direction (n,s,e,w,u,d) that goes from the current room to another room that is connected by that exit.
        @return the direction that goes to the next room through that exit.
     
    */
    String getDir() { return dir; }

    /** Returns the <tt>Room</tt> object that the exit would take the user from.
        @return the room that the exit takes the user from.
    */
    Room getSrc() { return src; }

    /** Returns the <tt>Room</tt> object that the exit would take the user into.
        @return the room that the exit takes the user into.
    */
    Room getDest() { return dest; }
}
