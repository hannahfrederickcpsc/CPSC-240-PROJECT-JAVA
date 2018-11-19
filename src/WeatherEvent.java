/** 
*A weather event object is generated from WeatherGnerator and changes the state of the game by opening a new exit, as well as new rooms, for the game after the adventurer completes a number of moves in the game.
*
*@author Isabella
*/

import java.util.Scanner;
/** 
*A NoWeatherException class that extends Exception. Thrown where there is NoWeatherEvent to be performed.
*
*@author Isabella
*/
class NoWeatherException extends Exception{}
class WeatherEvent{
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
	public WeatherEvent(Scanner s){}
/**
*Returns the string that is the name of the weather event.
*
*@return the string name of the event.
*/
	public String getName(){}
/**
*Returns the room that is the destination of the weather event.
*
*@return the room that the weather event opens up to.
*/
	public Room getDest(){}
/**
*Returns the room that the weather event occurs in.
*
*@return the room the the weather event occurs in.
*/
	public Room getSrc(){}
/**
*Returns a description of the weather event.
*
*@return the string that gives a description of the weather event
*/
	public String getWeatherDescription(){}
/**
*Returns the drection of the exit the weather event opens when it occurs.
*
*@return the string of the direction of the exit made by the weather object occurring.
*/

	public String getDir(){}
}
