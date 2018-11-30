/** A <tt>TransformEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which eliminates that item from the {@link Dungeon} and replaces it with a new item in the dungeon.
    @author Hannah
*/

public class TransformEvent extends Event{
	private String command;
	private String itemName;
	private Room currRoom;

	/** Constructs a new <tt>TransformEvent</tt> object with the command that contains the verb and the item that causes a transform event with that item. The string command always matches a valid item because the <tt>ItemSpecificCommand</tt> class checks that the item is not null before it parses the command as an event.
            @param command the string that the user types which contains the verb with the item that will cause a transform event to occur.
            @param itemName the string that is the name of the item that when typed after the verb will cause a transform event to occur.
	    @param currRoom the room object that is the user's current room in the dungeon.
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
		int startIndex = command.indexOf("(") + 1;
                int endIndex = command.indexOf(")");
                String newItem  = command.substring(startIndex, endIndex);
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
