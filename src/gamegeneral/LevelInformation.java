package gamegeneral;

import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Velocity;
import java.util.List;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * in this method we can decide how many balls we want to have in the game.
     * @return the balls number.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * in this method we can decide the paddle's speed.
     * @return paddle's speed.
     */
    int paddleSpeed();
    /**
     * in this method we can decide the paddle's width.
     * @return paddle's width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return string that contains level's name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite background, in our case its just color Sprite.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return list of balls
     */
    int numberOfBlocksToRemove();
}