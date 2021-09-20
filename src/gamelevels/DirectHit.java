package gamelevels;

import gameobjects.Background;
import gamegeneral.LevelInformation;
import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for describe "direct hit" level information.
 */
public class DirectHit implements LevelInformation {
    private Sprite background;

    /** Constructor - create the background for the level. */
    public DirectHit() {
        this.background = new Background(Color.BLACK);
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        velocity.add(Velocity.fromAngleAndSpeed(70, 5));
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Rectangle rec = new Rectangle(new Point(375, 150), 50, 50);
        blockList.add(new Block(rec, Color.red));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
