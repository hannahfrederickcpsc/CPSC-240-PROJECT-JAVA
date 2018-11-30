/** A <tt>WoundEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which subtracts the points associated with that {@link Command} from the user's health.
    @author Hannah
*/

class WoundEvent extends Event{
	private String command;

	/** Constructs a new <tt>WoundEvent</tt> object with the command that contains the verb and the item that causes a wound event with that item. The string command always matches a valid item because the <tt>ItemSpecificCommand</tt> class checks that the item is not null before it parses the command as an event.
            @param command the string that the user types which contains the verb with the item that will cause a wound event to occur.
        */
	public WoundEvent(String command){
		super(command);
		this.command = command;
	}

	/** Executes the <tt>WoundEvent</tt> object after a certain command is used with a specific item so that the point value associated with that wound event is subtracted from the user's health.
        */
	public void execute(){
			
                GameState g = GameState.instance();
		int startIndex = command.indexOf("(") + 1;
                int endIndex = command.indexOf(")");
                String woundString  = command.substring(startIndex, endIndex);
               
                int wound = Integer.parseInt(woundString);
              
                g.changeHealth(wound);
	}
}
