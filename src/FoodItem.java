/** A <tt>Food</tt> represents an certain type of {@link Item} that a {@link Command} can be called on to increase the hunger and increase the health of the user. Each item entry in a dungeon file lists the category of the item, if the category is equal to food then this item has all the qualities of other items, but it also has a hunger quantity and a wound quantity, so that when the food is eaten it increases their hunger and their health by that number.
    @author HF
*/
public class FoodItem extends Item{
	private int healthChange;
	private int hungerChange;

	/** Returns the number of points that are added to the user's health when the user types a certain command with that specific item.
            @return the number of health points associated with that food item, which is found in each food item entry of a dungeon file.
        */
	public int getHealthValue(){
		return this.healthChange;
	}

	/** Returns the number of points that are added to the user's hunger when the user types a certain command with that specific item.
            @return the number of hunger points associated with that food item, which is found in each food item entry of a dungeon file.
        */
	public int getHungerValue(){
		return this.hungerChange;
	}
}
