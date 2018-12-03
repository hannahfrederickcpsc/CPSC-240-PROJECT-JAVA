
import java.util.Scanner;
/** A <tt>WinEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which ends the program so the user wins.
    @author Hannah
*/

public class WinEvent extends Event{
	private String command;
	private String verb;
	private String itemName;

	/** Constructs a new <tt>WinEvent</tt> object with the command that contains the verb and the item that causes a win event with that item. The string command always matches a valid item because the <tt>ItemSpecificCommand</tt> class checks that the item is not null before it parses the command as an event. 
            @param command the string that the user types which contains the verb with the item that will cause a win event to occur.
	    @param verb the string that is the verb that when typed before the name of the item will cause a win event to occur.
            @param itemName the string that is the name of the item that when typed after the verb will cause a win event to occur.
        */
	public WinEvent(String command, String verb, String itemName){
		super(command);
		this.command = command;
		this.verb = verb;
		this.itemName = itemName;
	}

	/** Executes the <tt>WinEvent</tt> object after a certain command is used with a specific item so that the program will end and the user will win.

        */
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
                Item item = d.getItem(itemName);
		System.out.println(item.getMessageForVerb(verb));
		System.out.println("You win!\n" + 
				"Results: \n" + 
				"Score: " + g.getAdventurersScore() + "\n" +
				"Health: " + g.getAdventurersHealth());
		System.exit(1);
	}

}
