package listeners;
import gamegeneral.Counter;
import gamegeneral.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class creates a listener that will delete the ball in case he "dies".
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**
     * constructor.
     * @param gameLevel stands for the game we play in.
     * @param removedBalls stands for the gameGeneral.Counter of the remaining blocks.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
