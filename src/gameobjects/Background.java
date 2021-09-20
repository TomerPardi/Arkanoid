package gameobjects;

import biuoop.DrawSurface;
import gamegeneral.GameLevel;
import gameobjects.Sprite;
import java.awt.Color;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * This class is in charge of creating the background of the game (Color sprite).
 */
public class Background implements Sprite {
    //fields
    private Color color;

    /**
     * Constructor.
     * @param color the color we want the background be.
     */
    public Background(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(-10, -10, 1000, 1000);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Getter method.
     * @return color field.
     */
    public Color getColor() {
        return color;
    }
}
