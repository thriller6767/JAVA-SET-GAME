package set.record.command;

import set.GameMode;
import set.model.GameModel;
import set.record.Instruction;

import java.util.HashMap;
import java.util.Map;

/**
 * This class registers and runs commands according to the keyword.
 */
public class CommandRegister {

    private final Map<String, Command> commands;

    /**
     * Initiate a <keyword:command> HashMap
     */
    public CommandRegister() {
        this.commands = new HashMap<>();
    }

    /**
     * Registers a command with the specified keyword.
     *
     * @param command Command to register.
     */
    public void addCommand(Command command) {
        this.commands.put(command.getKeyword(), command);
    }

    /**
     * Takes the specified instruction and runs the corresponding command (if
     * it is registered).
     *
     * @param instruction Instruction to run.
     */
    public void runCommand(Instruction instruction, GameModel gameModel) {
        String command = instruction.getCommand();
        if (commands.containsKey(command)) {
            commands.get(command).execute(instruction.getArgs(), gameModel);
        }
    }
}
