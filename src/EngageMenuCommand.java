/**
 * A <tt>EngageMenuCommand</tt> is the abstract base class for all commands involving 
 * the actual actions that take place during NPC interactions. 
 * 
 * @author Matt 
 */
abstract class EngageMenuCommand extends Command{
	private String command;

	/**
	 * Constructs a new EngageMenuCommand object.
	 *
	 * @param command a String that is the command given by the adventurer to 
	 * further proceed with NPC interactions.
	 */
	EngageMenuCommand(String command){}

	/**
	 * Carries out the actual interactions with the engaged NPC returning a phrase 
	 * responding to the actions that take place during the NPC interactions.
	 *
	 * @return String that is responding to the interactions that take place.
	 */
	public abstract String execute(){}
}
