/** Represents an certain type of item that commands can be called on to increase the hunger and increase the health of the user. Each item entry in a dungeon file lists the category of the item, if the category is equal to food then this item has all the qualities of other items, but it also has a hunger quantity and a wound quantity, so that when the food is eaten it increases their hunger and their health by that number.
    @author zorkaholics
*/
public class FoodItem extends Item{
	private int healthChange;
	private int hungerChange;
}
