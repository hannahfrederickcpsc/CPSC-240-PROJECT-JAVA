/**
 * A <tt>CompanionAttackCommand</tt> represents the command that the adventurer gives
 * to order their companion to attack another NPC.
 *
 * @author zorkaholics
 */
class CompanionAttackCommand extends CompanionCommand{
	private Companion companion;
	private NPC npcToAttack;

	/**
	 * Constructs a new <tt>CompanionAttackCommand</tt> object.
	 *
	 * @param npcToAttack the NPC that the adventurer wants the NPC to attack.
	 */
	CompanionAttackCommand(NPC npcToAttack){}

	/**
	 * Carries out the process of the adventurers Companion attacking an NPC 
	 * returning a phrase declaring the winner.
	 *
	 * @return a phrase declaring the winner of the battle between the companion 
	 * and the NPC that is being attacked.
	 */
	public String execute(){}
}
