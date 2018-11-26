import java.util.ArrayList;
/**
 * An <tt>ItemSpecificCommand</tt> represents the command given by the adventurer that
 * directly corresponds to a given item.
 *
 * @author Matt 
 */
class ItemSpecificCommand extends Command{
	private String verb;
	private String noun;

	/**
	 * Constructs a new <tt>ItemSpecificCommand</tt> object setting the given noun 
	 * and verb.
	 *
	 * @param noun the item name that the specific command corresponds to.
	 * @param verb the command to be done to the item given.
	 */
	ItemSpecificCommand(String verb, String noun){
		super(noun);
		this.verb = verb;
		this.noun = noun;
	}
	 /** Constructs a new <tt>ItemSpecificCommand</tt> object with the default
	 * value for the noun being a blank string.
	 *
	 * @param verb the command to be carried out.
	 */
	ItemSpecificCommand(String verb){
		super(verb);
		this.verb = verb;
		this.noun = "";
	}
	/**
	 * Carries out the command given on the item given, returning the correct string
	 * responding to the command being carried out. If the item given is 
	 * non-existent or not in the current room/adventurers inventory, the 
	 * system will return the correct string responding to such issues.
	 *
	 * @return the correct string responding to what happened when the command
	 * was carried out.
	 */
	String execute(){
		GameState g = GameState.instance();
		EventFactory e = EventFactory.instance();
		Dungeon dungeon = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		Item item;
		ArrayList <Item> inventory = g.getInventory();
		ArrayList<String> verbs = g.getVerbs();
		Event event;
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
					if(item.getEvents() != null){
						for(String l: item.getEvents()){
								event = e.parse(l, item.getPrimaryName());
								event.execute();
								}
					}
					return item.getMessageForVerb(verb) + "\n";

				}
			}
	        
                }	
	}
		return "I don't understand the command '" + verb + "'.\n";
	}

}
