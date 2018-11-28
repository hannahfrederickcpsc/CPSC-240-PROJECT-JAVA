import java.util.Scanner;

/** A <tt>Weapon</tt> represents an certain type of {@link Item} that a {@link Command} can be called on against non-player characters ({@link NPC}) to decrease their health. Each item entry in a dungeon file lists the category of the item, if the category is equal to weapon then this item has all the qualities of other items, but it also has a damage quantity, so that when the weapon is used against a non-player character it decreases their health by that number.
    @author Hannah
*/
public class Weapon extends Item{
	private int damage;

	public Weapon(Scanner s)throws Item.NoItemException, Dungeon.IllegalDungeonFormatException{
		super(s);
		
	}	

	/** Returns the number of points that are subtracted from a non-player character's health when the user types an {@link AttackCommand} with that specific item while engaged with that non-player character.
            @return the number of damage points associated with that weapon item, which is found in each weapon item entry of a dungeon file.
        */
	public int getDamageValue(){
		return this.damage;
	}
}
