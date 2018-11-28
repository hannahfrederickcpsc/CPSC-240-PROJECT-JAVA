public class EventFactory{
	private static EventFactory theInstance;

	public static synchronized EventFactory instance(){
		if(theInstance == null){
			return new EventFactory();
		}
		return theInstance;
	}

	private EventFactory(){
	}


	public Event parse(String event, String itemName, Room currRoom){
		if(event.startsWith("Transform")){
			return new TransformEvent(event, itemName);
		}
		else if(event.startsWith("Teleport")){
			return new TeleportEvent(event);
		}
		else if(event.startsWith("Disappear")){
			return new DisappearEvent(event, itemName, currRoom);
		}
		else if(event.startsWith("Die")){
			return new DieEvent(event);
		}
		else if(event.startsWith("Wound")){
			return new WoundEvent(event);
		}
		else if(event.startsWith("Score")){
			return new ScoreEvent(event);
		}
		else if(event.startsWith("Win")){
			return new WinEvent(event);
		}
		else if(event.startsWith("Drop")){
			return new DropEvent(event, itemName);
		}
		return null;

	}
}


