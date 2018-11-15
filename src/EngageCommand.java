/**
 * An <tt>EngageCommand</tt> represents the command used to engage in complex 
 * interactions with a Non player character. 
 */
class EngageCommand extends Command{
	private NPC npcToEngage;

	/**
	 * Constructs a new <tt>EngageCommand</tt> object with the NPC the adventurer
	 * wants to engage interactions with. 
	 *
	 * @param npcToEngage the NPC the adventurer is wanting to engage interactions
	 * with.
	 */
	EngageCommand(NPC npcToEngage){}

	/**
	 * Carries out the command to engage interactions with an NPC returning a phrase 
	 * corresponding to the interactions that took place with the engaged NPC.
	 *
	 * @return the correct phrase responding to the interactions that took place
	 * with the engaged NPC.
	 */
	public String execute(){}
}

