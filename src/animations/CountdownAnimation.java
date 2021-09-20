package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamegeneral.SpriteCollection;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds; // The number of seconds of the numbers to appear on screen
    private int counter; // The counter that counts backwards
    private int countFrom; // The number to from backwards
    private SpriteCollection screen; // The list of the sprites of the game
    private boolean running; // The member that says whether the game runs or not

    /**
     * Constructor.
     * @param numOfSeconds stands for the number of seconds of the numbers to appear on screen.
     * @param countFrom stands for the number to from backwards.
     * @param gameScreen stands for the list of the sprites of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        counter = countFrom;
        this.countFrom = countFrom;
        screen = gameScreen;
        running = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double sec) {
        //this.screen.drawAllOn(d);
        //this.screen.notifyAllTimePassed();
        String s;
        Sleeper sleeper = new Sleeper();
        screen.drawAllOn(d);
        if (counter == 0) {
            s = "GO";
        } else {
            s = Integer.toString(counter);
        }
        if (counter == -1) {
            running = true;
        } else {
            d.drawText(d.getWidth() / 2, 300, s, 50);
            --counter;

        }
        if (countFrom != counter + 1) {
            sleeper.sleepFor((long) ((1000) * this.numOfSeconds) / (countFrom + 1));
        }
    }

    @Override
    public boolean shouldStop() {
        return running;
    }
}