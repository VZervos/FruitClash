package controller;

import model.Board;
import model.Item;
import model.ItemType;
import model.Timer;
import utilities.Utility;
import view.GameWindow;
import view.MainMenu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    public static final int MAX_MOVES = 100;
    public static final int MAX_FRUITS = ItemType.values().length;

    static Map<ItemType, Integer> itemPops = new HashMap<ItemType, Integer>();
    static int[] totalStats = new int[9];
    static int[] settings = new int[5];
    static Board board = new Board();
    static int movesLeft = MAX_MOVES / 2;
    static Timer timer = new Timer();
    static Item selectedItem = null;
    static GameWindow gameWindow = null;
    static int maxNumberOfFruits = MAX_FRUITS;

    public static void start(int[] Settings) {
//        System.arraycopy(Settings, 0, settings, 0, settings.length);
        board.initializeBoard();
        board.evaluateBoard();
        Arrays.stream(ItemType.values()).forEach(itemType -> {
            itemPops.put(itemType, 0);
        });
        itemPops.remove(ItemType.Empty);
        gameWindow = new GameWindow(board, itemPops, settings, timer);
        //GameBoard gb = new GameBoard(board, settings, gameStats, timer);
    }

    public Controller(int[] settings) {
        start(settings);
    }

    private static void loadStats() {
        // TODO
    }

    public static void handleClick(int x, int y) {
        if (selectedItem != null) {
            gameWindow.deselectItemOnGameboard(selectedItem.getXPosition(), selectedItem.getYPosition());
            Map<ItemType, Integer> fruitsPopped;
            fruitsPopped = board.executeSwap(selectedItem.getXPosition(), selectedItem.getYPosition(), x, y);
            if (!fruitsPopped.isEmpty())
                useMove();

            updateStats(fruitsPopped);
            gameWindow.updateStatsOnStatsWindow(itemPops);

            selectedItem = null;
            System.out.println("Done");
            board.printBoard();
            gameWindow.refreshGameboard(board);

            // TODO: Game end
            if (getNumberOfMoves() == 0)
                endGame();
        } else {
            selectedItem = board.getItemAt(x, y);
            gameWindow.selectItemOnGameboard(x, y);
        }
    }

    private static void endGame() {
        gameWindow.endGame(itemPops);
    }

    public static void setNumberOfFruits(int numberOfFruits) {
        maxNumberOfFruits = numberOfFruits;
    }

    private static void updateStats(Map<ItemType, Integer> fruitsPopped) {
        Utility.addMaps(itemPops, fruitsPopped);
        System.out.println(fruitsPopped);
    }

    public static void main(String[] args) {
        loadStats();
        MainMenu mm = new MainMenu();
    }

    public static int getNumberOfFruits() {
        return maxNumberOfFruits;
    }

    public static void setNumberOfMoves(int value) {
        movesLeft = value;
    }

    public static int getNumberOfMoves() {
        return movesLeft;
    }

    private static void useMove() {
        setNumberOfMoves(getNumberOfMoves() - 1) ;
    }
}
