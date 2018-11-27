import java.util.Scanner;

/**
 * A <tt>Friendly</tt> is a NPC that is friendly towards the adventurer.
 *
 * @author Matt 
 */
public class Friendly extends NPC{

	public Friendly(Scanner s){
		super(s);
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
}
