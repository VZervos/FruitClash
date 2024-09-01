package view;

import controller.Controller;
import model.ItemType;

import javax.swing.*;
import java.util.*;

public class StatsWindow extends JPanel {
    Map<ItemType, JLabel> itemLabels = new HashMap<>();
    JLabel movesLeft;

    public StatsWindow(Map<ItemType, Integer> fruitsPopped) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        movesLeft = new JLabel("Moves: " + Controller.getNumberOfMoves());
        add(movesLeft);

        add(new JLabel("\n"));

        add(new JLabel("\nFruits popped:"));
        fruitsPopped.keySet().forEach(item -> {
            itemLabels.put(item, new JLabel(item.toString() + " " + 0));
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
