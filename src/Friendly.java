import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.PrintWriter;
/**
 * A <tt>Friendly</tt> is a NPC that is friendly towards the adventurer.
 *
 * @author Matt 
 */
public class Friendly extends NPC{
	private String properName;
	private Hashtable<String,String>  dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;
	boolean isCompanion;

	
	public Friendly(String type, Scanner s, Dungeon d){
		super(type,s, d);
		this.health = 100;
		this.isCompanion = false;
		this.dialogue = new Hashtable<String,String>();
		this.inventory = new ArrayList<Item>();
		this.type = type;
		
		this.properName = s.nextLine();
		String itemLine = s.nextLine();
		if(itemLine.startsWith("items:")){
		String [] items = itemLine.replace("items:","").split(",");
		for (String item: items){
			this.inventory.add(d.getItem(item));
		}
		itemLine = s.nextLine();
		}
		this.level = Integer.valueOf(itemLine.replace("level:",""));
		String greeting  = s.nextLine().replace("greeting:","");
		this.dialogue.put("Hello",greeting);
		String dialogue = s.nextLine().replace("dialogue:","");
		this.dialogue.put("Talk",dialogue);
		String goodbye = s.nextLine().replace("goodbye:","");
		this.dialogue.put("Bye",goodbye);
		s.nextLine();
	}

	/**
	 * Returns the type of the NPC.
	 *
	 * @return Type of the NPC.
	 */ 
	public String getType(){
		return this.type;
	}
	public int getLevel(){
		return this.level;
	}
	public String getProperName(){
		return this.properName;
	}

	
	/**
	 * Allows the adventurer to trade items with the NPC.
	 *
	 */
	public void trade(){}
	/**
	 * Sets the type of the NPC.
	 *
	 * @param type Type the NPC is.
	 */
	public void setType(String type){}
	public String speak(){
		return null;
	}
	public String follow(){
		return "Your companion is now following you.";
	}
	public String stay(){
		return "Your companion is now staying in the current room.";
	}
	public void MakeCompanion(){
		this.isCompanion = true;
	}
	public void storeState(PrintWriter w){
		w.println(this.properName);
		String inventoryLine = "";
		if(!this.inventory.isEmpty()){
		w.print("Inventory: ");
		for(Item item: this.inventory){
			inventoryLine += item.getPrimaryName() + ",";
		}
 		inventoryLine = inventoryLine.substring(0,inventoryLine.length() - 1);
		w.println(inventoryLine);
		}
		Dungeon d = GameState.instance().getDungeon();
		w.println("isCompanion=" + isCompanion);
		w.println("---");



	}
	public void restoreState(Scanner s, Dungeon d){
		String inventoryLine = s.nextLine();
		String [] inventoryLineSplit;
		if(inventoryLine.startsWith("Inventory:")){
			inventoryLineSplit = inventoryLine.replace("Inventory: ","").split(",");
			for(String itemName: inventoryLineSplit){
				this.inventory.add(d.getItem(itemName));
			}
			inventoryLine = s.nextLine();
		}
		this.isCompanion = Boolean.valueOf(s.nextLine().replace("isCompanion=",""));
	}


	public ArrayList<Item> getInventory(){
		return this.inventory;
	}
	public Hashtable<String,String> getDialogue(){
		return this.dialogue;
	}
	void removeFromInventory(Item item){
    		int index = this.inventory.indexOf(item);
		this.inventory.remove(index);    
	}
}
