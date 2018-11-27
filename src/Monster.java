import java.util.Scanner;

/**
 * A <tt>Monster</tt> is a NPC that is unfriendly, and can only be 
 * attacked.
 *
 * @author Matt 
 */
public class Monster extends NPC{

	public Monster(Scanner s){
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
	 * Sets the type of the NPC.
	 *
	 * @param type Type of the NPC.
	 */
	public void setType(String type){}
}
