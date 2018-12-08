import java.util.Scanner;
/**
 * A <tt>TradeCommand</tt> represents the command given to trade items with a NPC.
 * This command is a {@link EngageMenuCommand}.
 *
 * @author Matt
 */
class TradeCommand extends EngageMenuCommand{
	private String command;
	private Scanner s;
	private String npcName;
	/**
	 * Constructs a new <tt>TradeCommand</tt> object.
	 */
	TradeCommand(String command, Scanner s, String npcName){
		super(command);
		this.s = s;
		this.npcName = npcName;
	}

	/**
	 * Carries out trade interactions with an engaged NPC returning a phrase of
	 * what was swapped in the trade.
	 *
	 * @return phrase responding to what was swapped in the trade with an NPC.
	 */ 
	public String execute(){
		GameState g = GameState.instance();
                Dungeon d = g.getDungeon();
                NPC npc = d.getNPC(npcName);
		String advValue = "";
		String npcValue = "";
		if(npc.getType().equals("Friendly")){
                        if(g.getInventory().isEmpty() || npc.getInventory().isEmpty()){
				return "You cannot trade with " + npc.getProperName() + "!";
			}
			if(!g.getInventory().isEmpty()){
				for(Item i : g.getInventory()){
					advValue += "name: " + i.getPrimaryName() + "    weight: " + i.getWeight() + "    value: " + i.getValue() + "\n";
				}
			System.out.println("\033[4mAdventurer's Inventory:\033[0m\n" + advValue);
			
			}
			if(!npc.getInventory().isEmpty()){
                                for(Item i : npc.getInventory()){
                                        npcValue += "name: " + i.getPrimaryName() + "    weight: " + i.getWeight() + "    value: " + i.getValue() + "\n";
                                }
                        System.out.println("\033[4mNPC's Inventory:\033[0m\n" + npcValue);

			}
			System.out.println("If you would like to swap items, then type 'swap 'your item' 'npc item''");
			System.out.println("If you don't want to swap items, then type 'stop'");
			System.out.print("> ");
			String answer;
			String advItem = "";
			String npcItem = "";
			String[] answerSplit;
			answer = s.nextLine();
			while(!answer.equals("stop")){
				if(!answer.startsWith("swap")){
					System.out.println("incorrect format");
				}
				else if(answer.split(" ").length != 3){
					System.out.println("incorrect format");
				}
				else if(answer.split(" ").length == 3){
					answerSplit = answer.split(" ");
					if(d.getItem(answerSplit[1]) == null || !g.getInventory().contains(d.getItem(answerSplit[1]))){
						System.out.println("trade what?");
					}
					else if(d.getItem(answerSplit[2]) == null || !npc.getInventory().contains(d.getItem(answerSplit[2]))){
                                                System.out.println("trade for what?");
                                        }
					else{
						if(((g.getWeight() - d.getItem(answerSplit[1]).getWeight())+ d.getItem(answerSplit[2]).getWeight()) > 40){
							System.out.println("this will put you over the weight limit");
						}
						else{
							if((d.getItem(answerSplit[2]).getValue() - d.getItem(answerSplit[1]).getValue()) > 10){
								System.out.println(npc.getProperName() + " doesn't want to trade this.");
							}
							else{
								g.getInventory().remove(d.getItem(answerSplit[1]));
								npc.getInventory().add(d.getItem(answerSplit[1]));
								npc.getInventory().remove(d.getItem(answerSplit[2]));
								g.getInventory().add(d.getItem(answerSplit[2]));
								return "you have swapped " + answerSplit[1] + " for " + answerSplit[2];
							}
						}
					}
				}
				System.out.print("> ");
				answer = s.nextLine();
			}
		}
		return "you have stopped trading";
			
	}
	
}
