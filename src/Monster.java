import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
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
	
	public Monster(String type,Scanner s, Dungeon d){
		super(type,s, d);
		this.health = 100;
		this.dialogue = new Hashtable<String,String>();
		this.inventory = new ArrayList<Item>();
		this.type = type;
		
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
		String goodbye = s.nextLine().replace("goodbye: ","");
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
}
