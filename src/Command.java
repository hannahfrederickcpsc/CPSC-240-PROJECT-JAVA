
/**
 * A <tt>Command</tt> represents the abstract base class for all commands in zork.
 *
 * @author Matt 
 */
abstract class Command {

    private String dir;     
    /**
     * Constructs a new <tt>Command</tt> object.
     *
     * @param dir any command phrase.
     */
    Command(String dir) {
        this.dir = dir;
    }
    /**
     * Carries out the process desired using the command phrase given, returning
     * the correct string responding to the command.
     *
     * @return the correct string responding to the command.
     */
    abstract String execute();
      
}
