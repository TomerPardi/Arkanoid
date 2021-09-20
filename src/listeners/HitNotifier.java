package listeners;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this interface will indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * addHitListener adds hl as a listener to hit events.
     * The hitter parameter is the gameObjects.Ball that's doing the hitting.
     * @param hl stands for hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * addHitListener removes hl from the list of listeners to hit events.
     * The hitter parameter is the gameObjects.Ball that's doing the hitting.
     * @param hl stands for hit listener.
     */
    void removeHitListener(HitListener hl);
}