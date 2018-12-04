
import java.util.*;
/**
*The CommandFactory  instantiates a new command object based on the users input.
*
*@author Isabella
*/
public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );
/**
*Returns the one instance of the command factory in the game. If not initialzed when called, then it will instantiate the new instance.
*
*@return the instance of the Command Factory.
*/
    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }
/**
*The constructor for the Command Factory.
*/
    private CommandFactory() {
    }
/**
*Returns a command object based on the users input. If the command is not understood, then the method will return an unknown command.
*
*@param command a string that the user typed into the command prompt for zork
*@return the command object that corresponds to the command entered into the command prompt.
*/
    public Command parse(String command, Scanner commandLine) {
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
	else if(command.equals("score")){
                return new GetScoreCommand(command);
	}
	else if(command.equals("health")){
                return new GetHealthCommand(command);
	}
	else if(command.startsWith("drop")){
		String dropReplace = command.replace("drop ","");
		return new DropCommand(dropReplace);
	}
	else if(command.equals("pause")){
                return new PauseCommand(command, commandLine);
        }
	else if(command.startsWith("engage")){
                return new EngageCommand(command, commandLine);
        }
	else if(command.equals("speak")){
                return new SpeakCommand(command, commandLine);
        }
	else if(command.contains(" ")){
		String[] specificCommand = command.split(" ");
		if(specificCommand.length == 2){
		return new ItemSpecificCommand(specificCommand[0], specificCommand[1]);
		}

		else{
		return new ItemSpecificCommand(specificCommand[0], "");
		}
	}
	else if(!command.contains(" ")){
                return new ItemSpecificCommand(command, "");
	}
	return new UnknownCommand(command);
	
    }

}

