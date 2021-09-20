package gameobjects;

import biuoop.DrawSurface;
import gamegeneral.GameLevel;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * a gameObjects.Sprite is a game object that can be drawn to the screen
 * (and which is not just a background image).
 */
public interface Sprite {
    /**
     * drawOn draws the sprite to the screen.
     * @param d stands for the drawing surface;
     */
    void drawOn(DrawSurface d);
    /**
     * drawOn method -- notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * addToGame method -- adding the sprite to the game.
     * @param g stands for the game in which we want to add the sprite.
     */
    void addToGame(GameLevel g);
}