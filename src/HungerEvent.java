class HungerEvent extends Event{
	private String command;

	public HungerEvent(String command){
		super(command);
		this.command = command;
	}

	public void execute(){
		GameState g = GameState.instance();
		int startIndex = command.indexOf("(") + 1;
		int endIndex = command.indexOf(")");
		String hungerString  = command.substring(startIndex, endIndex);
		int score = Integer.parseInt(hungerString);
		g.changeHunger(score);
	}
}

