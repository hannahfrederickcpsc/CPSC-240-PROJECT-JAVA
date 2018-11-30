/** A <tt>DieEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which ends the program so the user loses.
    @author Hannah
*/

class DieEvent extends Event{
	private String command;
	private String verb;
	private String itemName;

	/** Constructs a new <tt>DieEvent</tt> object with the command that contains the verb and the item that causes a die event with that item. The string command always matches a valid item because the <tt>ItemSpecificCommand</tt> class checks that the item is not null before it parses the command as an event. 
	    @param command the string that the user types which contains the verb with the item that will cause a die event to occur.
	    @param verb the string that is the verb that when typed before the name of the item will cause a die event to occur.
	    @param itemName the string that is the name of the item that when typed after the verb will cause a die event to occur.
	*/
	public DieEvent(String command, String verb, String itemName){
		super(command);
		this.command = command;
		this.verb = verb;
		this.itemName = itemName;
	}

	/** Executes the <tt>DieEvent</tt> object after a certain command is used with a specific item so that the program will end and the user will lose. 
	 
	*/
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		Item item = d.getItem(itemName);
		System.out.println(item.getMessageForVerb(verb));
		System.out.println("════════════╗\n║            ║[]\n╚════════════╝\n");
		System.exit(0);
	}
}
