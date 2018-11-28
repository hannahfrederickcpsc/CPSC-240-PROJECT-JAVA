/** A <tt>DropEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which drops that item into the current {@link Room}.
    @author Hannah
*/
class DropEvent extends Event{
	private String command;
	private String itemName;
	private Room currRoom;

	/** Constructs a new <tt>DropEvent</tt> object with the command that contains the verb and the item that causes a drop event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a drop event associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a drop event to occur.
        */
	public DropEvent(String command, String itemName, Room currRoom){
		super(command);
		this.command = command;
		this.itemName = itemName;	
		this.currRoom = currRoom;
	}

	/** Executes the <tt>DropEvent</tt> object after a certain command is used with a specific item so that the item is dropped into the room that the user was in when the command was called and is removed from the user's inventory if the item was stored there.
        */
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Item item = d.getItem(this.itemName);
		if(g.getInventory().contains(item)){
			g.removeFromInventory(item);
			currRoom.add(item);
		}

	}
}
