/**
 * A <tt>MovementCommand</tt> represents the command given to allow the adventurer
 * to move freely between rooms.
 *
 * @author Matt 
 */
class MovementCommand extends Command{
	private String dir;
	
	/**
	 * Constructs a new <tt>MovementCommand</tt> object setting which direction
	 * the adventurer would like to move.
	 *
	 * @param dir direction the user would like to move.
	 */
	MovementCommand(String dir){
		super(dir);
		this.dir = dir;
	}
	
	/**
	 * Carries out the command to move in a direction returning the description
	 * of the newly entered room. If the direction given is not a direction allowed
	 * to be taken from the current room, the method returns the correct string 
	 * responding to such issue.
	 *
	 * @return the description of the newly entered room if the movement command
	 * succeeds.
	 */
	String execute(){
		Room currentRoom = 
			GameState.instance().getAdventurersCurrentRoom();
		Room nextRoom = currentRoom.leaveBy(dir);
		Dungeon d = GameState.instance().getDungeon();
		WeatherEvent event;
			if (nextRoom != null) {
				GameState.instance().setAdventurersCurrentRoom(nextRoom);
				GameState.instance().changeMoves(1);
				if(GameState.instance().getMoves() == 5){
					event = d.getRandomWeatherEvent();
					if(event != null){
					WeatherGenerator g = new WeatherGenerator(event);
					GameState.instance().changeMoves(-5);
					return "\n" + g.execute() + "\n" +
						nextRoom.describe() + "\n";
					}
				}
				return "\n" + nextRoom.describe() + "\n";
			}
			else{
				return "Sorry, you can't go " + dir + " from " + currentRoom.getTitle() + ".\n";
		}
	}
}




