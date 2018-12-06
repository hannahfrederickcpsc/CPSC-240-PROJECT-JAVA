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
	boolean follow;

	
	public Friendly(String type, Scanner s, Dungeon d, boolean initState){
		super(type,s, d);
		this.health = 100;
		this.dialogue = new Hashtable<String,String>();
		this.inventory = new ArrayList<Item>();
		this.type = type;
		
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
		GameState g = GameState.instance();
		g.setNonPlayerCharacterCurrRoom(g.getAdventurersCurrentRoom(), this);
		this.follow = true;
		return "Your companion is now following you.";
	}
	public String stay(){
		this.follow = false;
		return "Your companion is now staying in the current room.";
	}
	public void makeCompanion(){
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
		if(isCompanion){
			w.println("following=" + follow);
		}
		w.println("---");



	}
	public void restoreState(Scanner s, Dungeon d){
		String inventoryLine = s.nextLine();
		String [] inventoryLineSplit;
		this.type = "Friendly";
		if(inventoryLine.startsWith("Inventory:")){
			inventoryLineSplit = inventoryLine.replace("Inventory: ","").split(",");
			for(String itemName: inventoryLineSplit){
				this.inventory.add(d.getItem(itemName));
			}
			inventoryLine = s.nextLine();
		}
		this.isCompanion = Boolean.valueOf(inventoryLine.substring(inventoryLine.indexOf("=")+1));
		if(isCompanion){
			GameState.instance().setCompanion(true,this);
			String followLine = s.nextLine();
			this.follow = Boolean.valueOf(followLine.substring(followLine.indexOf("=")+1));
		}
		s.nextLine();
	}
	boolean isCompanion(){
		return isCompanion;
	}
	void releaseCompanion(){
		this.isCompanion = false;
		this.follow = false;
		GameState.instance().setCompanion(false,this);
		GameState.instance().releaseCompanion();
	}


	public ArrayList<Item> getInventory(){
		return this.inventory;
	}
	public Hashtable<String,String> getDialogue(){
		return this.dialogue;
	}
	void removeFromInventory(Item item){
    		this.inventory.remove(item);
	    	getCurrRoom().add(item);	
	}
	boolean getFollow(){
		return follow;
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
	void dropAllItems(){
		while(this.inventory.size() != 0){
			System.out.println(this.inventory.get(0));
			getCurrRoom().add(this.inventory.get(0));
			this.inventory.remove(this.inventory.get(0));
		}
	}
}
