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
		List<String> answerList = Arrays.asList("speak","trade","disengage", "pause");
		if(npcName.equals("companion")){
				return "You cannot engage that of which you do not have.\n";
				}
		if(npc == null || !currRoom.getNonPlayerCharacters().contains(npc)){
			return "Engage who?\n";
		}
		if(npc.equals(g.getCompanion())){
			return "Use 'engage companion' to engage your companion.\n";
		}
		
		String answer = "";
		if(npc.getType().equals("Friendly")){
			g.setEngage(true);
			System.out.println("You have engaged " + npc.getProperName() + "\n" +
					"You can:\n" + 
					"-speak\n" + 
					"-trade\n" +
					"-befriend\n" +
					"-disengage");
			System.out.print("> ");
			answer = s.nextLine();
			while(!answer.equals("disengage") && !answer.equals("befriend")){
				if(answerList.contains(answer)){
				System.out.println(CommandFactory.instance().parse(answer,s,npcName).execute());
				System.out.println("What would you like to do?\n" +
						"-speak\n" +
						"-trade\n" + 
						"-befriend\n" +
						"-disengage\n");
				}
				else{
					System.out.println("Do what?\n");
				}
				System.out.print("> ");
				answer = s.nextLine();

			}
			g.setEngage(false);
			if(answer.equals("befriend")){
				return new MakeCompanionCommand(answer,npcName).execute()+ "\n";
			}
			return "You have disengaged " + npc.getProperName() + "\n";
		}

		if(npc.getType().equals("Thief")|| npc.getType().equals("Monster")){
                        System.out.println("You have engaged " + npc.getProperName() + "\n" +
                                        "You can:\n" +
                                        "-speak\n" +
                                        "-attack\n" +
                                        "-disengage");
                        System.out.print("> ");
                        answer = s.nextLine();
                        while(!answer.equals("disengage")){
                                if(answerList.contains(answer)){
                                System.out.println(CommandFactory.instance().parse(answer,s,npcName).execute());
                                System.out.println("What would you like to do?\n" +
                                                "-speak\n" +
                                                "-attack\n" +
                                                "-disengage\n");
                                }
                                else{
                                        System.out.println("Do what?\n");
                                }
                                System.out.print("> ");
                                answer = s.nextLine();
                        }
                        return "You have disengaged " + npc.getProperName() + "\n";
                }
		return null;
	}
}

