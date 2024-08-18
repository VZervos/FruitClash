package view;

import controller.Controller;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ItemButton extends JButton {
    /**
     * The item represented
     */
    private Item Item;
    /**
     * Button contained
     */
    private JButton button;

    /**
     * Sets item represented to item
     *
     * @param item item to be set
     * @post item represented is set to item
     */
    public void setitem(Item item) {
        this.Item = item;
    }

    /**
     * Gets the item represented
     *
     * @return item represented
     * @post The item represented is returned
     */
    public Item getitem() {
        return this.Item;
    }

    /**
     * Sets the button
     *
     * @param button Button
     * @post button is set to button
     */
    public void setButton(JButton button) {
        this.button = button;
    }

    /**
     * Gets the button
     *
     * @return itembutton's button
     * @post Button contained is returned
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Creates a new itemButton of item with tag ij
     *
     * @param item item to be represented
     * @param i     Y position
     * @param j     X position
     */
    public ItemButton(Item item, int i, int j) {
        this.Item = item;
//        this.button = setIcon();
        this.button = new JButton();
        this.button.setName(Integer.toString(i) + j);
        this.button.setText(item.getItemType().toString().substring(0, 1));
//        this.button.setBorder(null);
//        this.button.setFocusPainted(false);
//        this.button.setContentAreaFilled(false);
//        this.button.setBorderPainted(false);
    }

    /**
     * Sets the icon of the button
     *
     * @return Button with icon is returned
     * @post Button's icon is updated based on its properties // Activates "X" button
     */
    private JButton setIcon() {
        return button;
    }
}
