package view;

import model.ItemType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CellColor {
    private static final Map<ItemType, Color> COLORS = Map.of(
            ItemType.Apple, Color.RED,
            ItemType.Blueberry, Color.BLUE,
            ItemType.Grapes, Color.MAGENTA,
            ItemType.Lemon, Color.YELLOW,
            ItemType.Orange, Color.ORANGE,
            ItemType.Pear, Color.GREEN
    );

    public static Color getColor(ItemType itemType) {
        return COLORS.get(itemType);
    }
}
