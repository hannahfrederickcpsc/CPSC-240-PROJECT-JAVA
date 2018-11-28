/**
 * A <tt>PauseMenuCommand</tt> is an abstract base class for all pause menu commands.
 *
 * @author Matt 
 */
abstract class PauseMenuCommand extends Command{
	private String command;
	/**
	 * Constructs a new <tt>PauseMenuCommand</tt> object.
	 */
	PauseMenuCommand(String command){
		super(command);
	}
	/**
	 * Carries out the process of the pause menu command given
	 * by the adventurer, returning the correct string responding to the 
	 * command.
	 *
	 * @return the correct string responding to the given pause menu command.
	 */
	public abstract String execute();
}
