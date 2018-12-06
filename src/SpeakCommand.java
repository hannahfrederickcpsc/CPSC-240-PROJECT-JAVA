import java.util.Scanner;
/**
 * A <tt>SpeakCommand</tt> represents a command to allow the adventurer to speak with
 * an engaged NPC. 
 *
 * @author Matt 
 */
class SpeakCommand extends EngageMenuCommand{
	private String command;
	private Scanner s;
	private String npcName;
	/**
	 * Constructs a new <tt>SpeakCommand</tt> object.
	 */
	SpeakCommand(String command, Scanner s, String npcName){
		super(command);
		this.s = s;
		this.npcName = npcName;
	}

	/**
	 * Carries out the speaking interaction with an engaged NPC, returning a phrase 
	 * from the NPC saying goodbye.
	 *
	 * @return Phrase from the NPC saying goodbye.
	 */
	public String execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		NPC npc = d.getNPC(npcName);
		if(npc.getType().equals("Friendly")){
			System.out.println("You can say:" + "\n" + "-Hello" + "\n" + "-Talk" + "\n" + "-Bye"); 
			System.out.print("> ");
			String answer = s.nextLine();
			while(!answer.equals("Bye")){
				if(answer.equals("Hello") || answer.equals("Talk")){
					System.out.println(npc.getProperName() + ": " + npc.getDialogue().get(answer));
				}
				else{
					System.out.println("What?");
				}
				System.out.print("> ");

				answer = s.nextLine();
			}
			System.out.println(npc.getProperName() + ": " + npc.getDialogue().get("Bye"));
			return "";
			
		}

		if(npc.getType().equals("Thief")|| npc.getType().equals("Monster")){
                        System.out.println("You can say:" + "\n" + "-Hello" + "\n" + "-Talk" + "\n" + "-Bye");
                        System.out.print("> ");
                        String answer = s.nextLine();
                        while(!answer.equals("Bye")){
                                if(answer.equals("Hello") || answer.equals("Talk")){
                                        System.out.println(npc.getProperName() + ": " + npc.getDialogue().get(answer));
                                }
                                else{
                                        System.out.println("What?");
                                }
                                System.out.print("> ");
                                answer = s.nextLine();
                        }
                        System.out.println(npc.getProperName() + ": " + npc.getDialogue().get("Bye"));
                        return "";
                }
		return "You cannot speak with " + npc.getProperName() + "!";
	}
}
