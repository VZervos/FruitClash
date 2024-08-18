package view;

import controller.Controller;
import model.Board;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class GameBoard extends JPanel implements ActionListener {
    private ItemButton[][] gameBoard = new ItemButton[Board.BoardWidth][Board.BoardHeight];

    public GameBoard(Board board) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/background.png"))); // TODO
//        Image image;
//        image = icon.getImage();
//        image = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
//        icon = new ImageIcon(image);
//        setContentPane(new JLabel(icon));
        setLayout(new GridLayout(Board.BoardWidth, Board.BoardHeight));
        refreshBoard(board);
        setVisible(true);
    }


    public void refreshBoard(Board board) {
        removeAll();
        List<Item> boardItems = board.getAllBoardItems();
        for (Item item : boardItems) {
            int XPos = item.getXPosition();
            int YPos = item.getYPosition();
            ItemButton itemButton = new ItemButton(item, XPos, YPos);
            itemButton.getButton().addActionListener(this);
            gameBoard[XPos][YPos] = itemButton;
            add(itemButton.getButton());
        }
        // Repaint and revalidate to reflect changes
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getName();
        int XPos = Integer.parseInt(buttonName.substring(0, 1));
        int YPos = Integer.parseInt(buttonName.substring(1, 2));
        Controller.handleClick(XPos, YPos);

        System.out.println(buttonName);
//        Item item = (Item) e.getSource();
//        System.out.println(((JButton) e.getSource()).getText());
    }

    public void selectItem(int x, int y) {
        JButton button = gameBoard[x][y].getButton();
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    public void deselectItem(int x, int y) {
        JButton button = gameBoard[x][y].getButton();
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
    }
}
