/**
 * A <tt>PauseSaveCommand</tt> represents the command given by the adventurer
 * to save the game from the pause menu.
 *
 * @author Matt 
 */
class PauseSaveCommand extends PauseMenuCommand{
	/**
	 * Constructs a new <tt>PauseSaveCommand</tt> object.
	 */
	PauseSaveCommand(String command){
		super(command);
	}

	/**
	 * Carries out the process to save the game from the pause menu returning the 
	 * phrase responding to the game being saved.
	 *
	 * @return the phrase responding to the game being saved.
	 *
	 */
	public String execute(){
		Command save = new SaveCommand("zork_save.sav");
		save.execute();
		return "The game has been saved to 'zork_save.sav' and resumed!\n";
	}
	
}
