/** A <tt>WinEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which ends the game so the user wins.
    @author Hannah
*/

public class WinEvent extends Event{
	private String command;

	/** Constructs a new <tt>WinEvent</tt> object with the command that contains the verb and the item that causes a win event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a win event associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a win event to occur.
        */
	public WinEvent(String command){
		super(command);
	}

	/** Executes the <tt>WinEvent</tt> object after a certain command is used with a specific item so that the game will end and the user will win.

        */
	public void execute(){
		System.exit(1);
	}

}
