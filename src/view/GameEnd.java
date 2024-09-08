package view;

import model.ItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static javax.swing.SwingConstants.CENTER;

public class GameEnd extends JFrame implements ActionListener {

    public GameEnd(Map<ItemType, Integer> fruitsPopped) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game Over");
        setBounds(200, 100, 800, 600);
        setLayout(new GridLayout(0, 1));

        JLabel gameOverLabel = new JLabel("Game over!");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 40));
        gameOverLabel.setHorizontalAlignment(CENTER);
        add(gameOverLabel);

        add(new JLabel("\n"));

        JLabel finalStatsLabel = new JLabel("Final Stats:");
        finalStatsLabel.setFont(new Font("Serif", Font.BOLD, 30));
        finalStatsLabel.setHorizontalAlignment(CENTER);
        add(finalStatsLabel);

        fruitsPopped.keySet().forEach(item -> {
            JLabel itemLabel = new JLabel(item.toString() + " " + 0);
            itemLabel.setFont(new Font("Serif", Font.PLAIN, 30));
            add(itemLabel);
        });

        add(new JLabel("\n"));

        JButton exitButton = new JButton("Exit game");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(this);
        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
