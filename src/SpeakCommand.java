/**
 * A <tt>SpeakCommand</tt> represents a command to allow the adventurer to speak with
 * an engaged NPC. 
 *
 * @author Matt 
 */
class SpeakCommand extends EngageMenuCommand{
	private String command;
	
	/**
	 * Constructs a new <tt>SpeakCommand</tt> object.
	 */
	SpeakCommand(String command){
		super(command);
	}

	/**
	 * Carries out the speaking interaction with an engaged NPC, returning a phrase 
	 * from the NPC saying goodbye.
	 *
	 * @return Phrase from the NPC saying goodbye.
	 */
	public String execute(){}
}
