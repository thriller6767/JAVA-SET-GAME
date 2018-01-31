package set.view.toolbar;

import set.record.command.Command;

import java.util.Optional;

/**
 * This interface specifies what PlaybackMode need to do
 */
public interface PlaybackToolbarListener extends SoloToolbarListener  {

    /**
     *
     * @return the file name chosen by the user
     */
    Optional<String> selectFile();

    /**
     * After player clicks 'Next Step' button, the game will display the next step
     */
    void nextStep();
}
