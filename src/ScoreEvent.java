/** A <tt>ScoreEvent</tt> represents a type of {@link Event} that occurs when an {@link ItemSpecificCommand} is typed with that specific {@link Item} which adds the points associated with that {@link Command} to the user's score.
    @author Hannah
*/
class ScoreEvent extends Event{
	private String command;

	/** Constructs a new <tt>ScoreEvent</tt> object with the command that contains the verb and the item that causes a score event with that item. The string command always matches a valid item because the <tt>ItemSpecificCommand</tt> class checks that the item is not null before it parses the command as an event.
            @param command the string that the user types which contains the verb with the item that will cause a score event to occur.
        */
	public ScoreEvent(String command){
		super(command);
		this.command = command;
	}

	/** Executes the score event object after a certain command is used with a specific item so that the point value associated with that score event is added to the user's score.
        */
	public void execute(){
		GameState g = GameState.instance();
		int startIndex = command.indexOf("(") + 1;
		int endIndex = command.indexOf(")");
		String scoreString  = command.substring(startIndex, endIndex);
		int score = Integer.parseInt(scoreString);
		g.changeScore(score);
		if(g.getAdventurersScore() >= 25){
			System.out.println("You win!\n" + 
				"Results: \n" + 
				"Score: " + g.getAdventurersScore() + "\n" +
				"Health: " + g.getAdventurersHealth());
			System.exit(1);

		}
	}
}
