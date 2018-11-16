import java.util.Scanner;

/** An <tt>Exit</tt> represents an exit out of one {@link Room} and into the next <tt>Room</tt> by a cardinal direction in a certain {@link Dungeon}. Each exit entry in a dungeon file lists the two rooms connected by the exit, first the current room and then the subsequent room, and the cardinal direction, from the former room to the latter room.
    @author zorkaholics
*/
public class Exit {

    /** Specifies what happens when the scanner object is not positioned at the start of an exit entry, this happens when the scanner has reached the end of the exits section of a dungeon file. 
     
    */
    class NoExitException extends Exception {}

    private String dir;
    private Room src, dest;
    
    /** Constucts a new exit object with the cardinal direction from one room into the next room, the room object for the former room, and the room object for the latter room.
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

    /** Constructs a new exit object with a scanner object positioned at the beginning of an exit entry of a dungeon file. 
	@param s the scanner object that reads an exit entry of a dungeon file to instantiate an exit object in that dungeon.
	@param d the dungeon object that contains this exit object, so the room objects connected to the exit can be obtained.
        @throws NoExitException if the scanner object is not positioned at the start of an exit entry, now the scanner's cursor is positioned one line past where it was.
        @throws IllegalDungeonFormatException if the dungeon file is not formatted the way that the exit constructor expected it to be, so the exit entry did not end with the exit delimiter.
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

    /** Describes an exit from the current room to another room that is connected by that exit with the cardinal direction from the former room to the latter room.
	@return the description of the exit with the cardinal direction to the subsequent room through that exit.
    */
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }

    /** Returns the cardinal direction that goes from the current room to another room that is connected by that exit.
        @return the direction that goes to the next room through that exit.
     
    */
    String getDir() { return dir; }

    /** Returns the room object that the exit would take the user from.
        @return the room that the exit takes the user from.
    */
    Room getSrc() { return src; }

    /** Returns the room object that the exit would take the user into.
        @return the room that the exit takes the user into.
    */
    Room getDest() { return dest; }
}
