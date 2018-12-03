import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
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


	public Thief(Scanner s, Dungeon d){
		super(s, d);
		this.dialogue = new Hashtable<String,String>();
		this.inventory = new ArrayList<Item>();
		this.type = "Friend";
		this.health = 100;
		this.type = "Thief";
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
	 * Allows the Thief to steal from the adventurer.
	 *
	 * @return A phrase mocking the adventurer.
	 */
	public String steal(){
		return null;
	}
	/**
	 * Returns the type of the NPC.
	 *
	 * @return Type of the NPC.
	 */
	public String getType(){
		return null;
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
}
