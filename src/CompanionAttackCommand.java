import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
/**
 * A <tt>CompanionAttackCommand</tt> represents the command that the adventurer gives
 * to order their companion to attack another NPC.
 *
 * @author Matt 
 */
class CompanionAttackCommand extends CompanionCommand{
	private NPC companion;
	private String command;
	private Scanner s;

	/**
	 * Constructs a new <tt>CompanionAttackCommand</tt> object.
	 *
	 * @param npcToAttack the NPC that the adventurer wants the NPC to attack.
	 */
	CompanionAttackCommand(String command, NPC companion, Scanner s){
		super(command,companion,s);
		this.companion = companion;
		this.command = command;
		this.s = s;
	}

	/**
	 * Carries out the process of the adventurers Companion attacking an NPC 
	 * returning a phrase declaring the winner.
	 *
	 * @return a phrase declaring the winner of the battle between the companion 
	 * and the NPC that is being attacked.
	 */
	public String execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		String[]commandSplit;
		double compLevel;
		double npcLevel;
		Random rand = new Random();
		String answer = "";
		List<String> answerList = Arrays.asList("Yes","yes","Y","y");
		if(command.split(" ").length != 2){
			System.out.println("Attack who?\n");
			return new CompanionCommand(answer, this.companion, s).execute();
		}
		else if(command.split(" ").length == 2){
			commandSplit = command.split(" ");
			if(d.getNPC(commandSplit[1]) == null){
				System.out.println("Attack who?\n");
				return new CompanionCommand(answer,this.companion,s).execute();
			}
			else if(d.getNPC(commandSplit[1]).getType().equals("Friendly")){
				System.out.println("You cannot attack a Friendly!\n");
				return new CompanionCommand(answer,this.companion,s).execute();
			}
			else{
				if(g.getAdventurersCurrentRoom().getNonPlayerCharacters().contains(d.getNPC(commandSplit[1]))){
				NPC npc = d.getNPC(commandSplit[1]);
				compLevel = this.companion.getLevel();
				npcLevel = npc.getLevel();
				double percentage = (compLevel/npcLevel)/2;
				percentage *= 100;
				int randNum = rand.nextInt(100);
				System.out.println("Your companion has a %" + percentage + " chance of winning.\n" +
						"Are you sure you want to continue with this?(y/n)\n");
				System.out.print("> ");
				answer = s.nextLine();
				if(answerList.contains(answer)){
				if(randNum <= percentage){
					return g.die(npc);
				}
				else{
					return g.die(companion);
				}
				}
				else{
					System.out.println("You have chosen to return to the menu or the command was not understood.\n");
					return new CompanionCommand(answer,this.companion,s).execute();
				}
				}
				else{
					System.out.println("Attack who?");
					return new CompanionCommand(answer,this.companion,s).execute();
				}
				
			}
		}
		return "";
	}	

}
