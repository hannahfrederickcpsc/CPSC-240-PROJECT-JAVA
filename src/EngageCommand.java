import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
/**
 * An <tt>EngageCommand</tt> represents the command used to engage in complex 
 * interactions with a Non player character. 
 *
 * @author Matt
 */
class EngageCommand extends Command{
	private String npcName;
	private Scanner s;

	/**
	 * Constructs a new <tt>EngageCommand</tt> object with the NPC the adventurer
	 * wants to engage interactions with. 
	 *
	 * @param npcToEngage the NPC the adventurer is wanting to engage interactions
	 * with.
	 */
	EngageCommand(String npcName, Scanner s){
		super(npcName);
		this.s = s;
		this.npcName = npcName.replace("engage ","");

	}

	/**
	 * Carries out the command to engage interactions with an NPC returning a phrase 
	 * corresponding to the interactions that took place with the engaged NPC.
	 *
	 * @return the correct phrase responding to the interactions that took place
	 * with the engaged NPC.
	 */
	public String execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		NPC npc = d.getNPC(this.npcName);
		List<String> answerList = Arrays.asList("speak","trade","befriend","disengage");
		if(npc == null || !currRoom.getNonPlayerCharacters().contains(npc)){
			return "Engage who?";
		}
		
		String answer = "";
		if(npc.getType().equals("Friendly")){
			System.out.println("You have engaged " + npc.getProperName() + "\n" +
					"You can:\n" + 
					"-speak\n" + 
					"-trade\n" +
					"-befriend\n" +
					"-disengage");
			System.out.print("> ");
			answer = s.nextLine();
			while(!answer.equals("disengage")){
				if(answerList.contains(answer)){
				CommandFactory.instance().parse(answer,s,npcName).execute();
				System.out.println("What would you like to do?");
				}
				else{
					System.out.println("Do what?");
				}
				System.out.print("> ");
				answer = s.nextLine();

			}
			return "You have disengaged " + npc.getProperName();
		}
		return null;
	}
}

