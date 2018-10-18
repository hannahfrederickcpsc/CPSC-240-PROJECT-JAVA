class UnknownCommand extends Command{
	private String BogusCommand;

	UnknownCommand(String BogusCommand){
		super(BogusCommand);
		this.BogusCommand = BogusCommand;
	}

	String execute(){
		return "I don't understand the command '" + BogusCommand + "'.\n";
	}
}
