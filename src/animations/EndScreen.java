package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used to display the score and an message to the user at the end of the game.
 */
public class EndScreen implements Animation {
    //fields
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    private boolean isWin;

    /**
     * Constructor.
     * @param k stands for the keyboard sensor parameter.
     * @param score stands for the score parameter.
     * @param isWin stands for the boolean parameter (win - true, else - false).
     */
    public EndScreen(KeyboardSensor k, int score, boolean isWin) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.isWin = isWin;
    }
    @Override
    public void doOneFrame(DrawSurface d, double sec) {
        if (isWin) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
            //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
            //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}