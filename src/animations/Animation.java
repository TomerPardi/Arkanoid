package animations;
import biuoop.DrawSurface;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class will store template-methods thet responsible for the logic and stop condition.
 */
public interface Animation {
    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d stands for the surface we want to draw our game in.
     * @param sec stands for the seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double sec);

    /**
     * shouldStop() is in charge of stopping condition.
     * @return true if the run loop should stop.
     */
    boolean shouldStop();
}