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
 * this class is used for describe "green 3" level information.
 */
public class Green3 implements LevelInformation {
    private Sprite background;

    /** Constructor - create the background for the level. */
    public Green3() {
        this.background = new Background(Color.getHSBColor((float) 0.34, (float) 0.2, (float) 1.5));
    }
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        int j = 300;
        // Make a velocity as the number of the balls and put on the list.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocity.add(Velocity.fromAngleAndSpeed(j % 360, 5));
            j += 12;
            if (j == 360) {
                j += 12;
            }
        }
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int blockWidth = 50;
        int blockHeight = 30;
        for (int i = 0; i < 6; i++) {
            Color color = Color.getHSBColor((float) 0.17 * i, 1, 1);
            for (int j = i; j < 12; j++) {
                Block block = new Block(new Rectangle(new Point(775 - 12 * 40 + 40 * j,
                        100 + 20 * i), 40, 20), color);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
//    @Override
//    public List<Ball> balls() {
//        List<Ball> ballsList = new ArrayList<Ball>();
//        int ballRadius = 3;
//        Color ballColor = Color.white;
//        for (int i = 0; i < numberOfBalls(); i++) {
//            Color color = Color.getHSBColor((float) 0.17 * i, 1, 1);
//            geometry.Rectangle rec = new geometry.Rectangle(new Point(30 + i * blockWidth, 350),
//                    blockWidth, blockHeight);
//            ballsList.add(new Block(rec, color));
//        }
//        return ballsList;
//    }
}