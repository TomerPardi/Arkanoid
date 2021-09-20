package gameobjects;

import biuoop.DrawSurface;
import gamegeneral.Counter;
import gamegeneral.GameLevel;
import gamegeneral.LevelInformation;

import java.awt.Color;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for displaying a score in the top of the frame.
 */
public class ScoreIndicator implements Sprite {
    private LevelInformation level;
    private Counter score;
    //private Counter lives; this field is not relevant for the current project.
    /**
     * Constructor.
     * @param level stands for level's information.
     * @param score1 stands for the score in game.
     */
    public ScoreIndicator(LevelInformation level, Counter score1) {
        this.level = level;
        score = score1;
        //lives = new Counter();
        //lives.increase(7);
    }
    /**
     * increase method -- add number to current count.
     * @param num stands for the parameter we want to add to counter.
     */
    public void increase(int num) {
        score.increase(num);
    }
    /**
     * getScore method -- get current count.
     * @return counter;
     */
    public Counter getScore() {
        return this.score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 20, "Score: " + score.getValue(), 15);
        d.drawText(600, 20, "Level Name: " + level.levelName(), 15);
        //d.drawText(100, 20, "Lives: " + lives.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
