import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;
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

	
	public Friendly(Scanner s, Dungeon d){
		super(s, d);
		this.health = 100;
		this.isCompanion = false;
		this.dialogue = new Hashtable<String,String>();
		this.inventory = new ArrayList<Item>();
		this.type = "Friend";
		
		this.properName = s.nextLine();
		String [] items = s.nextLine().replace("items: ","").split(",");
		for (String item: items){
			this.inventory.add(d.getItem(item));
		}
		this.level = Integer.valueOf(s.nextLine().replace("level: ",""));
		String greeting  = s.nextLine().replace("greeting: ","");
		this.dialogue.put("Hello",greeting);
		String dialogue = s.nextLine().replace("dialogue: ","");
		this.dialogue.put("Talk",dialogue);
		String goodbye = s.nextLine().replace("goodbye","");
		this.dialogue.put("Bye",goodbye);
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
}
