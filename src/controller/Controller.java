package controller;

import model.Board;
import model.Item;
import model.Timer;
import view.GameBoard;
import view.GameWindow;
import view.MainMenu;

public class Controller {
    static int[] gameStats = new int[7];
    static int[] totalStats = new int[9];
    static int[] settings = new int[5];
    static Board board = new Board();
    static int movesLeft = -1;
    static Timer timer = new Timer();
    static Item selectedItem = null;
    static GameWindow gameWindow = null;

    public static void start(int[] Settings) {
//        System.arraycopy(Settings, 0, settings, 0, settings.length);
        board.initializeBoard();
        board.evaluateBoard();
        gameWindow = new GameWindow(board, settings, timer);
        //GameBoard gb = new GameBoard(board, settings, gameStats, timer);
    }

    public Controller(int[] settings) {
        start(settings);
    }

    private static void loadStats() {
        // TODO
    }

    public static void handleClick(int x, int y) {
        GameBoard gameBoard = gameWindow.getGameBoard();
        if (selectedItem != null) {
            gameBoard.deselectItem(selectedItem.getXPosition(), selectedItem.getYPosition());
            board.executeSwap(selectedItem.getXPosition(), selectedItem.getYPosition(), x, y);
            selectedItem = null;
            gameBoard.refreshBoard(board);
        } else {
            selectedItem = board.getItemAt(x, y);
            gameBoard.selectItem(x, y);
        }
    }

    public static void main(String[] args) {
        loadStats();
        MainMenu mm = new MainMenu();
    }
}
