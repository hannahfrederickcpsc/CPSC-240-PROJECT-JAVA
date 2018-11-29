/**
 * A <tt>GetHealthCommand</tt> represents the command given to retrieve the 
 * adventurers current state of their health.
 *
 * @author Matt
 */
class GetHealthCommand extends Command{

	/**
	 * Constructs a new <tt>GetHealthCommand</tt> object.
	 */
	GetHealthCommand(String command){
		super(command);
	}

	/**
	 * Carries out the command, returning a phrase corresponding to the 
	 * integer health value of the adventurer.
	 *
	 * @return Phrase corresponding to the adventurers health value.
	 */
	public String execute(){
		GameState g = GameState.instance();
		int health = g.getAdventurersHealth();
		if(health > 89){
			return "You feel amazing!\n╔════════════╗\n║████████████║[]\n╚════════════╝\n";
		}
		else if(health > 69){
			return "You are feeling ok, but not great.\n╔════════════╗\n║██████████  ║[]\n╚════════════╝\n";
		}
		else if(health > 49){
			return "You are feeling a little down.\n╔════════════╗\n║████████    ║[]\n╚════════════╝\n";
		}
		else if(health > 29){
			return "You are feeling like crap.\n╔════════════╗\n║██████      ║[]\n╚════════════╝\n";
		}
		else if(health > 9){
			return "You are close to giving up.. but not yet.\n╔════════════╗\n║████        ║[]\n╚════════════╝\n";
		}
		else{
			return "Bro... you are dying. Find food or something.\n╔════════════╗\n║██          ║[]\n╚════════════╝\n";
		}
	}
}
