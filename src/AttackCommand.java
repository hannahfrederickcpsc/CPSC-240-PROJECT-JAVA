import java.util.Scanner;
/**
 * An <tt>AttackCommand</tt> represents the command given to attack
 * a NPC. 
 *
 * @author Matt 
 */
class AttackCommand extends EngageMenuCommand{
	private String command;
	private Scanner s;
	private String npcName;
	private Weapon weapon;
	private String weaponName;

	/**
	 * Constructs a new <tt>AttackCommand</tt> object.
	 *
	 * @param command the string that was given to attack an NPC
	 */
	AttackCommand(String command, Scanner s, String npcName){
		super(command);
		this.s = s;
		this.npcName = npcName;
	}

	/**
	 * Carries out the process of attacking a NPC, returning the string declaring 
	 * who won the battle in the end.
	 *
	 * @return the string declaring who won the battle.
	 */
	public String execute(){
		/*GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		NPC npc = d.getNPC(npcName);
		String answer;
		if(npc.getType().equals("Thief") || npc.getType().equals("Monster")){
			if(!g.getInventory().isEmpty() || !currRoom.getContents().isEmpty()){
				System.out.println("What would you like to attack with?");
                        	System.out.print("> ");
				answer = s.nextLine();
				if(g.getItemFromInventoryNamed(answer) != null || g.getItemInVicinityNamed(answer) != null){
					

					if(compareLevels(npc) == true){
						attack(weaponName);
						while(g.getAdventurersHealth() > 0 || npc.getHealth() > 0){
							npcAttack(npcWeaponName);
							attack(weaponName);
						}
						if(g.getAdventurersHealth() <= 0){
							System.out.println(npc.getProperName() + " has defeated you!");
							for(Item i: g.getInventory()){
								currRoom.add(i);
							}
							System.exit(0);
						}
						else if(npc.getHealth() <= 0){
							System.out.println("You have defeated " + npc.getProperName());
							d.removeNPC(npc);
							currRoom.removeFromRoom(npc);
						}
					}

					else{
						npcAttack(npcWeaponName);
                                                while(g.getAdventurersHealth() > 0 || npc.getHealth() > 0){
                                                        attack(weaponName);
							npcAttack(npcWeaponName);
                                                }
                                                if(g.getAdventurersHealth() <= 0){
                                                        System.out.println(npc.getProperName() + " has defeated you!");
                                                        for(Item i: g.getInventory()){
                                                                currRoom.add(i);
                                                        }
                                                        System.exit(0);
                                                }
                                                else if(npc.getHealth() <= 0){
                                                        System.out.println("You have defeated " + npc.getProperName());
                                                        d.removeNPC(npc);
                                                        currRoom.removeFromRoom(npc);
                                                }
					}		

				}
				else{
					System.out.println("You do not have that item with you");
				}
			}
		}*/return null;
	}

	/**
	 * Selects the weapon that is to be used during the battle. If the weapon is not
	 * in the adventurers inventory, the program will prompt the user again to 
	 * select a weapon.
	 *
	 * @param item the weapon to be used in battle.
	 */
	/*private void selectWeapon(Item item){
		GameState g = GameState.instance();
                Dungeon d = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		if(!g.getInventory().contains(item) && !currRoom.getContents().contains(item)){
			System.out.println("You do not have that item with you. Please type an item that you have.");
		}
		weapon = item;
		weaponName = item.getPrimaryName();
	}*/

	/**
	 * Compares the level between the adventurer and the engaged NPC to decide who
	 * attacks first in the battle.
	 *
	 * @param npc the engaged NPC to compare levels with.
	 *
	 * @return boolean marking if the adventurer or NPC has a higher level.
	 */
	/*private boolean compareLevels(NPC npc){
		GameState g = GameState.instance();
		if(g.getAdventurersScore() >= npc.getLevel()){
			return true;
		}
		return false;
	}*/

	/**
	 * Carries out the process for the NPC to attack the adventurer.
	 *
	 * @param itemName the item name of what the NPC is attacking with.
	 *
	 * @return the string responding to the attack.
	 */
	/*private String NPCAttack(String itemName){
		return null;
	}*/

	/**
	 * Carries out the process to allow the adventurer to attack the NPC.
	 *
	 * @param usableWeaponName name of the weapon selected for the battle.
	 *
	 * @return String responding to the attack.
	 */
	/*private String attack(String weaponName){
		return null;
	}*/
}
