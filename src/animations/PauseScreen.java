package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is in charge of giving us an option to pause the game when pressing the p key.
 */
public class PauseScreen implements Animation {
    //fields
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k stands for the keyboard sensor parameter.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d, double sec) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}