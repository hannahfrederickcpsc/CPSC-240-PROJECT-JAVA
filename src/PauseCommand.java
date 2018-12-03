/**
 * A <tt>PauseCommand</tt> represents the command to completely pause the game.
 *
 * @author Matt 
 */
class PauseCommand extends Command{
		private Scanner s;
	/**
	 * Constructs a new <tt>PauseCommand</tt> object.
	 */
	PauseCommand(String command, Scanner s){
		super(command);
		this.s = s;
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
			System.out.println("Type in the option you want: resume, save, or quit");
			String option = s.nextLine();
			if(option.equals("resume"))
			{
				
			}
			else if (option.equals("save"))
			{
			}
			else if (option.equals("quit"))
			{
			}
	}
}
