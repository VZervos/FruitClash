package view;

import controller.Controller;
import model.ItemType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame implements ActionListener, ChangeListener {

    private final MainMenu mainMenuFrame;
    private final JSlider fruitsNumberSlider;
    private final JSlider movesSlider;
    private final JButton saveButton;
    private final JLabel fruitsNumberLabel;
    private final JLabel movesLabel;
    private JPanel settingsPanel;

    public Settings(MainMenu mainMenuFrame) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Settings");
        setBounds(200, 100, 800, 600);
        setLayout(new BorderLayout());
        this.mainMenuFrame = mainMenuFrame;

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JLabel settingsTitle = new JLabel("Settings");
        settingsTitle.setFont(new Font("Serif", Font.BOLD, 60));
        settingsPanel.add(settingsTitle);

        fruitsNumberLabel = new JLabel();
        fruitsNumberLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        settingsPanel.add(fruitsNumberLabel);

        fruitsNumberSlider = new JSlider(4, Controller.MAX_FRUITS - 1);
        fruitsNumberSlider.setValue(Controller.MAX_FRUITS - 1);
        fruitsNumberSlider.setSnapToTicks(true);
        fruitsNumberSlider.setPaintTrack(true);
        fruitsNumberSlider.setPaintTicks(true);
        fruitsNumberSlider.setPaintLabels(true);
        fruitsNumberSlider.addChangeListener(this);
        settingsPanel.add(fruitsNumberSlider);
        fruitsNumberLabel.setText("Fruits amount: " + fruitsNumberSlider.getValue());


        movesLabel = new JLabel();
        movesLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        settingsPanel.add(movesLabel);

        movesSlider = new JSlider(5, Controller.MAX_MOVES);
        movesSlider.setValue(Controller.MAX_MOVES);
        movesSlider.setSnapToTicks(true);
        movesSlider.setPaintTrack(true);
        movesSlider.setPaintTicks(true);
        movesSlider.setPaintLabels(true);
        movesSlider.addChangeListener(this);
        settingsPanel.add(movesSlider);
        movesLabel.setText("Moves amount: " + movesSlider.getValue());

        add(settingsPanel, BorderLayout.NORTH);

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Serif", Font.PLAIN, 30));
        saveButton.addActionListener(this);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller.setNumberOfFruits(fruitsNumberSlider.getValue() + 1);
        Controller.setNumberOfMoves(movesSlider.getValue());
        mainMenuFrame.activateFrame();
        dispose();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == fruitsNumberSlider)
            fruitsNumberLabel.setText("Fruits Number: " + fruitsNumberSlider.getValue());
        else if (e.getSource() == movesSlider)
            movesLabel.setText("Moves Amount: " + movesSlider.getValue());
        else
            assert (false);
    }
}
