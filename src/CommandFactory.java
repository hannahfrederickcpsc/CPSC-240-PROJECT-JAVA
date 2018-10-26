import java.util.*;

public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    public Command parse(String command) {
	if (MOVEMENT_COMMANDS.contains(command)) {
            return new MovementCommand(command);
        } else if (command.equals("save")){
            // For now, only one type of command object, to move and to save.
            return new SaveCommand(command);
        }
	else if (command.equals("look")){
		return new LookCommand(command);
	}
	else if(command.startsWith("take")){
		String takeReplace = command.replace("take ","");
		return new TakeCommand(takeReplace);
	}
	else if(command.equals("i")){
		return new InventoryCommand(command);
	}
	else if(command.startsWith("drop")){
		String dropReplace = command.replace("drop ","");
		return new DropCommand(dropReplace);
	}
	else if(command.contains(" ")){
		String[] specificCommand = command.split(" ");
		if(specificCommand.length == 2){
		return new ItemSpecificCommand(specificCommand[0], specificCommand[1]);
		}
		else{
		return new ItemSpecificCommand(command, "");
		}
	}
	else if(!command.contains(" ")){
                return new ItemSpecificCommand(command, "");
	}
	return new UnknownCommand(command);
	
    }

}

