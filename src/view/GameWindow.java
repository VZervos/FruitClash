package view;

import model.Board;
import model.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameWindow extends JFrame implements ActionListener {

    private GameBoard gameBoard;
    private JLabel[] goalInfo = new JLabel[9];
    private JLabel[] gameStats = new JLabel[8];

    public GameWindow(Board board, int[] settings, Timer timer) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FruitClash");
        setBounds(200, 100, 800, 600);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/background.png"))); // TODO
        Image image;
        image = icon.getImage();
        image = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        setContentPane(new JLabel(icon));
        setLayout(new BorderLayout(0, 3));
        setPreferredSize(new Dimension(800, 600));
        gameBoard = new GameBoard(board);

        Container contentPane = getContentPane();
        contentPane.add(new JLabel(), BorderLayout.PAGE_START);
        contentPane.add(new JLabel(), BorderLayout.PAGE_END);
        contentPane.add(new JLabel("Goal info"), BorderLayout.LINE_START);//createGoalInfo(), BorderLayout.LINE_START);
        contentPane.add(new JLabel("Game stats"), BorderLayout.LINE_END);//createGameStats, BorderLayout.LINE_END);
        contentPane.add(gameBoard, BorderLayout.CENTER);//createGameBoard, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
