package listeners;
// ID: 316163922

import gameobjects.Ball;
import gameobjects.Block;

/**
 * @author Tomer Pardilov
 * this interface will be used by Objects that want to be notified of hit events.
 */
public interface HitListener {
    /**
     * hitEvent is called whenever the beingHit object is hit.
     * The hitter parameter is the gameObjects.Ball that's doing the hitting.
     * @param beingHit stands for the blocks that we want to remove.
     * @param hitter stands for the ball that hits the blocks.
     */
    void hitEvent(Block beingHit, Ball hitter);
}