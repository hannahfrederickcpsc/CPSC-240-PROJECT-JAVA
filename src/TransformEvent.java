/** A <tt>TransformEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which eliminates that item from the {@link Dungeon} and replaces it with a new item in the dungeon.
    @author Hannah
*/

public class TransformEvent extends Event{
	private String command;
	private String itemName;
	private Room currRoom;

	/** Constructs a new <tt>TransformEvent</tt> object with the command that contains the verb and the item that causes a transform event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a transform event associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a transform event to occur.
        */
	public TransformEvent(String command, String itemName, Room currRoom){
		super(command);
		this.command = command;
		this.itemName = itemName;
		this.currRoom = currRoom;
	}

	/** Executes the <tt>TransformEvent</tt> object after a certain command is used with a specific item so that the item disappears from the dungeon and is replaced with a new item in the same location; the original item does not appear anywhere in the dungeon again.

        */
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Item item = d.getItem(this.itemName);
		String newItem = "";
		String [] tempItem = command.split("(");
		newItem = tempItem[1];
		newItem = newItem.substring(0, newItem.length() - 1);
		if(g.getInventory().contains(item)){
			g.removeFromInventory(item);
			g.addToInventory(d.getItem(newItem));
		}
		if(currRoom.getContents().contains(item)){
			currRoom.remove(item);
			currRoom.add(d.getItem(newItem));
		}
		if(d.getItemList().contains(item)){
			d.removeItem(item);
			d.add(d.getItem(newItem));
		}
	}
}
