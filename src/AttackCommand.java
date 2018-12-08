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
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		NPC npc = d.getNPC(npcName);
		String answer;
		Item weapon;
		String winner = "";
		weapon = selectWeapon(this.s);
		if(npc.getType().equals("Thief") || npc.getType().equals("Monster")){
			while(g.getAdventurersHealth() > 0 && npc.getHealth() > 0){
				if(weapon == null){
					System.out.println("That weapon does not exist.");
					weapon = selectWeapon(this.s);
				}
				else if(!g.getInventory().contains(weapon) && !weapon.getPrimaryName().equals("Fists")){
					System.out.println("You do not own this weapon.");
					weapon = selectWeapon(this.s);
				}
				else{
					attack(npc, weapon);
				}
			}
		}
		if(g.getAdventurersHealth() <= 0){
			g.die();
			return npc.getProperName() + " is the winner!\n";
		}
		else{
			System.out.println(g.die(npc));
			return "You win!\n";
		}
	}

	/**
	 * Selects the weapon that is to be used during the battle. If the weapon is not
	 * in the adventurers inventory, the program will prompt the user again to 
	 * select a weapon.
	 *
	 * @param item the weapon to be used in battle.
	 */
	private Item selectWeapon(Scanner s){
		GameState g = GameState.instance();
                Dungeon d = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		Item w;
		System.out.println("Weapons:");
		int count = 0;
		for(Item item: g.getInventory()){
			if(item.getType().equals("weapon")){
				System.out.println(item);
				count++;
			}
		}
		if(count == 0){
			System.out.println("You do not have any weapons.. I guess you will have to punch your way out.");
			return new Weapon("Fists",10);
		}
		System.out.println("What item would you like to use?");
		String answer = s.nextLine();
		return d.getItem(answer);

	}

	/**
	 * Compares the level between the adventurer and the engaged NPC to decide who
	 * attacks first in the battle.
	 *
	 * @param npc the engaged NPC to compare levels with.
	 *
	 * @return boolean marking if the adventurer or NPC has a higher level.
	 */
	private boolean compareLevels(NPC npc){
		GameState g = GameState.instance();
		if(g.getAdventurersScore() >= npc.getLevel()){
			return true;
		}
		return false;
	}

	/**
	 * Carries out the process to allow the adventurer to attack the NPC.
	 *
	 * @param usableWeaponName name of the weapon selected for the battle.
	 *
	 * @return String responding to the attack.
	 */
	private void attack(NPC npc,Item weapon){
		int damage = weapon.getDamage();
		GameState g = GameState.instance();
		if(compareLevels(npc) == true){
			npc.changeHealth(damage);
			g.changeHealth(npc.getLevel()/2);
		}
		else{
			g.changeHealth(npc.getLevel()/2);
			npc.changeHealth(damage);
		}

	}
}
