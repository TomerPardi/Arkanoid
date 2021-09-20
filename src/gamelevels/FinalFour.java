package gamelevels;

import gameobjects.Background;
import gamegeneral.LevelInformation;
import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for describe "final four" level information.
 */
public class FinalFour implements LevelInformation {
    private Sprite background;

    /** Constructor - create the background for the level. */
    public FinalFour() {
        this.background = new Background(Color.BLUE);
    }
    @Override
    public int numberOfBalls() {
        return 3;
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
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
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
        for (int j = 0; j < 7; j++) {
            Color color = Color.getHSBColor((float) 0.17 * j, 1, 1);
            for (int i = 0; i < 15; i++) {
                geometry.Rectangle rec = new geometry.Rectangle(new Point(25 + i * blockWidth,
                        120 + j * blockHeight), blockWidth, blockHeight);
                blockList.add(new Block(rec, color));
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
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