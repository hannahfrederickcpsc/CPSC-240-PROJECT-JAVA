import java.util.Scanner;
class ItemFactory{
	private static ItemFactory theInstance;
	class NoItemException extends Exception{}
	public static synchronized ItemFactory instance(){
		if(theInstance == null){
			theInstance = new ItemFactory();
		}
		return theInstance;
	}

	private ItemFactory(){}

	public Item parse(Scanner s)throws NoItemException{
		String category = s.nextLine();
		if(category.equals("===")){
			throw new NoItemException();
		}
		category = category.replace("category:","");
		if(category.equals("weapon")){
			return new Weapon(s);
		}
		else if(category.equals("item")){
			return new Item(s);
		}
		return null;
	}
}
