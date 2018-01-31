package set.controller;

import set.MainListener;
import set.model.CardModel;
import set.model.GameModel;
import set.record.CommandConstants;
import set.record.SerializeUtils;
import set.view.GameView;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 *
 */
public class RecordingController extends SoloGameController {

    private String filePath;
    private boolean firstStart;

    /**
     * Recording Controller constructor
     * @param m
     * @param v
     * @param main
     */
    public RecordingController(GameModel m, GameView v, MainListener main) {
        super(m, v, main);
        this.firstStart = true;
    }

    /**
     * Record clicking cards.
     *
     * @param cardModel the card get clicked
     */
    @Override
    public void cardClicked(CardModel cardModel) {
        String command = CommandConstants.CLICK_CARD + " "
                + gameModel.getCardModelIndex(cardModel);
        writeCommand(command);
        super.cardClicked(cardModel);

    }

    /**
     * Record drawing three cards.
     */
    @Override
    public void drawThreeCards() {
        super.drawThreeCards();
        writeCommand(CommandConstants.ADD_THREE);
    }

    /**
     * Record showing hint.
     */
    @Override
    public void showHint() {
        super.showHint();
        writeCommand(CommandConstants.HINT);
    }

    /**
     * Calls new game.
     */
    @Override
    public void newGame() {

        if (firstStart) {

            Optional<String> file = chooseRecordingFile();
            if (file.isPresent()) {
                filePath = file.get();
                firstStart = false;
                newGame();
            } else {
                mainListener.goToMainMenu();
            }
        } else {

            super.newGame();
            try {
                String command = CommandConstants.LOAD + " "
                        + SerializeUtils.serializeObject(gameModel);
                writeCommand(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Opens a dialog window to Choose a file name to record to.
     */
    private Optional<String> chooseRecordingFile() {
        JFileChooser saveFile = new JFileChooser();
        int saveOption = saveFile.showSaveDialog(gameView);

        if (saveOption == JFileChooser.APPROVE_OPTION) {
            return Optional.of(saveFile.getSelectedFile().getAbsolutePath());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Writes a given string to the file
     * @param command
     */
    private void writeCommand(String command) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(filePath, true));
            writer.write(command + "\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

}
