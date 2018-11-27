/**
 * A <tt>TradeCommand</tt> represents the command given to trade items with a NPC.
 * This command is a {@link EngageMenuCommand}.
 *
 * @author Matt
 */
class TradeCommand extends EngageMenuCommand{

	/**
	 * Constructs a new <tt>TradeCommand</tt> object.
	 */
	TradeCommand(String command){
		super(command);
	}

	/**
	 * Carries out trade interactions with an engaged NPC returning a phrase of
	 * what was swapped in the trade.
	 *
	 * @return phrase responding to what was swapped in the trade with an NPC.
	 */ 
	public String execute(){}
}
