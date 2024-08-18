package model.fruits;

import model.ItemType;

public class Empty extends Fruit {

    public Empty(int[] position) {
        super(position, ItemType.Empty);
    }

}
