import java.util.ArrayList;
class DropCommand extends Command{
	private String itemName;

	DropCommand(String itemName){
		super(itemName);
		this.itemName = itemName;
	}
	
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
