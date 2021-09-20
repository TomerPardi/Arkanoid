package gameobjects;

import gamegeneral.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for creating blocks by rectangle and color.
 * It's implementing collidible and sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private Rectangle rectangle;
    private Color color;
    private java.util.List<HitListener> hitListeners;

    /**
     * constructor.
     * @param rec stands for block's structure.
     * @param color stands for block's color.
     */
    public Block(Rectangle rec, Color color) {
        this.rectangle = rec;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * constructor.
     * @param rec stands for block's structure.
     */
    public Block(Rectangle rec) {
        this.rectangle = rec;
        this.color = Color.BLACK; //default color
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = null;
        if (isCornerHit(collisionPoint)) {
            newVelocity = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (rectangle.getSideByPoint(collisionPoint) == rectangle.getLeftSide()) {
            newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (rectangle.getSideByPoint(collisionPoint) == rectangle.getRightSide()) {
            newVelocity =  new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (rectangle.getSideByPoint(collisionPoint) == rectangle.getUpperSide()) {
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (rectangle.getSideByPoint(collisionPoint) == rectangle.getLowerSide()) {
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * isCornerHit method -- check whether collision point is one of rectangle's corner.
     * @param collisionPoint stands for collision point of ball with block.
     * @return true if the collision point is one of rectangle's corner, false otherwise.
     */
    private boolean isCornerHit(Point collisionPoint) {
        return (collisionPoint.equals(rectangle.getUpperLeft()) || collisionPoint.equals(rectangle.getUpperLeft())
                || collisionPoint.equals(rectangle.getUpperLeft()) || collisionPoint.equals(rectangle.getUpperLeft()));
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                rectangle.getWidth(), rectangle.getHeight());
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removeFromGame method -- removes the block from the game environment and from sprite collection.
     * @param gameLevel stands for collision point of ball with block.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * removeFromGame method -- removes the block from the game environment and from sprite collection.
     * @param hitter stands for the ball that hits the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. this method is only for "paddle" like  objects.
     * @param collisionPoint is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @return the new velocity after the hit.
     */
    public Velocity semiHit(Point collisionPoint, Velocity currentVelocity) {
        if (isCornerHit(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (rectangle.getSideByPoint(collisionPoint) == rectangle.getLeftSide()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (rectangle.getSideByPoint(collisionPoint) == rectangle.getRightSide()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (rectangle.getSideByPoint(collisionPoint) == rectangle.getUpperSide()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (rectangle.getSideByPoint(collisionPoint) == rectangle.getLowerSide()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return null;
    }
}
