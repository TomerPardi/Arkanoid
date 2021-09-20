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
 * this class is used for describe "wide easy" level information.
 */
public class WideEasy implements LevelInformation {
    private Sprite background;

    /** Constructor - create the background for the level. */
    public WideEasy() {
        this.background = new Background(Color.WHITE);
    }
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        int j = 300;
        // Make a velocity as the number of the balls and put on the list.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocity.add(Velocity.fromAngleAndSpeed(j % 360, 7));
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
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
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
        Color color;
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            color = Color.BLACK;
            switch (i) {
                case 0: case 1:
                    color = Color.red;
                    break;
                case 2: case 3:
                    color = Color.orange;
                    break;
                case 4: case 5:
                    color = Color.yellow;
                    break;
                case 6: case 7: case 8:
                    color = Color.green;
                    break;
                case 9: case 10:
                    color = Color.blue;
                    break;
                case 11: case 12:
                    color = Color.pink;
                    break;
                case 13: case 14:
                    color = Color.cyan;
                    break;
                default:
                    break;
            }
            geometry.Rectangle rec = new geometry.Rectangle(new Point(25 + i * blockWidth, 250),
                    blockWidth, blockHeight);
            blockList.add(new Block(rec, color));
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
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
