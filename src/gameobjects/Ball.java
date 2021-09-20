package gameobjects;

import biuoop.DrawSurface;
import gamegeneral.CollisionInfo;
import gamegeneral.GameLevel;
import gamegeneral.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import java.awt.Color;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for creating balls. Balls have size (radius), color,
 * and location (a geometry.Point). Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    private Point center;
    private double x;
    private double y;
    private double r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * constructor.
     * @param x stands for x axe.
     * @param y stands for y axe.
     * @param r stands for radius size.
     * @param color stands for color of ball.
     * @param env stands for environment.
     */
    public Ball(double x, double y, double r, java.awt.Color color, GameEnvironment env) {
        center = new Point(x, y);
        this.x = x;
        this.y = y;
        this.color = color;
        this.r = r;
        this.environment = env;
    }
    /**
     * constructor.
     * @param center stands for center point.
     * @param r stand for radius size.
     * @param color stands for color of ball.
     * @param env stands for environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment env) {
        this.center = center;
        this.x = center.getX();
        this.y = center.getY();
        this.color = color;
        this.r = r;
        this.environment = env;
    }

    /**
     * accessor.
     * @return center point.
     */
    public Point getCenter() {
        return center;
    }
    /**
     * accessor.
     * @return x value.
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * accessor.
     * @return y value.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * accessor.
     * @return r value.
     */
    public int getSize() {
        return (int) this.r;
    }
    /**
     * accessor.
     * @return color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * accessor.
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * accessor.
     * @return environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * drawOn method -- draw the ball on the given DrawSurface.
     * @param surface stands for drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), (int) r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * removeFromGame method -- remove ball from sprite collection.
     * @param gameLevel stands for the game we will play in.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * setVelocity method -- set class velocity by given velocity.
     * @param v stands for velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * setVelocity method -- set class velocity by given points dx and dy.
     * @param dx stands for velocity's dx.
     * @param dy stands for velocity's dy.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * moveOneStep method -- change ball's center using it's velocity.
     */
    public void moveOneStep() {
        double epsilon = Math.pow(10, -4);
        if (getVelocity() == null) {
            System.out.println("error");
            return;
        }
        Line trajectory = new Line(this.center, getVelocity().applyToPoint(this.center));
        CollisionInfo colInfo =  environment.getClosestCollision(trajectory);

        if (colInfo.collisionPoint() == null) { //no collision
            this.center = this.getVelocity().applyToPoint(this.center);
        } else { //modify ball's movement by collision point
            Point collision = colInfo.collisionPoint();
            Collidable rec = colInfo.collisionObject();
            moveBallCenter(collision, rec, epsilon);
            this.velocity = rec.hit(this, collision, velocity);
        }
    }



    /**
     * moveBallCenter method -- change ball's center close to the collision point.
     * @param collision stands for the actual collision point.
     * @param rec stands for the collision block that is involved.
     * @param epsilon stands for small enough value.
     */
    private void moveBallCenter(Point collision, Collidable rec, double epsilon) {
        if (rec.getCollisionRectangle().getSideByPoint(collision).equals(rec.getCollisionRectangle().
                getUpperSide())) {
            this.center = new Point(collision.getX(), collision.getY() - epsilon);
        } else if (rec.getCollisionRectangle().getSideByPoint(collision).equals(rec.getCollisionRectangle().
                getRightSide())) {
            this.center = new Point(collision.getX() + epsilon, collision.getY());
        } else if (rec.getCollisionRectangle().getSideByPoint(collision).equals(rec.getCollisionRectangle().
                getLeftSide())) {
            this.center = new Point(collision.getX() - epsilon, collision.getY());
        } else if (rec.getCollisionRectangle().getSideByPoint(collision).equals(rec.getCollisionRectangle().
                getLowerSide())) {
            this.center = new Point(collision.getX(), collision.getY() + epsilon);
        }
    }
}