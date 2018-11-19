/** A <tt>DieEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which ends the game so the user loses.
    @author Hannah
*/

class DieEvent extends Event{
	private String command;

	/** Constructs a new <tt>DieEvent</tt> object with the command that contains the verb and the item that causes a die event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a die event associated with it.
	    @param command the string that the user types which contains the verb with the item that will cause a die event to occur.
	*/
	public DieEvent(String command){}

	/** Executes the <tt>DieEvent</tt> object after a certain command is used with a specific item so that the game will end and the user will lose.
	 
	*/
	public void execute(){}
}
