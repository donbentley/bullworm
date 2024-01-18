package cs1302.omega;

import cs1302.omega.OmegaApp;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import java.util.ArrayList;
import cs1302.omega.FoodSprite;
import cs1302.omega.Snake;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Class for "Field" which is a Pane that holds background and adds snake and food
 * to scene graph. Accounts for the lower parts of scene graph.
 *
 */
public class Field extends Pane {
    private int w;
    private int h;
    public int score = 0;
    ArrayList<Block> blocks = new ArrayList<Block>();
    Snake snake;
    FoodSprite f;
    String bgIMG =
        "file:resources/bgimg.jpg";

    /**
     * Constructs a field with height and width.
     *
     * @param width
     * @param height
     */
    public Field(int width, int height) {
        w = width;
        h = height;

        ImageView imgView = new ImageView();
        Image bgImg = new Image(bgIMG);

        setMinSize(w * OmegaApp.block_size, h * OmegaApp.block_size);
        BackgroundImage bg = new BackgroundImage(
            bgImg,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100,100,true,true,true,true));
        Background background1 = new Background(bg);
        setBackground(background1);

        setBorder(
            new Border(
                new BorderStroke(
                    Color.TAN, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));

        addFood();
    } // Field

     /**
      * Method that updates every time a block is translated, snake eats, and keeps score.
      *
      */
    public void update() {
        for (Block b: blocks) {
            b.update();
        } // for

        if (isEaten(f)) {
            score ++;
            addFood();
            grow();
        } // if
    } // update

    /**
      * Adds a new snake to the pane.
      *
      * @param snake
      */
    public void addSnake(Snake snake) {
        this.snake = snake;
        for (Block b: snake.blocks) {
            addBlock(b);
        } //for
    } //addSnake

    /**
      * Checks to see if snake has died.
      * Death condition is if the snake has collided with itself.
      *
      * @return boolean
      */
    public boolean dead() {
        for (Block b: blocks) {
            if (b != snake.head) {
                if (b.xPos == snake.head.xPos && b.yPos == snake.head.yPos) {
                    return true;
                } // if
            } // if
        } // for
        return false;
    } // dead

    /**
     * Adds food to the pane at a random index.
     */
    public void addFood() {
        int randomX = (int) (Math.random() * w);
        int randomY = (int) (Math.random() * h);

        FoodSprite food = new FoodSprite(randomX,randomY);
        getChildren().add(food);
        getChildren().remove(f);
        f = food;
    } // addFood

    /**
     * Grows snake and adds block to tail.
     *
     */
    public void grow() {
        Block b = new Block(snake.tail.lastXPos, snake.tail.lastYPos, snake.tail, this);
        snake.tail = b;
        snake.tail.setFill(Color.DARKSALMON);
        snake.tail.setStroke(Color.MAROON);
        addBlock(b);
    } // grow

    /**
     * Adds block to end of snake list.
     *
     * @param b
     */
    private void addBlock(Block b) {
        getChildren().add(b);
        blocks.add(b);
    } //addBlock

    /**
     * Checks whether the food has been eaten.
     * If xPos and yPos of head are same as xPos and yPos of foodSprite.
     *
     * @param f
     * @return boolean
     */
    public boolean isEaten(FoodSprite f) {
        boolean eaten = false;
        if (f == null) {
            eaten = false;
        } else if ((f.getXPos() == snake.head.xPos) && (f.getYPos() == snake.head.yPos)) {
            eaten = true;
        } // else if

        return eaten;
    } // isEaten

    /**
     * Returns width of pane.
     *
     * @return w
     */
    public int getW() {
        return w;
    } //getW

    /**
     * Returns height of pane.
     *
     * @return h
     */
    public int getH() {
        return h;
    } //getH


} // Field
