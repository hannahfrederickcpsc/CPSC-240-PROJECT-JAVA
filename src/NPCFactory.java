import java.util.Scanner;

public class NPCFactory{
	
	private static NPCFactory theInstance;

	class NoNPCException extends Exception{}
	public static synchronized NPCFactory instance(){
		if(theInstance == null){
			theInstance = new NPCFactory();
		}
		return theInstance;
	}

	private NPCFactory(){}

	public NPC parse(Scanner s, Dungeon d, boolean initState)throws NoNPCException{
		String type = s.nextLine();
		if(type.equals("===")){
			throw new NPCFactory.NoNPCException();
		}
		type = type.replace("Type:","");
		if(type.equals("Friendly")){
			return new Friendly(type,s,d, initState);
		}
		else if(type.equals("Monster")){
			return new Monster(type,s,d, initState);
		}
		return new Thief(type,s,d, initState);

		
		
		
	}
}
