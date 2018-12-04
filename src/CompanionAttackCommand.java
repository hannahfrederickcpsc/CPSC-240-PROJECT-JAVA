/**
 * A <tt>CompanionAttackCommand</tt> represents the command that the adventurer gives
 * to order their companion to attack another NPC.
 *
 * @author Matt 
 */
class CompanionAttackCommand extends CompanionCommand{
	private Friendly companion;
	private String npcName;

	/**
	 * Constructs a new <tt>CompanionAttackCommand</tt> object.
	 *
	 * @param npcToAttack the NPC that the adventurer wants the NPC to attack.
	 */
	CompanionAttackCommand(String npcName){
		super(npcName);
	}

	/**
	 * Carries out the process of the adventurers Companion attacking an NPC 
	 * returning a phrase declaring the winner.
	 *
	 * @return a phrase declaring the winner of the battle between the companion 
	 * and the NPC that is being attacked.
	 */
	public String execute(){
		return null;
	}
}
