/**
 * A <tt>LookCommand</tt> represents a command given by the adventurer to 
 * see the full description of the current room.
 *
 * @author zorkaholics
 */
class LookCommand extends Command{
	private String look;

	/**
	 * Constructs a new <tt>LookCommand</tt> object.
	 *
	 * @param look the command the adventurer gives to create a <tt>LookCommand</tt>
	 * object.
	 */
	LookCommand(String look){
		super(look);
		this.look = look;
		}
	/**
	 * Carries out the process and returns the full description of the current 
	 * room.
	 *
	 * @return the full description for the current room.
	 */
	String execute(){
		GameState g = GameState.instance();
		return g.getAdventurersCurrentRoom().getDesc();
	}
}

