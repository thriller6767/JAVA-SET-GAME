package set.controller;

import set.MainListener;
import set.model.GameModel;
import set.record.Instruction;
import set.record.RecordingReader;
import set.record.command.*;
import set.view.GameView;
import set.view.toolbar.PlaybackToolbarListener;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * This Class specifies the actions in playBack mode.
 */
public class PlaybackGameController extends SoloGameController implements PlaybackToolbarListener {

    private RecordingReader recordingReader;
    private CommandRegister commandRegister;

    /**
     * Initiate the recordingReader if the player selects a file
     */
    public PlaybackGameController(GameModel m, GameView v, MainListener main) {
        super(m, v, main);
        commandRegister = initCommandFactory();
    }
    
    /**
     * @return the name of the selected File (),
     *          empty if the player does not choose a file
     */
    @Override
    public Optional<String> selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        String dir = System.getProperty("user.home");
        fileChooser.setCurrentDirectory(new File(dir));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return Optional.of(selectedFile.getAbsolutePath());
        } else {
            return Optional.empty();
        }
    }

    /**
     * if the player clicks 'Next Step' button, the game will display the next step
     */
    @Override
    public void nextStep() {
        Optional<Instruction> next = recordingReader.nextInstruction();
        if (next.isPresent()) {
            commandRegister.runCommand(next.get(), gameModel);
        } else {
            JOptionPane.showMessageDialog(gameView, "End of recording reached!");
        }
    }

    /**
     * Registers the commands (Load, Hint, Add, and Click) for the PlayBack
     * Game Mode
     * @return
     */
    private CommandRegister initCommandFactory() {
        CommandRegister commandRegister = new CommandRegister();

        commandRegister.addCommand(new LoadCommand(this, gameView));
        commandRegister.addCommand(new HintCommand(this, gameView));
        commandRegister.addCommand(new AddCommand(this, gameView));
        commandRegister.addCommand(new ClickCommand(this, gameView));

        return commandRegister;
    }

    /**
     * Instead of loading the whole recorded GameModel in the constructor,
     *      we created an empty panel first until the player choose the file.
     *      And because we only record on time, both recording and playBack do
     *      NOT accept newGame.
     */
    @Override
    public void newGame() {
        Optional<String> filename = selectFile();
        if (filename.isPresent()) {
            try {
                recordingReader = new RecordingReader(filename.get());

                // Run the first step automatically
                // This will always be a "load" command.
                nextStep();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            mainListener.goToMainMenu();
        }
    }

}
