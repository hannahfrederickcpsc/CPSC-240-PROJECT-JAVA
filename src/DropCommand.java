import java.util.ArrayList;
/**
 * A <tt>DropCommand</tt> is a command that can be used to drop an Item from the 
 * adventurers inventory to the current room that the adventurer is in.
 * @author zorkaholics 
 */
class DropCommand extends Command{
	private String itemName;
	/**
	 * Constructs a new <tt>DropCommand</tt> object with the specified item name
	 * representing the item the adventurer is wanting to drop.
	 * @param itemName the name of the item the adventurer wants to drop from their
	 * inventory.
	 */
	DropCommand(String itemName){
		super(itemName);
		this.itemName = itemName;
	}
	/** Carries out the process of dropping an item into the current room returning 
	 * a phrase responding to if the player has successfully dropped an item or not. 
	 * If the item is not in their inventory, or the item does not
	 * exist,  the method realizes the item cannot be dropped, thus returning the 
	 * correct string corresponding to the issue.
	 * 
	 * @return correct String responding to whether the item given was dropped 
	 * or not. 
	 */
	String execute(){
		GameState g = GameState.instance();
		ArrayList<Item> inventory = g.getInventory();
		Room currRoom = g.getAdventurersCurrentRoom();
		ArrayList<Item> contents = currRoom.getContents();
		Dungeon d = g.getDungeon();
		Item item = d.getItem(itemName);
		ArrayList<Item> itemsToBeDropped = new ArrayList<Item>();
		String retValue = "";

		if(item == null && !itemName.equals("all")){
			return "Drop what?\n";
		}
		else if(!inventory.contains(item) && !itemName.equals("all")){
			return "You don't have this item!\n";
		}

		if(itemName.equals("all")){
			if(g.getInventory().isEmpty()){
				return "No contents to be dropped.\n";
			}
			for(int i = 0; i < g.getInventory().size(); ++i){
				retValue += g.getInventory().get(i).getPrimaryName() + " dropped.\n";
				itemsToBeDropped.add(g.getInventory().get(i));
			}

			for(Item items: itemsToBeDropped){
				g.removeFromInventory(items);
				currRoom.add(items);
			}
		}
		else if(item != null && g.getInventory().contains(item)){
			g.removeFromInventory(item);
			currRoom.add(item);
			retValue = item.getPrimaryName() + " dropped.\n";
		}
		return retValue;





	}


}
