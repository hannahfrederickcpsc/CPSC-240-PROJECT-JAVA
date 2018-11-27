/**
 *A <tt>GetScoreCommand</tt> represents the command given by the adventurer to retrieve
 * their score.
 *
 * @author Matt 
 */
class GetScoreCommand extends Command{
	/**
	 * Constructs a new <tt>GetScoreCommand</tt> object.
	 */
	GetScoreCommand(String command){
		super(command);
	}

	/**
	 * Carries out the process to get the adventurers score expressed as a phrase
	 * corresponding to the adventurers actual integer score value.
	 *
	 * @return the phrase corresponding to the adventurers integer health value.
	 */
	public String execute(){
		return null;
	}
}
	
