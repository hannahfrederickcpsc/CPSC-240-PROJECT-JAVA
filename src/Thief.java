import java.util.Scanner;
/**
 * A <tt>Thief</tt> is a NPC that is unfriendly and can only be attacked. 
 * A thief can also take a random item from the adventurers inventory,
 * and must be killed for the item to be returned.
 *
 * @author Matt 
 */
public class Thief extends NPC{

	public Thief(Scanner s){
		super(s);
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
	public void setType(String type){}
}
