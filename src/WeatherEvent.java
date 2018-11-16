import java.util.Scanner;
class NoWeatherException extends Exception{}
class WeatherEvent{
	private String desc;
	private String dir;
	private String name;
	private Room src;
	private Room dest;

	public WeatherEvent(Scanner s){}

	public String getName(){}

	public Room getDest(){}

	public Room getSrc(){}

	public String getWeatherDescription(){}

	public String getDir(){}
}
