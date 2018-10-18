class SaveCommand extends Command{
	private String saveFileName;

	SaveCommand(String saveFileName){
		super(saveFileName);
		this.saveFileName = saveFileName;
	}

	String execute(){
		try{
			GameState.instance().store();
			return "Data saved to " + GameState.DEFAULT_SAVE_FILE + GameState.SAVE_FILE_EXTENSION + ".\n";
		}
		catch (Exception e) {
			System.out.println("Culdn't save!");
			e.printStackTrace();
			return "";
		}
	}
}
