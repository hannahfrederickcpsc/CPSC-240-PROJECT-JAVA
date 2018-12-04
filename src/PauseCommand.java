import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
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
		System.out.println( "------------------------------------\n" + 
				"Score: " + g.getAdventurersScore() + "\n" + 
			"Health: " + g.getAdventurersHealth() + "\n" +
			"Hunger: " + g.getHungerPoints() + "\n" + 
			"Moves: " + g.getMoves() + "\n" +
			"Carry Weight: " + g.getWeight() + "\n" + 
			"------------------------------------\n" +
			"Inventory:" + "\n" + g.getInventoryList() + "\n" + 
			"------------------------------------\n" + 
			"Resume" + "\n" +
			"Save" + "\n" +
			"Quit Game" + "\n");
		System.out.println("Type in the option you want: resume, save, or quit");
		String option = s.nextLine();
		while(!option.equals("resume") && !option.equals("save") &&  !option.equals("quit")){
				Command unknownCommand = new UnknownCommand(option);
				System.out.println(unknownCommand.execute());
				option = s.nextLine();

		}
			if(option.equals("resume"))
                        {
				Command resumeCommand = new ResumeCommand(option);
				return resumeCommand.execute() + "\n"; 
					
                        }

                        else if (option.equals("save"))
                        {
				Command pauseSaveCommand = new PauseSaveCommand(option);
				return pauseSaveCommand.execute();
                        }

                        else if (option.equals("quit"))
                        {
				System.out.println("Would you like to save before quitting?");
				String answer = s.next();
				List<String> yesArray = Arrays.asList("Y","y","Yes","yes");
				List<String> noArray = Arrays.asList("N","n","No","no");				
				if(yesArray.contains(answer)){
					Command c = new SaveCommand(answer);
					Command d = new QuitCommand(answer);
					return c.execute() + "\n" + 
						d.execute();
				}
				else if(noArray.contains(answer)){
					Command quitCommand = new QuitCommand(answer);
					return quitCommand.execute();
				}
				else{
					return "Command not understood. Returning to game.\n";
				}
                        }
		return "";
	}
}
