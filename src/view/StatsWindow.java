package view;

import controller.Controller;
import model.ItemType;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StatsWindow extends JPanel {
    Map<ItemType, JLabel> itemLabels = new HashMap<>();
    JLabel movesLeft;

    public StatsWindow(Map<ItemType, Integer> fruitsPopped) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel movesLeftLabel = new JLabel("\nMoves Left:");
        movesLeftLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(movesLeftLabel);

        movesLeft = new JLabel("Moves: " + Controller.getNumberOfMoves());
        movesLeft.setFont(new Font("Serif", Font.PLAIN, 20));
        add(movesLeft);

        add(new JLabel("\n"));

        JLabel fruitsPoppedLabel = new JLabel("\nFruits popped:");
        fruitsPoppedLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(fruitsPoppedLabel);

        fruitsPopped.keySet().forEach(item -> {
            JLabel itemLabel = new JLabel(item.toString() + " " + 0);
            itemLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            itemLabels.put(item, itemLabel);
            add(itemLabels.get(item));
        });

        setVisible(true);
    }

    public void updateStats(Map<ItemType, Integer> fruitsPopped) {
        fruitsPopped.forEach((itemType, amount) -> {
            itemLabels.get(itemType).setText(itemType.toString() + " " + amount);
        });
        movesLeft.setText("Moves: " + Controller.getNumberOfMoves());
        revalidate();
        repaint();
    }
}
