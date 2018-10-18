import java.util.Hashtable;
import java.util.Scanner;

class NoItemException extends Exception{
}

public class Item{
	private String primaryName;
	private int weight;

	//hashtable <verb,messageOfThatVerb> 
	private Hashtable <String,String> messages;
	
	//constructor to read items from .zork file
	public Item(Scanner s)throws NoItemException{
		messages = new Hashtable<String,String>();
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
	public boolean goesBy(String name){
		String [] splitPrimaryName = this.primaryName.split(",");
		for (int i = 0; i < splitPrimaryName.length; ++i){
			if (name.equals(splitPrimaryName[i])){
				return true;
			}
		}
		return false;
	}//end goesBy()

	public String getPrimaryName(){
		return this.primaryName;
	}//end getPrimaryName()

	public String getMessageForVerb(String verb){
		return messages.get(verb);
	}//end getMessageForVerb()
	
	public String toString(){
		return "Primary names: " + this.primaryName +
			"\nWeight: " + this.weight;
	}//end toString();
}//end Item class
