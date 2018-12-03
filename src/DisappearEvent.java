/** A <tt>DisappearEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which eliminates the item from the {@link Dungeon}.
    @author Hannah
*/

class DisappearEvent extends Event{
	private String command;
	private String itemName;
	private Room currRoom;	
	/** Constructs a new <tt>DisappearEvent</tt> object with the command that contains the verb and the item that causes a disappear event with that item. The string command always matches a valid item because the <tt>ItemSpecificCommand</tt> class checks that the item is not null before it parses the command as an event.
            @param command the string that the user types which contains the verb with the item that will cause a disappear event to occur.
            @param verb the string that is the verb that when typed before the name of the item will cause a disappear event to occur.
            @param itemName the string that is the name of the item that when typed after the verb will cause a disappear event to occur.
	*/
	public DisappearEvent(String command, String itemName, Room currRoom){
		super(command);
		this.command = command;
		this.itemName = itemName;
		this.currRoom = currRoom;
	}

	/** Executes the <tt>DisappearEvent</tt> object after a certain command is used with a specific item so that the item is removed from where it is stored, whether that is the room or the inventory, and it doesn't appear in the dungeon again.
        */
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Item item = d.getItem(this.itemName);
		if(g.getInventory().contains(item)){
			g.removeFromInventory(item);
		}
		if(currRoom.getContents().contains(item)){
			currRoom.remove(item);	
		}
		if(d.getItemList().contains(item)){
			d.removeItem(item);
		}
		
	}
}
