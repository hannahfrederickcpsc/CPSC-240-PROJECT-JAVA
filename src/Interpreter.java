import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter dungeonFile.zork|saveFile.sav.";

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

                command = promptUser(commandLine);
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    private static String promptUser(Scanner commandLine) {

        System.out.print("> ");
        return commandLine.nextLine();
    }

}
