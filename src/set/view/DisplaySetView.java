package set.view;

import set.model.CardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * JFrame view to cycle through highlighted sets.
 */
public class DisplaySetView extends JFrame {

    private static final String MESSAGE = "There are %s set(s). Click 'cycle' to cycle through and highlight sets";

    private JLabel message;
    private JButton cycle;
    private GameView gameView;
    private List<List<CardModel>> sets;
    private int setIndex = 0;

    /**
     * Constructor for the DisplaySetView.
     * @param gameView
     * @param sets
     * @throws HeadlessException
     */
    public DisplaySetView(GameView gameView, List<List<CardModel>> sets) throws HeadlessException {
        super();
        this.gameView = gameView;
        this.sets = sets;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.message = new JLabel(String.format(MESSAGE, sets.size()));
        this.cycle = new JButton("Cycle");


        cycle.addActionListener(new CycleClickedAction());

        getContentPane().add(message);
        getContentPane().add(cycle);

        pack();

    }

    private class CycleClickedAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sets.size() > 0) {
                gameView.highlightOneSet(sets.get(setIndex));
                setIndex++;
                if (setIndex >= sets.size()) {
                    setIndex = 0;
                }
            }
        }
    }

}
