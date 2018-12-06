import java.util.Scanner;
import java.util.ArrayList;
public class MakeCompanionCommand extends Command{
	private String command;
	private String npcName;
	MakeCompanionCommand(String command, String npcName){
		super(command);
		this.command = command;
		this.npcName = npcName;
	}
	public String execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		NPC npc = d.getNPC(npcName);
		if(npc.getType().equals("Friendly") && g.companion() == false){
		npc.makeCompanion();
		System.out.println(npc.getProperName() + " is now your companion.\n" +
			"To engage your companion type 'engage companion'");
		g.setCompanion(true, npc);		
		return npc.follow();
		}
		if(g.companion() == true){
			return "Release your current companion if you would like to befriend " + npc.getProperName() + "\n";
		}
		return "You cannot make " + npc.getProperName() + " your companion.";

		
	}
}
