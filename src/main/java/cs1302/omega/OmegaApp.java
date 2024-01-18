package cs1302.omega;

import cs1302.game.DemoGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cs1302.omega.Field;
import javafx.geometry.Insets;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    String logo = ("file:resources/burninglogo.gif");
    Image logo1 = new Image(logo);
    ImageView imgView = new ImageView(logo1);

    static int block_size = 15;

    int width = 25, height = 25;
    int il = 4;

    long time = System.nanoTime();

    boolean modified = false;
    int update;
    boolean hasNext = false;
    Field f;

    @Override
    public void init(){

    } // init

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        VBox root = new VBox(5);
        HBox text = new HBox(60);
        f = new Field(width, height);
        f.addSnake(new Snake(il, f));
        Label instructions = new Label("Use arrow keys to move! Don't eat yourself!");
        instructions.setTextFill(Color.SADDLEBROWN);
        Label score = new Label("Score: 0");
        score.setTextFill(Color.SADDLEBROWN);
        text.getChildren().addAll(score, instructions);
        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 10);
        score.setFont(font);
        instructions.setFont(font);

        AnimationTimer timer = new AnimationTimer() {
                public void handle(long now) {
                    if (now - time > 1000000000 / 6) {
                        f.update();
                        time = now;
                        score.setText("Points: " + f.score);
                        modified = false;
                        if (hasNext) {
                            changeDir(f.snake, update);
                            hasNext = false;
                        } // if
                        if (f.dead()) {
                            root.getChildren().clear();
                            f = new Field(width, height);
                            f.addSnake(new Snake(il, f));
                            score.setText("Points: 0 ");
                            root.getChildren().addAll(f, text);
                        } // if
                    } // if
                } // handle override
            };
        timer.start();
        root.getChildren().addAll(f, text);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> { // button event handler
            if (e.getCode().equals(KeyCode.UP) && f.snake.getDir() != Block.DOWN) {
                changeDir(f.snake, Block.UP);
            } else if (e.getCode().equals(KeyCode.DOWN) && f.snake.getDir() != Block.UP) {
                changeDir(f.snake, Block.DOWN);
            } else if (e.getCode().equals(KeyCode.LEFT) && f.snake.getDir() != Block.RIGHT) {
                changeDir(f.snake, Block.LEFT);
            } else if (e.getCode().equals(KeyCode.RIGHT) && f.snake.getDir() != Block.LEFT) {
                changeDir(f.snake, Block.RIGHT);
            } // else if
        }); //button event handler

        stage.setTitle("BULLWORM(SNAKE)");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    } // start

    /**
     * If direction is changed use snakes changeDir method.
     * else update with next movement
     *
     * @param s
     * @param d
     */
    public void changeDir(Snake s, int d) {
        if (!modified) {
            s.changeDir(d);
            modified = true;
        } else {
            hasNext = true;
            update = d;
        } // else

    } // changeDir

} // OmegaApp
