/**
 * A <tt>SaveCommand</tt> represents the command given by the adventurer to save 
 * the game at its current state.
 *
 * @author zorkaholics
 */
class SaveCommand extends Command{
	private String saveFileName;
	/**
	 * Constructs a new <tt>SaveCommand</tt> object setting the file name to where
	 * the data should be stored.
	 *
	 * @param saveFileName the name of the file where the save data should be stored.
	 */
	SaveCommand(String saveFileName){
		super(saveFileName);
		this.saveFileName = saveFileName;
	}
	/**
	 * Carries out the process of saving the game with the directed save file name,
	 * returning the phrase responding to the game being saved.
	 *
	 * @return the phrase responding to the game being saved.
	 */
	String execute(){
		try{
			GameState.instance().store();
			return "Data saved to " + GameState.DEFAULT_SAVE_FILE + GameState.SAVE_FILE_EXTENSION + ".\n";
		}
		catch (Exception e) {
			System.out.println("Couldn't save!");
			e.printStackTrace();
			return "";
		}
	}
}
