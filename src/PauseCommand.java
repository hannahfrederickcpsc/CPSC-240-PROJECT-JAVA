/**
 * A <tt>PauseCommand</tt> represents the command to completely pause the game.
 *
 * @author Matt 
 */
class PauseCommand extends Command{
	/**
	 * Constructs a new <tt>PauseCommand</tt> object.
	 */
	PauseCommand(String command){
		super(command);
	}

	/**
	 * Carries out the process to pause the game, returning the adventurers
	 * inventory, score, health, and displays menu options.
	 *
	 * @return correctly formatted menu along with its options.
	 */
	public String execute(){
		GameState g = GameState.instance();
		return "Score: " + g.getAdventurersScore() + "\n" + 
			"Health: " + g.getAdventurersHealth() + "\n" +
			"Inventory:" + "\n" + g.getInventoryList() + "\n" +
			"You may..." + "\n" +
			"resume: to resume the game and not save the game" + "\n" +
			"save: to save the game and also resume the game" + "\n" +
			"quit: to quit the game and not save the game" + "\n";
	}
}
