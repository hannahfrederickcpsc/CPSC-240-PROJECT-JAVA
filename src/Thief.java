import java.util.Scanner;
/**
 * A <tt>Thief</tt> is a NPC that is unfriendly and can only be attacked. 
 * A thief can also take a random item from the adventurers inventory,
 * and must be killed for the item to be returned.
 *
 * @author zorkaholics
 */
public class Thief extends NPC{
	/**
	 * Allows the Thief to steal from the adventurer.
	 *
	 * @return A phrase mocking the adventurer.
	 */
	public String steal(){}
	/**
	 * Returns the type of the NPC.
	 *
	 * @return Type of the NPC.
	 */
	public String getType(){}
	/**
	 * Sets the type of the NPC.
	 *
	 * @param type Type of the NPC.
	 */
	public void setType(String type){}
}
