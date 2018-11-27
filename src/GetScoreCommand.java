/**
 *A <tt>GetScoreCommand</tt> represents the command given by the adventurer to retrieve
 * their score.
 *
 * @author Matt 
 */
class GetScoreCommand extends Command{
	/**
	 * Constructs a new <tt>GetScoreCommand</tt> object.
	 */
	GetScoreCommand(String command){
		super(command);
	}

	/**
	 * Carries out the process to get the adventurers score expressed as a phrase
	 * corresponding to the adventurers actual integer score value.
	 *
	 * @return the phrase corresponding to the adventurers integer health value.
	 */
	public String execute(){
		GameState g = GameState.instance();
		int score = g.getAdventurersScore();
		if(score > 100){
			return "You have accumulated " + score + " points, achieving a rank of Dungeon Master.\n";
		}
		if(score > 79){
			return "You have accumulated " + score + " points, achieving a rank of Expert.\n";
		}
		if(score > 59){
			return "You have accumulated " + score + " points, achieving a rank of Pro.\n";
		}
		if(score > 39){
			return "You have accumulated " + score + " points, achieving a rank of Novice.\n";
		}
		if(score > 19){
			return "You have accumulated " + score + " points, achieving a rank of Rookie.\n";
		}
		else{
			return "Wow.. you only have " + score + " points, achieving a rank of Absolute Pleb.\n";
		}
	}
}
	
