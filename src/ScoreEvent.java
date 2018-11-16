/** A <tt>ScoreEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which adds the points associated with that command to the user's score. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a score event with a point value associated with that verb for that item.
    @author zorkaholics
*/
class ScoreEvent extends Event{
	private String command;

	/** Constructs a new score event object with the command that contains the verb that causes a score event with a certain item.
            @param command the string that the user types which contains the verb with the item that will cause a score event to occur.
        */
	public ScoreEvent(String command){}

	/** Executes the score event object after a certain command is used with a specific item so that the point value associated with that score event is added to the user's score.
        */
	public void execute(){}
}
