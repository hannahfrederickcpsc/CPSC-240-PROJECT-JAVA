/** Represents an abstract event that occurs when a certain command is typed with a specific item which changes the state of the game. An event is abstract so it cannot be instantiated, but there are types of events that can be instantiated and different events change the state in different ways.
    @author zorkaholics
*/
abstract class Event{

	/** Instructs that event objects that inherit from the abstract event object must contain a constructor for that specific event since the abstract event constructor does not contain any information. The contents of the event constructor will be different for different types of events.
	 
	*/
	Event(){}

	/** Instructs that event objects that inherit from the abstract event object must contain an execute method for that specific event since the abstract execute method does not contain any information. The contents of the execute method will be different for different types of events.
	 
	*/
	abstract void execute();
}
