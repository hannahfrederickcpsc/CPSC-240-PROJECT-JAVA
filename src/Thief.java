import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.PrintWriter;
/**
 * A <tt>Thief</tt> is a NPC that is unfriendly and can only be attacked. 
 * A thief can also take a random item from the adventurers inventory,
 * and must be killed for the item to be returned.
 *
 * @author Matt 
 */
public class Thief extends NPC{
	private String properName;
	private Hashtable<String,String> dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;


	public Thief(String type,Scanner s, Dungeon d){
		super(type,s, d);
		this.health = 100;
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
	 * Allows the Thief to steal from the adventurer.
	 *
	 * @return A phrase mocking the adventurer.
	 */
	public String steal(){
		return null;
	}

	public String getProperName(){
		return this.properName;
	}
	/**
	 * Returns the type of the NPC.
	 *
	 * @return Type of the NPC.
	 */
	public String getType(){
		return this.type;
	}
	/**
	 * Sets the type of the NPC.
	 *
	 * @param type Type of the NPC.
	 */
	public void setType(String type){
		this.type = type;	
	}
	public String speak(){
		return null;
	}

	public ArrayList<Item> getInventory(){
		return this.inventory;
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
	}
}
