/**
 * A <tt>CompanionCommand</tt> is an abstract base class for all further commands 
 * involving the adventurers companion.
 *
 * @author Matt 
 */
abstract class CompanionCommand extends Command{
	private String command;
	/**
	 * Constructs a new <tt>CompanionCommand</tt> object.
	 */
	CompanionCommand(){}
	/**
	 * Carries out the process for further interaction involving the adventurers
	 * companion returning the correct phrase responding to such interactions.
	 *
	 * @return phrase responding the correct phrase responding to the companion 
	 * interactions.
	 */
	public abstract String execute(){}
}
