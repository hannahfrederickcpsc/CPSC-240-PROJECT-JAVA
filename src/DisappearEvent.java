/** A <tt>DisappearEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which eliminates the item from the {@link Dungeon}. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a disappear event associated with that verb for that item.
    @author Hannah
*/

class DisappearEvent extends Event{
	private String command;
	
	/** Constructs a new <tt>DisappearEvent</tt> object with the command that contains the verb and the item that causes a disappear event with that item.
            @param command the string that the user types which contains the verb with the item that will cause a disappear event to occur.
        */
	public DisappearEvent(String command){}

	/** Executes the <tt>DisappearEvent</tt> object after a certain command is used with a specific item so that the item is removed from where it is stored, whether that is the room or the inventory, and it doesn't appear in the dungeon again.
        */
	public void execute(){}
}
