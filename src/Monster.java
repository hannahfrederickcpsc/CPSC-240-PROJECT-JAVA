import java.util.Scanner;
import java.util.ArrayList;
/**
 * A <tt>Monster</tt> is a NPC that is unfriendly, and can only be 
 * attacked.
 *
 * @author Matt 
 */
public class Monster extends NPC{
	private String properName;
	private ArrayList<String> dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;	
	
	public Monster(Scanner s, Dungeon d){
		super(s, d);
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
	public void setType(String type){}

	public String speak(){
		return null;
	}
}
