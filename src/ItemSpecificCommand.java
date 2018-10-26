import java.util.ArrayList;
class ItemSpecificCommand extends Command{
	private String verb;
	private String noun;
	ItemSpecificCommand(String verb, String noun){
		super(noun);
		this.verb = verb;
		this.noun = noun;
	}
	ItemSpecificCommand(String verb){
		super(verb);
		this.verb = verb;
		this.noun = "";
	}
	String execute(){
		GameState g = GameState.instance();
		Dungeon dungeon = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		Item item;
		ArrayList <Item> inventory = g.getInventory();
		ArrayList<String> verbs = g.getVerbs();
		if(!verbs.contains(this.verb)){
			return "I don't understand the command '" + verb + "'.\n";
		}
		else if(verbs.contains(this.verb) && (noun.equals("") || dungeon.getItem(noun)==null))
		{
		        return verb + " what?\n";
		}
		else if(verbs.contains(this.verb)){
		        if(dungeon.getItem(noun)!=null){
			item = dungeon.getItem(noun);
			if(!inventory.contains(item) && !currRoom.getContents().contains(item)){
				return "There is no " + noun + " here.\n";
			}
			else if(inventory.contains(item) || currRoom.getContents().contains(item)){
				if(item.getMessageForVerb(verb)==null){
					return "You can't " + verb + " the " + noun + "\n";
				}
				else{
					return item.getMessageForVerb(verb) + "\n";
			
				}
			}
	        
                }	
	}
		return "";
	}

}
