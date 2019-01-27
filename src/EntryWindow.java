import javax.swing.*;
import java.util.List;
import java.util.Random;

public class EntryWindow {
    private JPanel InitialScreen;
    private JCheckBox toadetteAchievementsCheckBox;
    private JCheckBox purpleCoinAchievementsCheckBox;
    private JCheckBox rollingInCoinsCheckBox;
    private JCheckBox jumpRopeMoonsCheckBox;
    private JCheckBox volleyballMoonsCheckBox;
    private JTextField seedField;
    private JButton randomSeedButton;
    private JButton generateRunButton;
    private List<Moon> generatedList;

    private EntryWindow(JFrame thisWindow) {
        toadetteAchievementsCheckBox.setSelected(true);
        toadetteAchievementsCheckBox.addActionListener(e -> {
            if (toadetteAchievementsCheckBox.isSelected()) {
                purpleCoinAchievementsCheckBox.setEnabled(true);
                rollingInCoinsCheckBox.setEnabled(true);
            } else {
                purpleCoinAchievementsCheckBox.setEnabled(false);
                rollingInCoinsCheckBox.setEnabled(false);
            }
        });
        jumpRopeMoonsCheckBox.setSelected(true);
        volleyballMoonsCheckBox.setSelected(true);

        Random seedRandomizer = new Random();
        seedField.setText(Long.toString(Math.abs(seedRandomizer.nextLong())));
        randomSeedButton.addActionListener(e -> seedField.setText(Long.toString(Math.abs(seedRandomizer.nextLong()))));

        generateRunButton.addActionListener(e -> {
            //TODO: Open RunWindow for the generated route
            long seed;
            try {
                seed = Long.parseLong(seedField.getText());
                if (seed < 0) {
                    throw new NumberFormatException();
                }
                // Note that the randomize method already behaves properly if toadette is off and other achievement
                // options are on.
                generatedList = (new Randomizer()).randomize(toadetteAchievementsCheckBox.isSelected(),
                        purpleCoinAchievementsCheckBox.isSelected(),
                        rollingInCoinsCheckBox.isSelected(),
                        jumpRopeMoonsCheckBox.isSelected(),
                        volleyballMoonsCheckBox.isSelected(),
                        seed,
                        500);
            } catch (NumberFormatException unused) {
                JOptionPane.showMessageDialog(InitialScreen, "Seed must be a number between 0 and " +
                        Long.MAX_VALUE + ".", "Invalid Seed", JOptionPane.ERROR_MESSAGE);
            }

            thisWindow.setVisible(false);
            RunWindow frame = new RunWindow(generatedList, thisWindow);
            frame.setContentPane(frame.getMainScreen());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Darker Side Randomizer");
        frame.setContentPane(new EntryWindow(frame).InitialScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
