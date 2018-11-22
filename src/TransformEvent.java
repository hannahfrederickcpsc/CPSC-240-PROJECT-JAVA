/** A <tt>TransformEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which eliminates that item from the {@link Dungeon} and replaces it with a new item in the dungeon.
    @author Hannah
*/

public class TransformEvent extends Event{
	private String command;

	/** Constructs a new <tt>TransformEvent</tt> object with the command that contains the verb and the item that causes a transform event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a transform event associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a transform event to occur.
        */
	public TransformEvent(String command){}

	/** Executes the <tt>TransformEvent</tt> object after a certain command is used with a specific item so that the item disappears from the dungeon and is replaced with a new item in the same location; the original item does not appear anywhere in the dungeon again.

        */
	public void Execute(){}
}
