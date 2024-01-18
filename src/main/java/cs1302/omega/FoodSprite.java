package cs1302.omega;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import cs1302.omega.OmegaApp;
//import javafx.

/**
 * Public class for FoodSprite which is a Rectangle.
 * Provides food for snake.
 *
 */
public class FoodSprite extends Rectangle {
    int xPos;
    int yPos;

    /**
     * FoodSprite constructor that takes in a xPos and yPos which are likely random.
     *
     * @param x
     * @param y
     */
    public FoodSprite(int x, int y) {
        super(OmegaApp.block_size, OmegaApp.block_size);
        xPos = x;
        yPos = y;

        setTranslateX(xPos * OmegaApp.block_size);
        setTranslateY(yPos * OmegaApp.block_size);

        setFill(Color.TOMATO);
        setStroke(Color.MAROON);
    } // FoodSprite constructor

    /**
     * returns/gets x position.
     *
     * @return int
     */
    public int getXPos() {
        return xPos;
    } // xPos

    /**
     * returns/gets y position.
     *
     * @return int
     */
    public int getYPos() {
        return yPos;
    } // yPos

} // FoodSprite
