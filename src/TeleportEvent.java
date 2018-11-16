/** A <tt>TeleportEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which puts the user in a random {@link Room} of the {@link Dungeon}. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a teleport event associated with that verb for that item.
    @author zorkaholics
*/

class TeleportEvent extends Event{
	private String command;

	/** Constructs a new teleport event object with the command that contains the verb that causes a teleport event with a certain item.
            @param command the string that the user types which contains the verb with the item that will cause a teleport event to occur.
        */
	public TeleportEvent(String command){}

	/** Executes the teleport event object after a certain command is used with a specific item so that the user is put into a random room; it could be a room that was not visitied before since it can be any room in the dungeon.

        */
	public void execute(){}
}
