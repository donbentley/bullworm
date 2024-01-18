package cs1302.omega;

import javafx.scene.shape.Rectangle;
import cs1302.omega.OmegaApp;
import cs1302.omega.Field;

/**
 * Class for a blocks that make up the snake.
 *
 */
public class Block extends Rectangle {

    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    int xPos;
    int yPos;
    int lastXPos;
    int lastYPos;

    Block previous;
    int direction = LEFT;
    int maxWidth;
    int maxHeight;

    /**
     * Block class constructor that initializes block set.
     *
     * @param x
     * @param y
     * @param p
     * @param f
     */
    public Block(int x, int y, Block p, Field f) {
        super(OmegaApp.block_size, OmegaApp.block_size);
        xPos = x;
        yPos = y;

        setTranslateX(xPos * OmegaApp.block_size);
        setTranslateY(yPos * OmegaApp.block_size);
        previous = p;
        maxWidth = f.getW();
        maxHeight = f.getH();
    } // Block constructor



    /**
     * Updates blocks previous position and checks direction.
     *
     */
    public void update() {
        lastXPos = xPos;
        lastYPos = yPos;
        if (previous == null) {
            switch (direction) {
            case UP:  moveUp();
                break;
            case RIGHT: moveRight();
                break;
            case DOWN: moveDown();
                break;
            case LEFT: moveLeft();
                break;
            } // switch
        } else {
            xPos = previous.lastXPos;
            yPos = previous.lastYPos;
        } // else
        updatePos();
    } // update

    /**
     * Method that handles upward motion.
     *
     */
    public void moveUp() {
        yPos--;
        if (yPos < 0) {
            yPos = maxHeight - 1;
        }
    } // moveUp

    /** Method that handles downward motion.
      *
      */
    public void moveDown() {
        yPos++;
        if (yPos >= maxHeight) {
            yPos = 0;
        } // if

    } //moveDown

    /**
      * Method that handles rightward motion.
      *
      */
    public void moveRight() {
        xPos++;
        if (xPos >=  maxWidth) {
            xPos = 0;
        } // if

    } //moveRight

    /**
      * Method that handles leftward motion.
      *
      */
    public void moveLeft() {
        xPos--;
        if (xPos < 0) {
            xPos = maxWidth - 1;
        } // if
    } //moveLeft

    /**
      * Method that updates x and y position.
      *
      */
    public void updatePos() {
        setTranslateX(xPos * OmegaApp.block_size);
        setTranslateY(yPos * OmegaApp.block_size);
    } // updatePos

} // Block
