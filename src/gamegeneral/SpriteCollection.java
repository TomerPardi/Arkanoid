package gamegeneral;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import gameobjects.Sprite;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * gameGeneral.SpriteCollection will hold a collection of sprites.
 */
public class SpriteCollection {
    //fields
    private List<Sprite> spriteList;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * addSprite method -- adding sprite to the list.
     * @param s stands for the sprite we want to add to the list.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * notifyAllTimePassed method -- call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn method -- call drawOn(d) on all sprites.
     * @param d stands for the surface we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * accessor.
     * @return gameObjects.Collidable List.
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }
    /**
     * removeSprite method -- used to remove sprite from sprite list.
     * @param s stands for sprite.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

}