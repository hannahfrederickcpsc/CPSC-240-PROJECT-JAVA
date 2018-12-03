import java.util.Scanner;
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
		System.out.println( "Score: " + g.getAdventurersScore() + "\n" + 
			"Health: " + g.getAdventurersHealth() + "\n" +
			"Inventory:" + "\n" + g.getInventoryList() + "\n" +
			"You may..." + "\n" +
			"resume: to resume the game and not save the game" + "\n" +
			"save: to save the game and also resume the game" + "\n" +
			"quit: to quit the game and not save the game" + "\n");
		System.out.println("Type in the option you want: resume, save, or quit");
		String option = s.nextLine();
		while(!option.equals("resume") && !option.equals("save") &&  !option.equals("quit")){
				Command unknownCommand = new UnknownCommand(option);
				return unknownCommand.execute();
		}
			if(option.equals("resume"))
                        {
				Command resumeCommand = new ResumeCommand(option);
				return resumeCommand.execute();
                        }

                        else if (option.equals("save"))
                        {
				Command pauseSaveCommand = new PauseSaveCommand(option);
				return pauseSaveCommand.execute();
                        }

                        else if (option.equals("quit"))
                        {
				Command quitCommand = new QuitCommand(option);
				return quitCommand.execute();
                        }
		return "";
	}
}
