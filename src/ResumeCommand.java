
/**
 * A <tt>ResumeCommand</tt> represents the command the adventurer gives to resume
 * the game from the pause menu.
 *
 * @author Matt 
 */
class ResumeCommand extends PauseMenuCommand{
	/**
	 * Constructs a new <tt>ResumeCommand</tt> object.
	 */
	ResumeCommand(String command){
		super(command);
	}

	/**
	 * Carries out the process to resume the game, returning a phrase
	 * that signals the game has been resumed.
	 *
	 * @return phrase signaling the game has been resumed.
	 */
	public String execute(){
		return "The game has resumed!\n";
	}
}
