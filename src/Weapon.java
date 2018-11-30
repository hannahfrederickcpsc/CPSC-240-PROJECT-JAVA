import java.util.Scanner;

/** A <tt>Weapon</tt> represents an certain type of {@link Item} that a {@link Command} can be called on against non-player characters ({@link NPC}) to decrease their health.
    @author Hannah
*/
public class Weapon extends Item{
	private int damage;

	/** Constructs a new <tt>Weapon</tt> object with a scanner object positioned at the beginning of an item entry in a dungeon file. Each item entry in a dungeon file lists the category of the item, the aliases of the item, the weight of the item, and the commands for the item with the messages to be printed for the commands. If the category is equal to weapon then this item has all the qualities of other items, but it also has a damage quantity, so that when the weapon is used against a non-player character it decreases their health by that number.
            @param s the scanner object that reads an item entry of a dungeon file to instantiate an item object in that dungeon.
            @throws Item.NoItemException if the scanner object is not positioned at the start of an item entry, now the scanner's cursor is positioned one line past where it was.
            @throws Dungeon.IllegalDungeonFormatException if the dungeon file is not formatted the way that the item constructor expected it to be, so the item entry did not end with the item delimiter.

        */
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
