package cs1302.omega;

import java.util.ArrayList;
import cs1302.omega.Field;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Public class for an implementation of a snake.
 */
public class Snake {
    ArrayList<Block> blocks = new ArrayList<Block>();

    Block head;
    Block tail;

    /**
     * Snake constructor that initializes to size of block based on width and height of field.
     *
     * @param j
     * @param f
     */
    public Snake(int j, Field f) {
        int x = f.getW() / 2;
        int y = f.getH() / 2;
        head = new Block(x, y, null, f );
        head.setFill(Color.DARKSALMON);
        head.setStroke(Color.MAROON);

        tail = head;

        Block previous =  head;
        blocks.add(head);
        for (int i = 1; i < j; i++) {
            Block b = new Block(x + i, y, previous, f);
            blocks.add(b);
            tail = b;
        } // for
        tail.setFill(Color.DARKSALMON);
        tail.setStroke(Color.MAROON);

    } // Snake Constructor

    /**
     * Changes direction to d.
     * Utilized in cs1302.omega.OmegaApp by KeyCode Handler.
     *
     * @param d
     */
    public void changeDir(int d) {
        head.direction = d;
    } // changeDir

    /**
     * Gets direction head is traveling.
     *
     * @return int
     */
    public int getDir() {
        return head.direction;
    } //getDir

} // Snake
