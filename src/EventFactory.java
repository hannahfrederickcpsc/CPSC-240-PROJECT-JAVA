/** The <tt>EventFactory</tt> instantiates a new {@link Event} when the user types an {@link ItemSpecificCommand} with a certain {@link Item}, which will change the {@link GameState} of the {@link Dungeon}.
    @author Hannah
*/
public class EventFactory{
	private static EventFactory theInstance;

	/** Returns the one instance of the event factory. If the event factory has not been initialized yet, then it will instantiate the one instance of event factory.
	    @return the one instance of the event factory.
	*/
	public static synchronized EventFactory instance(){
		if(theInstance == null){
			return new EventFactory();
		}
		return theInstance;
	}

	/** Constructs a new event factory object.
	 
	*/
	private EventFactory(){
	}


	/** Returns a certain event object based on the specific command that was typed with a certain item. If there is no event object that matches the command, then it will return a null object.
	    @param event a string that begins with the event that is being caused.
	    @param itemName a string that is the name of the item that causes the event.
	    @param verb a string that is verb that when called with the item causes the event.
	    @param currRoom the room object that was the current room when the command was called.
	*/
	public Event parse(String event, String itemName, String verb, Room currRoom){
		if(event.startsWith("Transform")){
			return new TransformEvent(event, itemName, currRoom);
		}
		else if(event.startsWith("Teleport")){
			return new TeleportEvent(event);
		}
		else if(event.startsWith("Disappear")){
			return new DisappearEvent(event, itemName, currRoom);
		}
		else if(event.startsWith("Die")){
			return new DieEvent(event, verb, itemName);
		}
		else if(event.startsWith("Wound")){
			return new WoundEvent(event);
		}
		else if(event.startsWith("Score")){
			return new ScoreEvent(event);
		}
		else if(event.startsWith("Win")){
			return new WinEvent(event, verb, itemName);
		}
		else if(event.startsWith("Drop")){
			return new DropEvent(event, itemName, currRoom);
		}
		return null;

	}
}


