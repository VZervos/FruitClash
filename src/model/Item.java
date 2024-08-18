package model;

public abstract class Item {
    int[] position = new int[2];;
    boolean isPowerup;
    ItemType itemType;

    public int getXPosition() {
        return position[0];
    }

    public int getYPosition() {
        return position[1];
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        System.arraycopy(position, 0, this.position, 0, 2);
    }

    public void printPosition() {
        System.out.println(itemType + "(" + position[0] + ", " + position[1] + ")");
    }

    protected Item(int[] position, boolean isPowerup, ItemType itemType) {
        System.arraycopy(position, 0, this.position, 0, 2);
        this.isPowerup=isPowerup;
        this.itemType=itemType;
    }

}
