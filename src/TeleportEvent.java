/** A <tt>TeleportEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which puts the user in a random {@link Room} of the {@link Dungeon}.
    @author Hannah
*/

class TeleportEvent extends Event{
	private String command;

	/** Constructs a new <tt>TeleportEvent</tt> object with the command that contains the verb and the item that causes a teleport event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a teleport event associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a teleport event to occur.
        */
	public TeleportEvent(String command){
		super(command);
		this.command = command;
	}

	/** Executes the <tt>TeleportEvent</tt> object after a certain command is used with a specific item so that the user is put into a random room; it could be a room that was not visited or reachable before since it can be any room in the dungeon that is not the current room.

        */
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Room currRoom = g.getAdventurersCurrentRoom();
		Room randRoom = d.getRandomRoom();
		while(currRoom.equals(randRoom))
		{
			randRoom = d.getRandomRoom();
		}
		g.setAdventurersCurrentRoom(randRoom);
	}
	
}
