import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
/**
 * A <tt>CompanionCommand</tt> is an abstract base class for all further commands 
 * involving the adventurers companion.
 *
 * @author Matt 
 */
	class CompanionCommand extends Command{
	private String command;
	private NPC companion;
	private Scanner s;
	/**
	 * Constructs a new <tt>CompanionCommand</tt> object.
	 */
	CompanionCommand(String command, NPC companion, Scanner commandLine){
		super(command);
		this.command = command;
		this.companion = companion;
		this.s = commandLine;

	}
	/**
	 * Carries out the process for further interaction involving the adventurers
	 * companion returning the correct phrase responding to such interactions.
	 *
	 * @return phrase responding the correct phrase responding to the companion 
	 * interactions.
	 */
	public String execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		if(g.hasCompanion() == false){
			return "You don't have a companion loser!\n";
		}
		if(!companion.getCurrRoom().equals(g.getAdventurersCurrentRoom())){
			return "Your companion is not here.\n";
		}
		List<String> answerList = Arrays.asList("speak","trade","release","disengage","stay","follow","pause");
		System.out.println("You can command your companion to:\n" + 
				"-speak\n" +
				"-trade\n" +
				"-release\n" +
				"-disengage\n" + 
				"-stay\n" +
				"-follow\n" +
				"-attack 'NPC Name'\n");
		System.out.print("> ");
		String answer = s.nextLine();
		while(!answer.equals("disengage") && !answer.equals("release") && !answer.equals("stay") && !answer.equals("follow") && !answer.startsWith("attack")){
			g.setCompanionEngaged(true);
			if(!answerList.contains(answer)){
				System.out.println("What?\n");
			}
			else if(answerList.contains(answer)){
				System.out.println(CommandFactory.instance().parse(answer,s, this.companion.getProperName()).execute());
						
		System.out.println("-speak\n"+
				"-trade\n" +
				"-release\n" +
				"-disengage\n" +
				"-stay\n" +
				"-follow\n" +
				"-attack 'NPC name'\n");
			}
		System.out.print("> ");
		answer = s.nextLine();
		}
		g.setCompanionEngaged(false);
		if(answer.startsWith("attack")){
			return new CompanionAttackCommand(answer, g.getCompanion(),s).execute();
			}
		if(answer.equals("release")){
			this.companion.releaseCompanion();
			return "Your companion has been released.\n";

		}
		if(answer.equals("stay") && this.companion.getFollow() == false){
			System.out.println("Your companion is already staying!\n");
			return new CompanionCommand(answer,this.companion,s).execute();
		}
		else if (answer.equals("stay")){			
			return this.companion.stay() + "\n";
		}
		if(answer.equals("follow") && this.companion.getFollow() == true){
			System.out.println("Your companion is already following you!\n");
			return new CompanionCommand(answer,this.companion,s).execute();
		}
		else if (answer.equals("follow")){
			return this.companion.follow() + "\n";
		}


	return "You have disengaged your companion.\n";
	}
}

