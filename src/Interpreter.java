import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/** The <tt>Interpreter</tt> contains the main method of the entire program so it is the top level class of this program.
    @author Hannah
*/
public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter dungeonFile.zork|saveFile.sav.";

    /** Reads in the command line argument to use a dungeon file or a save file and requests user input to proceed through the {@link Dungeon} of that file. If the command line argument is a valid dungeon file or a valid save file in the current directory, then the user input will be read as a {@link Command} that will change the {@link GameState} of the dungeon. If this is a valid dungeon file, then the user starts in the entry room of that dungeon and types commands that change the state of the dungeon. If this is a valid save file, then the user resumes in the last room that the user was in and preserves the state of the dungeon when the user last saved. The user will be prompted for input after every turn until the user types q, which will quit the game.
        @param args[] the command line argument.
	@throws FileNotFoundException if the command line argument is not a dungeon file or a save file that is in the current directory.
     
    */
    public static void main(String args[])throws FileNotFoundException {
	File a = new File("../files");
	File[]files;
	File b = new File(".");
	boolean savPresent = false;
	for(File file: b.listFiles()){
		if(file.getName().equals(GameState.DEFAULT_SAVE_FILE + ".sav")){
			savPresent = true;
		}
	}
	if(a.isDirectory()){
		files = a.listFiles();

	}
	else{
		files = new File[0];
	}
		
        if (args.length < 1) {
            System.err.println(USAGE_MSG);
	    System.out.println("\033[4m\033[1mUsable .zork files:\033[0m");
	    for(File file: files){
		    System.out.println("\033[1m" + file.getName() + "\033[0m");
	    }
	    if(savPresent){
		    System.out.println("\033[4m\033[1mUsable Save file:\033[0m\n\033[1m"
				    + GameState.DEFAULT_SAVE_FILE + ".sav\033[0m");
	    }
            System.exit(1);
        }

        String command;
        Scanner commandLine = new Scanner(System.in);

        try {
		try{
            state = GameState.instance();
            if (args[0].endsWith(".zork")) {
                state.initialize(new Dungeon(args[0],true));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
            } else if (args[0].endsWith(".sav")) {
                state.restore(args[0]);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
            } 
		else {
                System.err.println(USAGE_MSG);
		System.out.println("\033[4m\033[1mUsable .zork files:\033[0m");
		for(File file: files){
			System.out.println("\033[1m" + file.getName() + "\033[0m");
		}
		if(savPresent){
			System.out.println("\033[4m\033[1mUsable Save File:\033[0m\n\033[1m" + 
					GameState.DEFAULT_SAVE_FILE + ".sav\033[0m");
		}

                System.exit(2);
            }
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(2);
		}

            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);
	    Scanner stdin = new Scanner(System.in);

            while (!command.equals("q")) {
		    System.out.print(
                    CommandFactory.instance().parse(command).execute());
		    if(state.getAdventurersHealth() <= 0){
				System.out.println("Oh no.. you have died!\n" + 
						"Results:\n" +
						"Score: " + state.getAdventurersScore());
				System.exit(0);
		    }
                command = promptUser(commandLine);
		
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    /** Returns the user input up to the return line taken in with a scanner object.
        @param commandLine the scanner object that reads the user input.
	@return the string that the scanner read from the user input.
     
    */
    private static String promptUser(Scanner commandLine) {

        System.out.print("> ");
        return commandLine.nextLine();
    }

}
