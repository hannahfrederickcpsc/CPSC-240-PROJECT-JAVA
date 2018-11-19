/** A <tt>WinEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which ends the game so the user wins. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a win event associated with that verb for that item.
    @author HF
*/

public class WinEvent extends Event{
	private String command;

	/** Constructs a new win event object with the command that contains the verb that causes a win event with a certain item.
            @param command the string that the user types which contains the verb with the item that will cause a win event to occur.
        */
	public WinEvent(String command){}

	/** Executes the win event object after a certain command is used with a specific item so that the game will end and the user will win.

        */
	public void execute(){}
}
