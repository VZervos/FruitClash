package model;

import controller.Controller;
import model.fruits.*;
import utilities.Utility;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    public final static int BoardWidth = 7;
    public final static int BoardHeight = 7;

    static Item[][] board = new Item[BoardHeight][BoardWidth];

    private Fruit addEmpty(int x, int y) { return new Empty(new int[]{x, y}); }

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
            this::addEmpty,
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

    public Map<ItemType, Integer> executeSwap(int x1, int y1, int x2, int y2) {
        swapItems(getItemAt(x1, y1), getItemAt(x2, y2));
        System.out.println("Moved " + x1 + y1 + " " + x2 + y2);
        Map<ItemType, Integer> fruitsPopped;
        fruitsPopped = evaluateBoard();

        if (fruitsPopped.isEmpty()) {
            swapItems(getItemAt(x2, y2), getItemAt(x1, y1));
        }

        printBoard();
        if (!hasAvailableMoves()) {
            System.out.println("No available moves. Resetting the board.");
            initializeBoard();
            printBoard();
        }
        return fruitsPopped;
    }


    public Map<ItemType, Integer> evaluateBoard() {
        Map<ItemType, Integer> fruitsPopped = new HashMap<>();
        do {
            Utility.addMaps(fruitsPopped, evaluteCombinations());
            printBoard();
        } while (fillGaps());
        return fruitsPopped;
    }

    private Map<ItemType, Integer> evaluteCombinations() {
        Map<ItemType, Integer> fruitsPopped = new HashMap<>();
        boolean isStillClearing;
        do {
            isStillClearing = false;
            isStillClearing = evaluateRow(fruitsPopped, true);
            isStillClearing = evaluateColumn(fruitsPopped, true) || isStillClearing;
        } while (isStillClearing);
        return fruitsPopped;
    }

    public void printBoard() {
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

    public boolean evaluateRow(Map<ItemType, Integer> fruitsPopped, boolean removeItems) {
        boolean clearedBoard = false;
        ItemType itemFoundType = null;
        List<Item> itemsToRemove = new ArrayList<>();
        for (int x = 0; x < BoardHeight + 1; x++) {
            clearedBoard = clearItemsFromBoard(fruitsPopped, removeItems, clearedBoard, itemsToRemove);
            itemFoundType = null;
            for (int y = 0; y < BoardWidth + 1; y++) {
                Item currentItem = getItemAt(x, y);
                ItemType currentItemType = currentItem != null ? currentItem.getItemType() : null;

                if (x >= BoardHeight || itemFoundType == null || !itemFoundType.equals(currentItemType)) {
                    clearedBoard = clearItemsFromBoard(fruitsPopped, removeItems, clearedBoard, itemsToRemove);
                    itemFoundType = currentItemType;
                }
                itemsToRemove.add(currentItem);
            }
        }
        return clearedBoard;
    }

    public boolean evaluateColumn(Map<ItemType, Integer> fruitsPopped, boolean removeItems) {
        boolean clearedBoard = false;
        ItemType itemFoundType = null;
        List<Item> itemsToRemove = new ArrayList<>();
        for (int y = 0; y < BoardWidth + 1; y++) {
//            int continuousItemsFound = 0;
            clearedBoard = clearItemsFromBoard(fruitsPopped, removeItems, clearedBoard, itemsToRemove);
            itemFoundType = null;
            for (int x = 0; x < BoardHeight + 1; x++) {
                Item currentItem = getItemAt(x, y);
                ItemType currentItemType = currentItem != null ? currentItem.getItemType() : null;

                if (x >= BoardWidth || itemFoundType == null || !itemFoundType.equals(currentItemType)) {
//                    if (continuousItemsFound == 4) {
//                        for (int i = 0; i < BoardHeight + 1; i ++) {
//                            Item tmpCurrentItem = getItemAt(i, y);
//                            itemsToRemove.add(tmpCurrentItem);
//                        }
//                        itemsToRemove = itemsToRemove.stream()
//                                .distinct()
//                                .collect(Collectors.toList());
//                    }
                    clearedBoard = clearItemsFromBoard(fruitsPopped, removeItems, clearedBoard, itemsToRemove);
                    itemFoundType = currentItemType;
//                    continuousItemsFound = 0;
                }
                itemsToRemove.add(currentItem);
//                continuousItemsFound++;
            }
        }
        return clearedBoard;
    }

    private boolean clearItemsFromBoard(Map<ItemType, Integer> fruitsPopped, boolean removeItems, boolean clearedBoard, List<Item> itemsToRemove) {
        clearedBoard = clearItems(itemsToRemove, fruitsPopped, removeItems) || clearedBoard;
        itemsToRemove.clear();
        return clearedBoard;
    }

    private boolean fillGaps() {
        boolean itemFall;
        do {
            itemFall = false;
            for (int y = 0; y < BoardWidth; y++) {
                for (int i = 0; i < BoardHeight - 1; i++) {
                    int j = i + 1;
                    if (board[i][y].getItemType() != ItemType.Empty && board[j][y].getItemType() == ItemType.Empty) {
                        swapItems(i, y, j, y);
                        itemFall = true;
                    }
                }
            }
        } while (itemFall);
        return fillEmptyCells();
    }

    private boolean clearItems(List<Item> itemsToRemove, Map<ItemType, Integer> fruitsPopped, boolean removeItems) {
        boolean hasItemsToRemove = itemsToRemove != null && itemsToRemove.size() > 2 && itemsToRemove.get(0).getItemType() != ItemType.Empty;
        if (hasItemsToRemove && removeItems) {
            for (Item item : itemsToRemove) {
                int x = item.getXPosition();
                int y = item.getYPosition();
                setItemAt(x, y, new Empty(new int[]{x, y}));
                if (fruitsPopped != null && fruitsPopped.containsKey(item.getItemType())) {
                    fruitsPopped.replace(item.getItemType(), fruitsPopped.get(item.getItemType()) + 1);
                } else if (fruitsPopped != null) {
                    fruitsPopped.put(item.getItemType(), 1);
                }
            }
            System.out.print("Remove: [");
            itemsToRemove.forEach(i -> System.out.print(i.getItemType().toString().charAt(0) + "(" + i.getXPosition() + ", " + i.getYPosition() + ") "));
            System.out.println(itemsToRemove);
        }
        return hasItemsToRemove;
    }

    public boolean fillEmptyCells() {
        boolean emptyCellFound = false;
        System.out.println("fillEmptyCells");
        printBoard();
        for (int i = 0; i < BoardHeight; i++) {
            for (int j = 0; j < BoardWidth; j++) {
                if (board[i][j].getItemType() == ItemType.Empty) {
                    board[i][j] = addFruit[Utility.getRandom(1, Controller.getNumberOfFruits())].addFruit(i, j); // settings[0])].addFruit(i, j);
                    emptyCellFound = true;
                }
            }
        }
        printBoard();
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
                    board[x][y] = addFruit[Utility.getRandom(1, Controller.getNumberOfFruits())].addFruit(x, y);
                }
            }
        } while (!hasAvailableMoves());
    }


    public boolean hasAvailableMoves() {
        // Check for horizontal swaps
        for (int x = 0; x < BoardHeight; x++) {
            for (int y = 0; y < BoardWidth - 1; y++) {
                swapItems(x, y, x, y + 1); // Swap horizontally
                boolean matchFound = evaluateRow(null, false) || evaluateColumn(null, false);
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
                boolean matchFound = evaluateRow(null, false) || evaluateColumn(null, false);
                swapItems(x, y, x + 1, y); // Swap back to original position

                if (matchFound) {
                    return true;
                }
            }
        }

        return false;
    }

}
