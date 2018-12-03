import java.util.Scanner;

/** A <tt>FoodItem</tt> represents an certain type of {@link Item} that a {@link Command} can be called on to increase the hunger and increase the health of the user.
    @author Hannah
*/
public class FoodItem extends Item{
	private int healthChange;
	private int hungerChange;

	/** Constructs a new <tt>FoodItem</tt> object with a scanner object positioned at the beginning of an item entry in a dungeon file. Each item entry in a dungeon file lists the category of the item, the aliases of the item, the weight of the item, and the commands for the item with the messages to be printed for the commands. If the category is equal to food, then this item has all the qualities of other items, but it also has a hunger quantity and a wound quantity so that when the food is eaten, it increases the user's hunger and the user's health by that number.
            @param s the scanner object that reads an item entry of a dungeon file to instantiate an item object in that dungeon.
            @throws Item.NoItemException if the scanner object is not positioned at the start of an item entry, now the scanner's cursor is positioned one line past where it was.
            @throws Dungeon.IllegalDungeonFormatException if the dungeon file is not formatted the way that the item constructor expected it to be, so the item entry did not end with the item delimiter.

        */
	public FoodItem(Scanner s) throws Item.NoItemException, Dungeon.IllegalDungeonFormatException{
		super(s);
	}

	/** Returns the number of points that are added to the user's health when the user types a certain command with that specific item. The greater the number of points in the user's health, the better the state of health for the user.
            @return the number of health points associated with that food item, which is found in each food item entry of a dungeon file.
        */
	public int getHealthValue(){
		return this.healthChange;
	}

	/** Returns the number of points that are added to the user's hunger when the user types a certain command with that specific item. The greater the number of points in the user's health, the better the state of health/hunger for the user. The user's hunger decreases every time that the user moves, so the user must eat food items to replenish their hunger/restore their health.
            @return the number of hunger points associated with that food item, which is found in each food item entry of a dungeon file.
        */
	public int getHungerValue(){
		return this.hungerChange;
	}
}
