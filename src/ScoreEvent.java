/** A <tt>ScoreEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which adds the points associated with that {@link Command} to the user's score.
    @author Hannah
*/
class ScoreEvent extends Event{
	private String command;

	/** Constructs a new <tt>ScoreEvent</tt> object with the command that contains the verb and the item that causes a score event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a score event with a point value associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a score event to occur.
        */
	public ScoreEvent(String command){
		super(command);
	}

	/** Executes the score event object after a certain command is used with a specific item so that the point value associated with that score event is added to the user's score.
        */
	public void execute(){}
}
