class LookCommand extends Command{
	private String look;
	LookCommand(String look){
		super(look);
		this.look = look;
		}

	String execute(){
		GameState g = GameState.instance();
		return g.getAdventurersCurrentRoom().getDesc();
	}
}

