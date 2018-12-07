import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
/** A <tt>Weapon</tt> represents an certain type of {@link Item} that a {@link Command} can be called on against non-player characters ({@link NPC}) to decrease their health.
    @author Hannah
*/
public class Weapon extends Item{
	private int damage;
	private String primaryName;
	private int weight;
	private int value;
	private String type;

	private Hashtable<String,String> messages;
	private ArrayList<String> verbs;
	private Hashtable<String,ArrayList<String>> events;

	/** Constructs a new <tt>Weapon</tt> object with a scanner object positioned at the beginning of an item entry in a dungeon file. Each item entry in a dungeon file lists the category of the item, the aliases of the item, the weight of the item, and the commands for the item with the messages to be printed for the commands. If the category is equal to weapon then this item has all the qualities of other items, but it also has a damage quantity, so that when the weapon is used against a non-player character it decreases their health by that number.
            @param s the scanner object that reads an item entry of a dungeon file to instantiate an item object in that dungeon.
            @throws Item.NoItemException if the scanner object is not positioned at the start of an item entry, now the scanner's cursor is positioned one line past where it was.
            @throws Dungeon.IllegalDungeonFormatException if the dungeon file is not formatted the way that the item constructor expected it to be, so the item entry did not end with the item delimiter.

        */
	public Weapon(Scanner s)throws ItemFactory.NoItemException{
		super();
		this.type = "weapon";
		messages = new Hashtable<String,String>();
		verbs =  new ArrayList<String>();
		events = new Hashtable<String,ArrayList<String>>();
		this.primaryName = s.nextLine();
		this.damage = Integer.valueOf(s.nextLine());
		this.weight = Integer.valueOf(s.nextLine());
		this.value = Integer.valueOf(s.nextLine());
		String currLine = s.nextLine();
		//array to capture all split up strings in the line
		String[] tempSplit;
		//message to be added to hashtable
		String message = "";
		//verb to be added to hashtable
		String verb = "";
		//string that will replace the ':' in the line
		String realCurrLine = "";
		int startIndex;
		int endIndex;
		while(!currLine.equals("---")){
			//splitting contents into array
			tempSplit = currLine.split(":");
			//populating verb and message variables
			for (int i = 0; i < tempSplit.length; ++i){
				if(i == 0){
				verb = tempSplit[i];
					if(verb.contains("[")){
						startIndex = verb.indexOf("[") + 1;
						endIndex = verb.indexOf("]");
						String event = verb.substring(startIndex, endIndex);
						ArrayList<String> eventsAL = new ArrayList<String>();
						verb = verb.substring(0,startIndex - 1);
						//verb = verb.replace(verb.substring(startIndex, endIndex), "");
						if(event.contains(",")){
							String [] tempEvents = event.split(",");
							for(String k : tempEvents){
								eventsAL.add(k);
							}	
						}
						else{
							eventsAL.add(event);
						}			
					this.events.put(verb,eventsAL);
					}
				}
				else{
				message += tempSplit[i] + " ";
				}
			}
			
			//adding message to ht, setting verb as reference to message
			messages.put(verb,message);
			message = "";
			currLine = s.nextLine();
		
		
		}
	}	

	/** Returns the number of points that are subtracted from a non-player character's health when the user types an {@link AttackCommand} with that specific item while engaged with that non-player character.
            @return the number of damage points associated with that weapon item, which is found in each weapon item entry of a dungeon file.
        */
	public int getDamageValue(){
		return this.damage;
	}
	public boolean goesBy(String name){
		String [] splitPrimaryName = this.primaryName.split(",");
		for (int i = 0; i < splitPrimaryName.length; ++i){
			if (name.equals(splitPrimaryName[i])){
				return true;
			}
		}
		return false;
	}
	public String getPrimaryName(){
		String name = this.primaryName;
		String [] nameSplit = name.split(",");
		return nameSplit[0];
	}
	public String getMessageForVerb(String verb){
		return messages.get(verb);
	}
	public ArrayList<String> getVerbs(){
		Set<String> verbSet  = messages.keySet();
		for(String verb: verbSet)
		{
			verbs.add(verb);
		}
		return verbs;
	}
	public ArrayList<String> getEvents(String key){
		return this.events.get(key);
	}	
	public int getValue(){
		return this.value;
	}
	public int getWeight(){
		return this.weight;
	}
}
