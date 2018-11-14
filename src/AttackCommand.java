/**
 * An <tt>AttackCommand</tt> represents the command given to attack
 * a NPC. 
 *
 * @author zorkaholics
 */
class AttackCommand extends EngageMenuCommand{
	private String usableWeaponName;
	private String command;

	/**
	 * Constructs a new <tt>AttackCommand</tt> object.
	 *
	 * @param command the string that was given to attack an NPC
	 */
	AttackCommand(String command){}

	/**
	 * Carries out the process of attacking a NPC, returning the string declaring 
	 * who won the battle in the end.
	 *
	 * @return the string declaring who won the battle.
	 */
	public String execute(){}

	/**
	 * Selects the weapon that is to be used during the battle. If the weapon is not
	 * in the adventurers inventory, the program will prompt the user again to 
	 * select a weapon.
	 *
	 * @param item the weapon to be used in battle.
	 */
	private static void selectWeapon(Item item){}

	/**
	 * Compares the level between the adventurer and the engaged NPC to decide who
	 * attacks first in the battle.
	 *
	 * @param npc the engaged NPC to compare levels with.
	 *
	 * @return boolean marking if the adventurer or NPC has a higher level.
	 */
	private static boolean compareLevels(NPC npc){}

	/**
	 * Carries out the process for the NPC to attack the adventurer.
	 *
	 * @param itemName the item name of what the NPC is attacking with.
	 *
	 * @return the string responding to the attack.
	 */
	private static String NPCAttack(String itemName){}

	/**
	 * Carries out the process to allow the adventurer to attack the NPC.
	 *
	 * @param usableWeaponName name of the weapon selected for the battle.
	 *
	 * @return String responding to the attack.
	 */
	private static String attack(String usableWeaponName){}

