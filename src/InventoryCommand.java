import java.util.ArrayList;
class InventoryCommand extends Command{
	private String i;

	InventoryCommand(String i){
		super(i);
		this.i = i;
	}

	String execute(){
		GameState g = GameState.instance();
		String retValue = "You are carrying:\n";
		ArrayList<Item> inventory = g.getInventory();
		int totalWeight = 0;
		if(!inventory.isEmpty()){
		for(Item item: inventory){
			retValue += "\033[4m" + item.getPrimaryName() + "\033[0m: " + item.getWeight() + "\n";
			totalWeight += item.getWeight();
		}
		retValue += "Total Weight: " + totalWeight + "\n";
		}
		else{
			retValue = "Inventory empty\n";
		}
		
		return retValue;
	}
}


