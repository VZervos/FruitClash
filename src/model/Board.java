package model;

import model.fruits.*;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    public final static int BoardWidth = 7;
    public final static int BoardHeight = 7;

    static Item[][] board = new Item[BoardHeight][BoardWidth];

    private Fruit addApple(int x, int y) {
        return new Apple(new int[]{x, y});
    }

    private Fruit addLemon(int x, int y) {
        return new Lemon(new int[]{x, y});
    }

    private Fruit addOrange(int x, int y) {
        return new Orange(new int[]{x, y});
    }

    private Fruit addGrapes(int x, int y) {
        return new Grapes(new int[]{x, y});
    }

    private Fruit addPear(int x, int y) {
        return new Pear(new int[]{x, y});
    }

    private Fruit addBlueberry(int x, int y) {
        return new Blueberry(new int[]{x, y});
    }

    public Item getItemAt(int x, int y) {
        if (x >= BoardHeight || y >= BoardWidth || board[x][y] == null)
            return null;
        return board[x][y];
    }

    public void setItemAt(int x, int y, Item item) {
        board[x][y] = item;
    }

    interface CreateFruit {
        Fruit addFruit(int x, int y);
    }

    private final CreateFruit[] addFruit = new CreateFruit[]{
            this::addApple,
            this::addLemon,
            this::addOrange,
            this::addGrapes,
            this::addPear,
            this::addBlueberry
    };

    private void swapItems(Item item1, Item item2) {
        Item tmp = getItemAt(item1.getXPosition(), item1.getYPosition());
        setItemAt(item1.getXPosition(), item1.getYPosition(), item2);
        setItemAt(item2.getXPosition(), item2.getYPosition(), tmp);

        int[] item1pos = new int[2];
        System.arraycopy(item1.getPosition(), 0, item1pos, 0, 2);
        item1.setPosition(item2.getPosition());
        item2.setPosition(item1pos);
    }

    private void swapItems(int x1, int y1, int x2, int y2) {
        Item item1 = getItemAt(x1, y1);
        Item item2 = getItemAt(x2, y2);
        swapItems(item1, item2);
    }

    public void executeSwap(int x1, int y1, int x2, int y2) {
        swapItems(getItemAt(x1, y1), getItemAt(x2, y2));
        System.out.println("Moved " + x1 + y1 + " " + x2 + y2);
        evaluateBoard();
        printBoard();
        if (!hasAvailableMoves()) {
            System.out.println("No available moves. Resetting the board.");
            initializeBoard();
            printBoard();
        }
    }


    public void evaluateBoard() {
        do {
            evaluteCombinations();
            printBoard();
        } while (fillGaps());
    }

    private void evaluteCombinations() {
        boolean isStillClearing;
        do {
            isStillClearing = false;
            isStillClearing = evaluateRow();
            isStillClearing = evaluateColumn() || isStillClearing;
        } while (isStillClearing);
    }

    private void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null)
                    System.out.print("0(x, x) ");
                else if (board[i][j].getItemType() == ItemType.Empty)
                    System.out.print("-(" + board[i][j].getXPosition() + ", " + board[i][j].getYPosition() + ") ");
                else
                    System.out.print(board[i][j].getItemType().toString().charAt(0) + "(" + board[i][j].getXPosition() + ", " + board[i][j].getYPosition() + ") ");
            }
            System.out.println();
        }
    }

    public boolean evaluateRow() {
        boolean clearedBoard = false;
        ItemType itemFoundType = null;
        List<Item> itemsToRemove = new ArrayList<>();
        for (int x = 0; x < BoardHeight + 1; x++) {
            clearedBoard = clearItems(itemsToRemove) || clearedBoard;
            itemsToRemove.clear();
            itemFoundType = null;
            for (int y = 0; y < BoardWidth + 1; y++) {
                Item currentItem = getItemAt(x, y);
                ItemType currentItemType = currentItem != null ? currentItem.getItemType() : null;

                if (x >= BoardHeight || itemFoundType == null || !itemFoundType.equals(currentItemType)) {
                    clearedBoard = clearItems(itemsToRemove) || clearedBoard;
                    itemsToRemove.clear();
                    itemFoundType = currentItemType;
                }
                itemsToRemove.add(currentItem);
            }
        }
        return clearedBoard;
    }

    public boolean evaluateColumn() {
        boolean clearedBoard = false;
        ItemType itemFoundType = null;
        List<Item> itemsToRemove = new ArrayList<>();
        for (int y = 0; y < BoardWidth + 1; y++) {
            clearedBoard = clearItems(itemsToRemove) || clearedBoard;
            itemsToRemove.clear();
            itemFoundType = null;
            for (int x = 0; x < BoardHeight + 1; x++) {
                Item currentItem = getItemAt(x, y);
                ItemType currentItemType = currentItem != null ? currentItem.getItemType() : null;

                if (x >= BoardWidth || itemFoundType == null || !itemFoundType.equals(currentItemType)) {
                    clearedBoard = clearItems(itemsToRemove) || clearedBoard;
                    itemsToRemove.clear();
                    itemFoundType = currentItemType;
                }
                itemsToRemove.add(currentItem);
            }
        }
        return clearedBoard;
    }

    private boolean fillGaps() {
        boolean filledGap = false;
        do {
            filledGap = false;
            for (int y = 0; y < BoardWidth; y++) {
                for (int i = 0; i < BoardHeight - 1; i++) {
                    int j = i + 1;
                    if (board[i][y].getItemType() != ItemType.Empty && board[j][y].getItemType() == ItemType.Empty) {
                        swapItems(i, y, j, y);
                        filledGap = true;
                    }
                }
            }
        } while (filledGap);
        return fillEmptyCells();
    }

    private boolean clearItems(List<Item> itemsToRemove) {
        if (itemsToRemove.size() > 2 && itemsToRemove.get(0).getItemType() != ItemType.Empty) {
            for (Item item : itemsToRemove) {
                int x = item.getXPosition();
                int y = item.getYPosition();
                setItemAt(x, y, new Empty(new int[]{x, y}));
            }
            System.out.print("Remove: [");
            itemsToRemove.forEach(i -> System.out.print(i.getItemType().toString().charAt(0) + "(" + i.getXPosition() + ", " + i.getYPosition() + ") "));
            System.out.println(itemsToRemove);
            return true;
        }
        return false;
    }

    public boolean fillEmptyCells() {
        boolean emptyCellFound = false;
        for (int i = 0; i < BoardHeight; i++) {
            for (int j = 0; j < BoardWidth; j++) {
                if (board[i][j].getItemType() == ItemType.Empty) {
                    board[i][j] = addFruit[Utility.getRandom(1, 6)].addFruit(i, j); // settings[0])].addFruit(i, j);
                    emptyCellFound = true;
                }
            }
        }
        return emptyCellFound;
    }

    public List<Item> getAllBoardItems() {
        List<Item> boardItems = new ArrayList<>();
        for (Item[] row : board) {
            boardItems.addAll(Arrays.asList(row));
        }
        return boardItems;
    }

    public void initializeBoard() {
        do {
            for (int x = 0; x < BoardHeight; x++) {
                for (int y = 0; y < BoardWidth; y++) {
                    board[x][y] = addFruit[Utility.getRandom(1, 6)].addFruit(x, y);
                }
            }
        } while (!hasAvailableMoves());
    }


    public boolean hasAvailableMoves() {
        // Check for horizontal swaps
        for (int x = 0; x < BoardHeight; x++) {
            for (int y = 0; y < BoardWidth - 1; y++) {
                swapItems(x, y, x, y + 1); // Swap horizontally
                boolean matchFound = evaluateRow() || evaluateColumn();
                swapItems(x, y, x, y + 1); // Swap back to original position

                if (matchFound) {
                    return true;
                }
            }
        }

        // Check for vertical swaps
        for (int x = 0; x < BoardHeight - 1; x++) {
            for (int y = 0; y < BoardWidth; y++) {
                swapItems(x, y, x + 1, y); // Swap vertically
                boolean matchFound = evaluateRow() || evaluateColumn();
                swapItems(x, y, x + 1, y); // Swap back to original position

                if (matchFound) {
                    return true;
                }
            }
        }

        return false;
    }

}
