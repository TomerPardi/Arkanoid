package gamegeneral;
import gameobjects.Collidable;
import geometry.Point;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class will store information about collision.
 */
public class CollisionInfo {
    //fields
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * constructor.
     * @param p stands for collision point.
     * @param col stands for collidable object.
     */
    public CollisionInfo(Point p, Collidable col) {
        this.collisionPoint = p;
        this.collidable = col;
    }
    /**
     * accessor.
     * @return collisionPoint with the collidable object.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * accessor.
     * @return collidable object involved in collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}