import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

/** An <tt>Item</tt> represents an object that a {@link Command} can be called on to change the {@link GameState} of the {@link Dungeon} and to print these changes to the screen. Each item entry in a dungeon file lists the category of the item, the aliases of the item, the weight of the item, and the commands for the item with the messages to be printed for the commands.
    @author Hannah
*/
public class Item{

	/** Specifies what happens when the scanner object is not positioned at the start of an item entry, this happens when the scanner has reached the end of th e items section of a dungeon file. 
	 
	*/
	class NoItemException extends Exception{}

	private String primaryName;
	private int weight;

	//hashtable <verb,messageOfThatVerb> 
	private Hashtable <String,String> messages;
	private ArrayList<String> verbs;	
	//constructor to read items from .zork file
	
	/** Constructs a new <tt>Item</tt> object with a scanner object positioned at the beginning of an item entry in a dungeon file.
	    @param s the scanner object that reads an item entry of a dungeon file to instantiate an item object in that dungeon.
	    @throws NoItemException if the scanner object is not positioned at the start of an item entry, now the scanner's cursor is positioned one line past where it was.
	    @throws IllegalDungeonFormatException if the dungeon file is not formatted the way that the item constructor expected it to be, so the item entry did not end with the item delimiter.
	 
	*/
	public Item(Scanner s)throws NoItemException, Dungeon.IllegalDungeonFormatException{
		messages = new Hashtable<String,String>();
		verbs =  new ArrayList<String>();
		this.primaryName = s.nextLine();
		if(primaryName.equals("===")){
			throw new NoItemException();
		}
		this.weight = Integer.valueOf(s.nextLine());
		
		String currLine = s.nextLine();
		//array to capture all split up strings in the line
		String[] tempSplit;
		//message to be added to hashtable
		String message = "";
		//verb to be added to hashtable
		String verb = "";
		//string that will replace the ':' in the line
		String realCurrLine = "";
		while(!currLine.equals("---")){
			//replacing ':'
			realCurrLine = currLine.replace(":"," ");
			//splitting contents into array
			tempSplit = realCurrLine.split(" ");
			//populating verb and message variables
			for (int i = 0; i < tempSplit.length; ++i){
				if(i == 0){
				verb = tempSplit[i];
				}
				else{
				message += tempSplit[i] + " ";
				}
			}
			//adding message to ht, setting verb as reference to message
			messages.put(verb,message);

			//resetting message
			message = "";
			currLine = s.nextLine();
		}
	
	}//end Item()
			
	//returns boolean if a verb "goes by" a specific name
	/** Determines if the <tt>Item</tt> object has an alias that matches the name that is provided, the aliases are listed in each item entry of a dungeon file.
	    @param name the name that the program checks to see if it is one of the alias for that item.
	    @return true if the name is one of the aliases and false if the name is not one of the aliases.
	*/
	public boolean goesBy(String name){
		String [] splitPrimaryName = this.primaryName.split(",");
		for (int i = 0; i < splitPrimaryName.length; ++i){
			if (name.equals(splitPrimaryName[i])){
				return true;
			}
		}
		return false;
	}//end goesBy()

	/** Returns the primary name for the <tt>Item</tt> object, this is the first alias listed in each item entry of a dungeon file.
	    @return the name that is the first alias for the item object, this is the alias that the program uses for the item.
	*/
	public String getPrimaryName(){
		String name = this.primaryName;
		String [] nameSplit = name.split(",");
		return nameSplit[0];
	}//end getPrimaryName()

	/** Returns the string that is printed when an {@link ItemSpecificCommand} associated with the item is called, this is in an item entry of a dungeon file.
	    @param verb the verb that is used right before an alias of the item to call a command on that item.
	    @return the string to be printed to the screen right after this command is called on the item.
	*/
	public String getMessageForVerb(String verb){
		return messages.get(verb);
	}//end getMessageForVerb()
	
	/** Returns the list of aliases for the <tt>Item</tt> object and the unit weight of the <tt>Item</tt> object, which is found in each item entry of a dungeon file.
	    @return a string that describes that item including the list of aliases and the unit weight of that item.
	*/
	public String toString(){
		return "Names: " + this.primaryName +
			"\nWeight: " + this.weight;
	}//end toString();

	/** Returns the unit weight of that <tt>Item</tt> object, if the item's weight will take the user's inventory over maximum capacity then the user can't take that item.
	    @return the weight associated with that item, which is found in each item entry of a dungeon file.
	*/
	public int getWeight(){
		return this.weight;
	}

	/** Returns the list of verbs that can be used right before an alias of the item to call a command on it, these verbs can only be called on certain items.
	    @return the verbs that can be called on that item in commands, these verbs only work with certain items.
	*/
	public ArrayList<String> getVerbs(){
		Set<String> verbSet  = messages.keySet();
		for(String verb: verbSet)
		{
			verbs.add(verb);
		}
		return verbs;
	}		
}//end Item class
