/** A <tt>WoundEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which subtracts the points associated with that {@link Command} from the user's health.
    @author Hannah
*/

class WoundEvent extends Event{
	private String command;

	/** Constructs a new <tt>WoundEvent</tt> object with the command that contains the verb and the item that causes a wound event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a wound event with a point value associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a wound event to occur.
        */
	public WoundEvent(String command){
		super(command);
	}

	/** Executes the <tt>WoundEvent</tt> object after a certain command is used with a specific item so that the point value associated with that wound event is subtracted from the user's health.
        */
	public void execute(){
		GameState g = GameState.instance();
                String [] woundArray = command.split("(");
                String woundString = woundArray[1].substring(0, woundArray[1].length() - 1);
                int wound = Integer.parseInt(woundString);
                g.changeHealth(wound);
	}
}
