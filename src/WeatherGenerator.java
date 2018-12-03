/**
*A random weather event in generated and executed through this class
*
*@author Isabella
*/

public class WeatherGenerator{

	private WeatherEvent event;
/**
*The constructor for the Weather Generator class that takes in a weather event.
*
*@param event a weather event that needs to be executed
*/
	public WeatherGenerator(WeatherEvent event){
		this.event = event;
	}
/**
*Returns a string that tells how the weather event affected the game as well as a new exit and new rooms that have opened in coorespondence to the weather event occuring.
*
*@return a  string that states the effect to the game, the new exit(s), and new room(s).
*/
	public String execute(){
		Room src = this.event.getSrc();
		String dir = this.event.getDir();
		Room dest = this.event.getDest();
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Exit exit = new Exit(dir, src, dest);
		return this.event.getWeatherDescription();
	}
}
