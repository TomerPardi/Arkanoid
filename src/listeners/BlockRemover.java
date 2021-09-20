package listeners;
import gamegeneral.Counter;
import gamegeneral.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    /**
     * Constructor.
     * @param gameLevel stands for the game we play in.
     * @param removedBlocks stands for the remaining block in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(1);
    }
}