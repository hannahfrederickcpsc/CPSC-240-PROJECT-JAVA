/** A <tt>WinEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which ends the game so the user wins.
    @author Hannah
*/

public class WinEvent extends Event{
	private String command;
	private String verb;
	private String itemName;

	/** Constructs a new <tt>WinEvent</tt> object with the command that contains the verb and the item that causes a win event with that item. An item entry in a dungeon file can contain verbs that can be typed right before an alias of the item and can have a win event associated with it.
            @param command the string that the user types which contains the verb with the item that will cause a win event to occur.
        */
	public WinEvent(String command, String verb, String itemName){
		super(command);
		this.command = command;
		this.verb = verb;
		this.itemName = itemName;
	}

	/** Executes the <tt>WinEvent</tt> object after a certain command is used with a specific item so that the game will end and the user will win.

        */
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
                Item item = d.getItem(itemName);
                System.out.println(item.getMessageForVerb(verb));
		System.exit(1);
	}

}
