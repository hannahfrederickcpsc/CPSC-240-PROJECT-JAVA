import java.util.ArrayList;
/**
 * A <tt>TakeCommand</tt> represents the command for the adventurer to take an item
 * and add it to their inventory.
 *
 * @author zorkaholics
 */
class TakeCommand extends Command{
	private String itemName;
	/**
	 * Constructs a new <tt>TakeCommand</tt> object with the itemname of the 
	 * item wanting to be taken.
	 *
	 * @param itemName the name of the item wanting to be taken by the adventurer.
	 */
	TakeCommand(String itemName){
		super(itemName);
		this.itemName = itemName;
	}
	
	/**
	 * Carries out the process of taking an item and adding it to the adventurers
	 * inventory, returning the correct phrase responding to the process. If the 
	 * item is non-existent, if the item is not in the adventurers inventory, or if
	 * the item is not in the current room the system will respond correctly to such
	 * issues.
	 *
	 * @return phrase responding to the process that has happened.
	 */
	String execute(){
		GameState g = GameState.instance();
		Room currRoom = g.getAdventurersCurrentRoom();
		Dungeon d = g.getDungeon();
		ArrayList<Item> inventory = g.getInventory();
		ArrayList<Item> itemsToBeRemoved = new ArrayList<Item>();
		String retValue = "";
		int invWeight = 0;
		int expectedWeight = 0;
		for (Item item: inventory){
			invWeight += item.getWeight();
		}
		

		

		if(d.getItem(itemName) == null && !itemName.equals("all")){
			return "Take what?\n";
		}
		else if(!currRoom.getContents().contains(d.getItem(itemName)) && !g.getInventory().contains(d.getItem(itemName)) && !itemName.equals("all")){
			return "Take what?\n";
		}

		else if(d.getItem(itemName) != null && currRoom.getContents().contains(d.getItem(itemName))){
			if(invWeight + d.getItem(itemName).getWeight() > 40){
				return "You will be overencumbered if you take this item.\n";
			}
		}
		else if(itemName.equals("all")){
			for (Item items: currRoom.getContents()){
				expectedWeight += items.getWeight();
			}
			if((expectedWeight + invWeight) > 40){
				return "You will be overencumbered if you take all of the contents of this room.\n";
			}
		}





		
		if(itemName.equals("all")){
			if(currRoom.getContents().isEmpty()){
				return "There are no available items to be taken.\n";
			}

			for(int i = 0; i < currRoom.getContents().size(); ++i){
				g.addToInventory(d.getItem(currRoom.getContents().get(i).getPrimaryName()));
				retValue += currRoom.getContents().get(i).getPrimaryName() + " taken\n";
				itemsToBeRemoved.add(currRoom.getContents().get(i));
			}

			for(Item items: itemsToBeRemoved){
				currRoom.remove(items);
			}
		}

		else if(currRoom.getContents().contains(d.getItem(itemName)) &&
				!inventory.contains(d.getItem(itemName))){
			g.addToInventory(d.getItem(itemName));
			retValue = d.getItem(itemName).getPrimaryName() + " taken.\n";
			currRoom.remove(d.getItem(itemName));
		}
		else{
			retValue = "You already have this item!\n";
		}

		return retValue;
	}




}
