import java.util.Scanner;

public class NPCFactory{
	class NoNPCException extends Exception{}
	private static NPCFactory theInstance;


	public static synchronized NPCFactory instance(){
		if(theInstance == null){
			theInstance = new NPCFactory();
		}
		return theInstance;
	}

	private NPCFactory(){}

	public NPC parse(Scanner s, Dungeon d)throws NoNPCException{
		String type = s.nextLine();
		if(type.equals("===")){
			throw new NoNPCException();
		}
		type = type.replace("Type: ","");
		if(type.equals("Friend")){
			return new Friendly(s,d);
		}
		else if(type.equals("Monster")){
			return new Monster(s,d);
		}
		return new Thief(s,d);
	}
}
