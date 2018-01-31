package set.record;

/**
 * Abstraction around a parsed command from recording file.
 */
public class Instruction {

    private String command;
    private String args;

    public Instruction(String command, String args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Returns the string name of the command.
     *
     * @return String
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the arguments of the command.
     *
     * @return String
     */
    public String getArgs() {
        return args;
    }
}
