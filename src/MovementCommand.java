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
		GameState g = GameState.instance();
		Room currentRoom = g.getAdventurersCurrentRoom();
		Room nextRoom = currentRoom.leaveBy(dir);
		Dungeon d = GameState.instance().getDungeon();
		WeatherEvent event;
			if (nextRoom != null) {

				g.setAdventurersCurrentRoom(nextRoom);
				g.changeMoves(1);

				if(g.getHungerPoints() <= 10)
				{
					g.changeHealth(5);
					System.out.println("You need to eat!!\n" + 
							"You are about to die!");
				}
				else if (g.getHungerPoints() <= 30)
				{
					g.changeHealth(3);
					System.out.println("Youre starving. Please eat.");

				}
				else if(g.getHungerPoints() <= 50)
				{
					g.changeHealth(1);
					System.out.println("You are getting hungry. Find food in the near future.");
				}
			
				
					g.changeHunger(-2);
				

				if((GameState.instance().getMoves() % 20) == 0){
					event = d.getRandomWeatherEvent();
					if(event != null){
					WeatherGenerator w = new WeatherGenerator(event);
					return "\n" + w.execute() + "\n" +
						nextRoom.describe() + "\n";
					}
				}
				if(g.companion() == true && g.getCompanion().getFollow() == true){
					g.getCompanion().follow();
				}
				else if(g.companion() == true && g.getCompanion().getFollow() == false){
					g.getCompanion().stay();
				}

				return "\n" + nextRoom.describe() + "\n";
			}
			else{
				return "Sorry, you can't go " + dir + " from " + currentRoom.getTitle() + ".\n";
		}
	}
}




