/**
 * An <tt>UnknownCommand</tt> represents a command given by the adventurer that is 
 * unknown to the system.
 *
 * @author zorkaholics
 */
class UnknownCommand extends Command{
	private String BogusCommand;
	
	/**
	 * Constructs a new <tt>UnknownCommand</tt> object.
	 *
	 * @param BogusCommand a command that is unknown by the system.
	 */
	UnknownCommand(String BogusCommand){
		super(BogusCommand);
		this.BogusCommand = BogusCommand;
	}
	
	/**
	 * Returns a statement declaring that the system does not understand the 
	 * given command.
	 *
	 * @return phrase stating the system does not understand the given command.
	 */
	String execute(){
		return "I don't understand the command '" + BogusCommand + "'.\n";
	}
}
