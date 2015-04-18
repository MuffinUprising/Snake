package casey;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by casey on 4/16/15.
 */
public class OptionsGUI extends JFrame{
    private JLabel optionsLabel;
    public JPanel optionsPanel;
    private JCheckBox warpWallsCheckBox;
    private JCheckBox soundsCheckBox;
    private JButton quitButton;
    private JComboBox speedComboBox;
    private JLabel gameSpeedLabel;
    private JButton playButton;




    protected OptionsGUI() {

        final String s = "slow";
        final String m = "medium";
        final String f = "fast";
        speedComboBox.addItem(s);
        speedComboBox.addItem(m);
        speedComboBox.addItem(f);


        //warp walls check box
        warpWallsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (warpWallsCheckBox.isSelected()) {
                    SnakeGame.setWarpWallsOn(true);
                } else {
                    SnakeGame.setWarpWallsOn(false);
                }
            }
        });

        //sounds checkbox
        soundsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(soundsCheckBox.isSelected()) {
                    SnakeGame.setSoundsOn(true);
                } else {
                    SnakeGame.setSoundsOn(false);
                }

            }
        });
        //speed combo box(to be implemented)
        speedComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String speed = speedComboBox.getSelectedItem().toString();
                if (speed.equals(s)) {
                    SnakeGame.setGameSpeed(500);
                } else if (speed.equals(m)) {
                    SnakeGame.setGameSpeed(300);
                } else if (speed.equals(f)) {
                    SnakeGame.setGameSpeed(200);
                }
            }
        });
        //quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitButton.isSelected()){
                    System.exit(0);
                }
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
