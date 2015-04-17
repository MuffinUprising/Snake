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
    public JPanel rootPanel;
    private JCheckBox warpWallsCheckBox;
    private JCheckBox soundsCheckBox;
    private JButton quitButton;
    private JComboBox speedComboBox;
    private JLabel gameSpeedLabel;


    protected OptionsGUI() {

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
    }
}
