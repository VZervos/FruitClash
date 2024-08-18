package model.fruits;

import model.Item;
import model.ItemType;

public abstract class Fruit extends Item {

    Fruit(int[] position, ItemType itemType) {
        super(position, false, itemType);
    }
}
