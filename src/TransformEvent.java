/** A <tt>TransformEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which eliminates that item from the {@link Dungeon} and replaces it with a new item in the dungeon. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a transform event associated with that verb for that item.
    @author HF
*/

public class TransformEvent extends Event{
	private String command;

	/** Constructs a new transform event object with the command that contains the verb that causes a transform event with a certain item.
            @param command the string that the user types which contains the verb with the item that will cause a transform event to occur.
        */
	public TransformEvent(String command){}

	/** Executes the transform event object after a certain command is used with a specific item so that the item disappears from where it was located and is replaced with a new item in the same location.

        */
	public void Execute(){}
}
