/**
 * A <tt>QuitCommand</tt> is a {@link PauseMenuCommand} that allows the adventurer
 * to quit the game out of the pause menu. 
 *
 * @author Matt 
 *
 */
class QuitCommand extends PauseMenuCommand{
	/**
	 * Constructs a new <tt>QuitCommand</tt> object.
	 */ 
	QuitCommand(String command){
		super(command);
	}

	/**
	 * Carries out the command to quit the game returning a string responding to the 
	 * user quitting out of the pause menu. If the user has not saved before 
	 * quitting, the adventurer will first be asked if they want to save before 
	 * quitting.
	 *
	 * @return a String responding to the user quitting out of the pause menu.
	 */
	public String execute(){
		return null;
	}
}
