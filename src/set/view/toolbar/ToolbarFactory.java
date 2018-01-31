package set.view.toolbar;

import set.GameMode;
import set.MainListener;

public class ToolbarFactory {

    /**
     * Create the game mode depending on the game mode the user clicked.
     *
     * @param mode game mode the user clicked.
     * @param mainListener call back to the main screen.
     * @return SoloToolBarView or TutorialToolBarView.
     */
    public static ToolbarView buildToolbar(GameMode mode, MainListener mainListener) {
        if (mode == GameMode.SOLO || mode == GameMode.RECORDING) {
            return new SoloToolbarView(mainListener);
        } else if (mode == GameMode.TUTORIAL) {
            return new TutorialToolBarView(mainListener);
        } else {
            return new PlaybackToolbarView(mainListener);
        }
    }


}
