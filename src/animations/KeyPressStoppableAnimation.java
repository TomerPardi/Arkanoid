package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor stands for the keyboard sensor parameter.
     * @param key stands for the key that was pressed.
     * @param animation stands for the animation we want to wrap.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double sec) {
        animation.doOneFrame(d, 0);
        if (!isAlreadyPressed) {
            if (this.sensor.isPressed(this.key)) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed  = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
