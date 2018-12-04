import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Hashtable;
/**
 * An <tt>NPC</tt> represents a Non player character that is allowed to be
 * interacted with while playing the game.
 *
 * @author Matt 
 */
public abstract class NPC{
	/**
*A NoNPCException is called when an NPC is the return value of a method, but there is not an instantiated NonPlayerCharacter so the method throws a NoNPCException. This method extends Exception.
*
*/
	
	private String properName;
	private ArrayList<String> dialogue;
	private int level;
	private int health;
	private String type;
	private ArrayList<Item>inventory;
	
	/**
	 * Constructs a new <tt>NPC</tt> object by reading in information
	 * from the .zork file. The given scanner is pointing at the place
	 * in the file that includes NPC-specific information.
	 *
	 * @param s Scanner that is pointing to the place in the .zork file
	 * where NPC-specific information is stored.
	 *
	 * @throws NoNPCException
	 */
	public NPC(String type,Scanner s, Dungeon d){}
	/**
	 * Allows the user to interact with an NPC by speaking to them.
	 *
	 */
	public abstract String speak();
	/**
	 * Returns the health of the NPC.
	 *
	 * @return Health of the NPC.
	 */
	public int getHealth(){
		return health;
	}
	/**
	 * Returns the level of the NPC.
	 *
	 * @return The level of the NPC.
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * Returns the inventory of the NPC.
	 *
	 * @return ArrayList containing the NPC's inventory of Items.
	 */
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	/**
	 * Returns the type of the NPC.
	 *
	 * @return Type of the NPC.
	 */
	public abstract String getType();
	/**
	 * Sets the type for the NPC.
	 *
	 * @param type Type the NPC is to be set as.
	 */
	public abstract void setType(String type);
	public String getProperName(){
		return this.properName;
	}
	public abstract Hashtable<String,String> getDialogue();
	public abstract void storeState(PrintWriter w);
	public abstract void restoreState(Scanner s, Dungeon d);
}


