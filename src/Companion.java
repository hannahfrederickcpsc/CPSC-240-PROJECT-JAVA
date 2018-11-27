import java.util.Scanner;

/**
 * A <tt>Companion</tt> is a Friendly NPC that has been made the 
 * adventurers 'Companion.' A companion is allowed to be ordered around
 * to do the adventurers bidding.
 *
 * @author Matt 
 */
public class Companion extends Friendly{

	public Companion(Scanner s){
		super(s);
	}
	/**
	 * Allows the companion to follow the adventurer throughout the 
	 * adventure.
	 *
	 * @return A phrase the NPC says stating they understand orders.
	 */
	public String follow(){
		return null;
	}
	/**
	 * Allows the companion to stay in the current room and not follow
	 * anymore.
	 *
	 * @return A phrase the NPC says stating they understand orders.
	 */
	public String stay(){
		return null;
	}
	/**
	 * Sets the type of the NPC.
	 *
	 * @param type Type the NPC is.
	 */
	public void setType(String type){}
}
