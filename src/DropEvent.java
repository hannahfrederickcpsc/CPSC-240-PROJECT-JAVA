/** A <tt>DropEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which drops that item into the current {@link Room}. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a drop event associated with that verb for that item.
    @author HF
*/
class DropEvent extends Event{
	private String command;

	/** Constructs a new drop event object with the command that contains the verb that causes a drop event with a certain item.
            @param command the string that the user types which contains the verb with the item that will cause a drop event to occur.
        */
	public DropEvent(String command){}

	/** Executes the drop event object after a certain command is used with a specific item so that the item is dropped into the room that the user was in when the command was called.
        */
	public void execute(){}
}
