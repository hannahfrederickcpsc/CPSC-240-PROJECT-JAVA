import java.util.ArrayList;

/**
 * An <tt>InventoryCommand</tt> represents the command used to display the adventurers
 * current inventory of items. 
 *
 * @author zorkaholics
 */
class InventoryCommand extends Command{
	private String i;
	
	/**
	 * Constructs a new <tt>InventoryCommand</tt> object.
	 *
	 * @param i the command that is given to display the adventurers inventory.
	 */
	InventoryCommand(String i){
		super(i);
		this.i = i;
	}
	
	/**
	 * Returns the adventurers inventory of items formatted to be easily read.
	 *
	 * @return the correctly formatted inventory of the adventurer.
	 */
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


