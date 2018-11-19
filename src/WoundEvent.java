/** A <tt>WoundEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which subtracts the points associated with that command from the user's health. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a wound event with a point value associated with that verb for that item.
    @author Hannah
*/

class WoundEvent extends Event{
	private String command;

	/** Constructs a new <tt>WoundEvent</tt> object with the command that contains the verb and the item that causes a wound event with that item.
            @param command the string that the user types which contains the verb with the item that will cause a wound event to occur.
        */
	public WoundEvent(String command){}

	/** Executes the <tt>WoundEvent</tt> object after a certain command is used with a specific item so that the point value associated with that wound event is subtracted from the user's health.
        */
	public void execute(){}
}
