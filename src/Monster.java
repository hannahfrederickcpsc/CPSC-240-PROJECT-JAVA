import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.PrintWriter;
/**
 * A <tt>Monster</tt> is a NPC that is unfriendly, and can only be 
 * attacked.
 *
 * @author Matt 
 */
public class Monster extends NPC{
	private String properName;
	private Hashtable<String,String> dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;	
	boolean ifStole;	
	
	public Monster(String type,Scanner s, Dungeon d, boolean initState){
		super(type,s, d);
		this.dialogue = new Hashtable<String,String>();
		this.inventory = new ArrayList<Item>();
		this.type = type;
		this.ifStole = false;
		
		this.properName = s.nextLine();
		String itemLine = "";
		if(initState == true){
		itemLine = s.nextLine();
		if(itemLine.startsWith("items:")){
		String [] items = itemLine.replace("items:","").split(",");
		for (String item: items){
			this.inventory.add(d.getItem(item));
		}
		itemLine = s.nextLine();
		}
		}
		else if(initState == false){
			itemLine = s.nextLine();
			if(itemLine.startsWith("items:")){
				itemLine = s.nextLine();
			}
		}
		
		this.level = Integer.valueOf(itemLine.replace("level:",""));
		this.health = this.level * 2;
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
	public String getProperName(){
		return this.properName;
	}
	/**
	 * Sets the type of the NPC.
	 *
	 * @param type Type of the NPC.
	 */
	public void setType(String type){}

	public String speak(){
		return null;
	}
	public ArrayList<Item> getInventory(){
		return this.inventory;
	}
	public Hashtable<String,String> getDialogue(){
                return this.dialogue;
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
	void removeFromInventory(Item item){
		int index = this.inventory.indexOf(item);
                this.inventory.remove(index);
		getCurrRoom().add(item);
         }  
	void makeCompanion(){}
	String follow(){
		return null;
	}
	String stay(){
		return null;
	}

	boolean getFollow(){
		return false;
	}
	Room getCurrRoom(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		for(Room currRoom: d.getRooms().values()){
			if(currRoom.getNonPlayerCharacters().contains(this)){
				return currRoom;
			}
		}
		return null;
	}
	boolean isCompanion(){
		return false;
	}
	void releaseCompanion(){}
	int getLevel(){
		return this.level;
	}
	void dropAllItems(){
		while(this.inventory.size() != 0){
			getCurrRoom().add(this.inventory.get(0));
			this.inventory.remove(this.inventory.get(0));
		}
	
	}
	public boolean getIfStole() {return this.ifStole;}
	int getHealth(){
		return this.health;
	}
	void changeHealth(int numChange){
		this.health -= numChange;
	}


}
