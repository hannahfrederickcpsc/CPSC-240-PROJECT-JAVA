
import java.util.Scanner;
/** 
*A NoWeatherException class that extends Exception. Thrown where there is NoWeatherEvent to be performed.
*
*@author Isabella
*/
/** 
*A weather event object is generated from WeatherGnerator and changes the state of the game by opening a new exit, 
as well as new rooms, for the game after the adventurer completes a number of moves in the game.
*
*@author Isabella
*/
class WeatherEvent{

	public static class NoWeatherException extends Exception{}
	private String desc;
	private String dir;
	private String name;
	private Room src;
	private Room dest;
/**
*The constructor for the Weather Event class that is initializd from a scanner.
*
*@param s scanner that is used to read information from the zork and sav files
*/
	public WeatherEvent(Scanner s, Dungeon d)throws NoWeatherException{
		this.name = s.nextLine();
		if(this.name.equals("===")){
			throw new NoWeatherException();
		}
		this.src = d.getRoom(s.nextLine());
		this.dir = s.nextLine();
		this.dest = d.getRoom(s.nextLine());
		this.desc = s.nextLine();
		String currLine = s.nextLine();
		while(!currLine.equals("---")){
			this.desc += "\n" + currLine;
			currLine = s.nextLine();
		}
		this.desc += "\n";	
	}
/**
*Returns the string that is the name of the weather event.
*
*@return the string name of the event.
*/
	public String getName(){
		return this.name;
	}
/**
*Returns the room that is the destination of the weather event.
*
*@return the room that the weather event opens up to.
*/
	public Room getDest(){
		return this.dest;
	}
/**
*Returns the room that the weather event occurs in.
*
*@return the room the the weather event occurs in.
*/
	public Room getSrc(){
		return this.src;
	}
/**
*Returns a description of the weather event.
*
*@return the string that gives a description of the weather event
*/
	public String getWeatherDescription(){
		return this.desc;
	}
/**
*Returns the drection of the exit the weather event opens when it occurs.
*
*@return the string of the direction of the exit made by the weather object occurring.
*/

	public String getDir(){
		return this.dir;
	}
}
