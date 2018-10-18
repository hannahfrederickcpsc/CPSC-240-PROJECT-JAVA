class MovementCommand extends Command{
	private String dir;

	MovementCommand(String dir){
		super(dir);
		this.dir = dir;
	}

	String execute(){
		Room currentRoom = 
			GameState.instance().getAdventurersCurrentRoom();
		Room nextRoom = currentRoom.leaveBy(dir);
			if (nextRoom != null) {
				GameState.instance().setAdventurersCurrentRoom(nextRoom);
				return "\n" + nextRoom.describe() + "\n";
			}
			else{
				return "Sorry, you can't go " + dir + "from " + currentRoom.getTitle() + ".\n";
		}
	}
}




