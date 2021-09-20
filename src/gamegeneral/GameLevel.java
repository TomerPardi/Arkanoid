package gamegeneral;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Paddle;
import gameobjects.Collidable;
import gameobjects.ScoreIndicator;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import java.awt.Color;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class will hold the sprites and the collidables, and will be in charge of the animation.
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Sleeper sleeper;
    //private KeyboardSensor keyboard;
    private final int guiWidth = 800;
    private final int guiHeight = 600;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private ScoreIndicator indicator;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;

    /**
     * Constructor.
     * @param level stands for the level information.
     * @param ar stands for the AnimationRunner.
     * @param score stands for the score in the game.
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, Counter score) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.indicator = new ScoreIndicator(level, score);
        this.running = true;
        this.runner = ar;
        this.level = level;
    }

    /**
     * addCollidable method -- add collidible to environment.
     * @param c stands for point on desirable line.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite method -- add sprite to sprites list.
     * @param s stands for point on desirable line.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * initialize method -- Initialize a new game: create the Blocks and gameObjects.Ball (and gameObjects.Paddle)
     *                      and add them to the game.
     */
    public void initialize() {
        //frame size
        int horizontalHeight = 25;
        int verticalWidth = 25;
        int horizontalWidth = guiWidth - 2 * verticalWidth;
        int verticalHeight = guiHeight;

        BlockRemover listener = new BlockRemover(this, blocksCounter);
        BallRemover bListener = new BallRemover(this, ballsCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);

        //background
        level.getBackground().addToGame(this);

        //create and add balls, random numbers that fit the size of frame.
        //we add the balls first, we want them to be in the first indexes in spritelist.
        createBalls(environment);

        //create and add paddle
        createPaddle(verticalHeight, horizontalHeight, verticalWidth, horizontalWidth);

        //add frame blocks
        //upper - right - left - lower frames
        createBlock(verticalWidth, 0, horizontalWidth, horizontalHeight, Color.GRAY);
        createBlock(horizontalWidth + verticalWidth, 0, verticalWidth, verticalHeight,
                Color.GRAY);
        createBlock(0, 0, verticalWidth, verticalHeight, Color.GRAY);
        //createBlock(verticalWidth, verticalHeight - horizontalHeight, horizontalWidth, horizontalHeight,
        //        Color.GRAY);
        Block lowerBlock = new Block(new Rectangle(new Point(verticalWidth, verticalHeight - 1),
                horizontalWidth, horizontalHeight), Color.GRAY);
        lowerBlock.addToGame(this);
        lowerBlock.addHitListener(bListener);

        //add indicator.
        indicator.addToGame(this);

        //create and add pattern blocks
        createPatternBlocks(scoreListener, listener, verticalWidth, horizontalWidth);
    }

    /**
     * run method -- Run the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * createBlock method -- creates block by compatible parameters.
     * @param x stands for rectangle's upper left point x axe.
     * @param y stands for rectangle's upper left point y axe;
     * @param width stands for rectangle's width.
     * @param height stands for rectangle's width.
     * @param c stands for rectangle's color.
     */
    private void createBlock(int x, int y, int width, int height, Color c) {
        Block block = new Block(new Rectangle(new Point(x, y), width, height), c);
        block.addToGame(this);
    }

    /**
     * createPaddle method -- creates paddle that stays in the frame.
     * @param verticalHeight stands for the height of the left/right frame.
     * @param horizontalHeight stands for the height of the lower/upper frame;
     * @param verticalWidth stands for the width of the left/right frame.
     * @param horizontalWidth stands for the width of the lower/upper frame.
     */
    private void createPaddle(int verticalHeight, int horizontalHeight, int verticalWidth, int horizontalWidth) {
        KeyboardSensor keyboard = runner.getGuiKeyboard();
        int paddleWidth = level.paddleWidth();
        int paddleHeight = 20;
        Color paddleColor = Color.orange;
        int paddleSpeed = level.paddleSpeed();
        //paddle lays on lower frame, X axe is random between frame sizes
        Rectangle paddleRec = new Rectangle(new Point(guiWidth / 2 - level.paddleWidth() / 2,
                verticalHeight - horizontalHeight - paddleHeight), paddleWidth, paddleHeight);

        //create and add paddle to game
        Paddle paddle = new Paddle(paddleRec, paddleColor, keyboard, paddleSpeed, verticalWidth,
                horizontalWidth + verticalWidth, this.sprites);
        paddle.addToGame(this);
    }

    /**
     * createBalls method -- creates two balls that bounce inside  the frames.
     * @param env stands for the environment the ball belongs to (where the collidables at).
     */
    private void createBalls(GameEnvironment env) {
        for (Velocity vel : level.initialBallVelocities()) {
            Ball ball = new Ball(400, 350, 4, Color.WHITE, env);
            ball.setVelocity(vel);
            ball.addToGame(this);
            ballsCounter.increase(1);
        }
    }

    /**
     * createPatternBlocks method -- creates blocks inside the frames in a specific pattern.
     * @param verticalWidth stands for the width of the left/right frame.
     * @param horizontalWidth stands for the width of the lower/upper frame.
     * @param scoreListener listener for the score.
     * @param listener listener for the block remover.
     */
    private void createPatternBlocks(ScoreTrackingListener scoreListener, BlockRemover listener,
                                     int verticalWidth, int horizontalWidth) {
        for (Block block : level.blocks()) {
            block.addToGame(this);
            block.addHitListener(listener);
            block.addHitListener(scoreListener);
            this.blocksCounter.increase(1);
        }
    }

    /**
     * accessor.
     * @return gui width.
     */
    public int getGuiWidth() {
        return guiWidth;
    }

    /**
     * accessor.
     * @return gui height.
     */
    public int getGuiHeight() {
        return guiHeight;
    }
    /**
     * removeCollidable method -- used to remove object from collidable list.
     * @param c stands for collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removeSprite method -- used to remove sprite from sprite list.
     * @param s stands for sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * getEnvironment method -- get the environment field.
     * @return environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public void doOneFrame(DrawSurface d, double sec) {
        //d.setColor(Color.pink);
        //d.fillRectangle(-10, -10, 1000, 1000);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        indicator.increase(score.getValue() - indicator.getScore().getValue());

        if (this.blocksCounter.getValue() == 0) {
            score.increase(100);
            //indicator.increase(100);

            //gui.close();
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0) {
            //gui.close();
            this.running = false;
        }
        if (runner.getGuiKeyboard().isPressed("p")) {
            //this.runner.run(new PauseScreen(runner.getGuiKeyboard()));
            this.runner.run(new KeyPressStoppableAnimation(runner.getGuiKeyboard(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen(runner.getGuiKeyboard())));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * boolean method that returns if any block still exist.
     * @return true if we still have blocks in the game, false otherwise.
     */
    public boolean haveBlocks() {
        if (this.blocksCounter.getValue() != 0) {
            return true;
        }
        return false;
    }
    /**
     * boolean method that returns if any ball still exist.
     * @return true if we still have balls in the game, false otherwise.
     */
    public boolean haveBalls() {
        if (this.ballsCounter.getValue() != 0) {
            return true;
        }
        return false;
    }

    /**
     * getter method - returns the level information field.
     * @return level information.
     */
    public LevelInformation getLevel() {
        return level;
    }
}