package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    //fields
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     */
    public AnimationRunner() {
        this.framesPerSecond = 60;
        this.gui = new GUI("Arkanoid", 800, 600);
    }

    /**
     * this method is in charge of the run loop of each level.
     * @param animation stands for the animation object we want to run (logic).
     */
    public void run(Animation animation) {
        Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, 0); //game logic

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * this method is in charge of closing the gui in case the game is over.
     */
    public void closeGui() {
        this.gui.close();
    }

    /**
     * Getter method - get the keyboard sensor.
     * @return keyboard sensor.
     */
    public biuoop.KeyboardSensor getGuiKeyboard() {
        return gui.getKeyboardSensor();
    }
}