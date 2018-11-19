/** An <tt>Event</tt> represents an abstract event that occurs when a certain {@link Command} is typed with a specific {@link Item} which changes the {@link GameState}. Event is abstract so it cannot be instantiated, but there are types of events that can be instantiated and different events change the state in different ways.
    @author Hannah
*/
abstract class Event{

	/** Instructs that certain event objects that inherit from the abstract event object must contain a constructor for that specific event since the abstract event constructor does not contain any information. The contents of the event constructor will be different for different types of events.
	 
	*/
	Event(){}

	/** Instructs that certain event objects that inherit from the abstract event object must contain an execute method for that specific event since the abstract execute method does not contain any information. The contents of the execute method will be different for different types of events.
	 
	*/
	abstract void execute();
}
