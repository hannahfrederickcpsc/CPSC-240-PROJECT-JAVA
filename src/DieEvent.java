/** A <tt>DieEvent</tt> represents a type of {@link Event} that occurs when a certain {@link Command} is typed with a specific {@link Item} which ends the game so the user loses. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a die event associated with that verb for that item.
    @author zorkaholics
*/

class DieEvent extends Event{
	private String command;

	/** Constructs a new die event object with the command that contains the verb that causes a die event with a certain item.
	    @param command the string that the user types which contains the verb with the item that will cause a die event to occur.
	*/
	public DieEvent(String command){}

	/** Executes the die event object after a certain command is used with a specific item so that the game will end and the user will lose.
	 
	*/
	public void execute(){}
}
