/** Represents an certain type of item that commands can be called on against non-player characters to decrease their health. Each item entry in a dungeon file lists the category of the item, if the category is equal to weapon then this item has all the qualities of other items, but it also has a damage quantity, so that when the weapon is used against a non-player character it decreases their health by that number.
    @author zorkaholics
*/
public class Weapon extends Item{
	private int Damage;
}
