package listeners;
import gamegeneral.Counter;
import gameobjects.Ball;
import gameobjects.Block;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for creating Listeners.HitListener that will update it's counter
 * when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor.
     * @param scoreCounter stands for the gameGeneral.Counter we want to edit.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}