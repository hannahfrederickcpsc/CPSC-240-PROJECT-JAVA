/** An <tt>Event</tt> represents an abstract event that occurs when a certain {@link Command} is typed with a specific {@link Item} which changes the {@link GameState}. Event is abstract so it cannot be instantiated, but there are types of events that can be instantiated and different events change the state in different ways.
    @author Hannah
*/
abstract class Event{

	/** Instructs that all event objects that inherit from the abstract event object must contain a constructor with all the necessary information for that specific event since the abstract event constructor does not contain any information. The contents of the event constructor will be different for different types of events.
	    @param command the string that the user types which contains the verb with the item that will cause a certain event to occur. 
	*/
	Event(String command){}

	/** Instructs that all event objects that inherit from the abstract event object must contain an execute method with all the necessary information for that specific event since the abstract execute method does not contain any information. The contents of the execute method will be different for different types of events.
	 
	*/
	abstract void execute();
}
