import java.util.Scanner;
import java.util.ArrayList;
/**
 * A <tt>Friendly</tt> is a NPC that is friendly towards the adventurer.
 *
 * @author Matt 
 */
public class Friendly extends NPC{
	private String properName;
	private ArrayList<String> dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;		
	
	public Friendly(Scanner s, Dungeon d){
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
}
