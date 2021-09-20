package gameobjects;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * getCollisionRectangle returns the rectangle structure of the collision.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     * @param collisionPoint is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @param ball stands for  the hitter ball.
     * @return the new velocity after the hit.
     */
    Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity);
}