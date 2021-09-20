package gameobjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamegeneral.GameLevel;
import gamegeneral.SpriteCollection;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class defines the player in the game. It is a rectangle
 * that is controlled by the arrow keys, and moves according to the
 * player key presses. It should implement the gameObjects.Sprite and the gameObjects.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    //fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int speed;
    private ArrayList<Line> areas; // 5 different areas of the paddle.
    private int leftBorder;
    private int rightBorder;
    private SpriteCollection sprites;

    /**
     * constructor.
     * @param rec stands for the rectangle shape of the paddle.
     * @param color stands for paddle's color.
     * @param keyboard lets us move the paddle.
     * @param speed  stands for the pace of the paddle movement.
     * @param leftBorder stands for the left frame.
     * @param rightBorder stands for the right frame.
     * @param sprites stands for sprite collection in game.
     */
    public Paddle(Rectangle rec, Color color, biuoop.KeyboardSensor keyboard, int speed,
                  int leftBorder, int rightBorder, SpriteCollection sprites) {
        this.rectangle = rec;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.sprites = sprites; //we need this to prevent ball step into paddle
        setAreasList();
    }

    /**
     * setAreasList method -- initialize the areas list. this list contains
     * 5 equally-spaced regions (as lines) of the paddle.
     */
    public void setAreasList() {
        this.areas = new ArrayList<Line>();
        int areaWidth = rectangle.getWidth() / 5;
        double start = rectangle.getUpperLeft().getX();
        double end = rectangle.getUpperLeft().getX() + areaWidth;
        double recYAxe = rectangle.getUpperLeft().getY();
        for (int i = 0; i < 5; ++i) {
            Point startP = new Point(start, recYAxe);
            Point endP = new Point(end, recYAxe);
            areas.add(new Line(startP, endP));
            start += areaWidth;
            end += areaWidth;
        }
    }

    /**
     * moveLeft method -- is in charge of moving the paddle left. The method checks
     * whether the next x coordinate is out left edge or not and moves it as it
     * should.
     */
    public void moveLeft() {
        this.rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() - speed,
                rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        if (rectangle.getUpperLeft().getX() < leftBorder) {
            this.rectangle = new Rectangle(new Point(leftBorder, rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), rectangle.getHeight());
        }
        // now prevent ball from step into paddle
//        for (Sprite sprite : sprites.getSpriteList()) {
//            if (sprite.getClass().getName().equals("gameobjects.Ball")) {
//                Ball ball = (Ball) sprites.getSpriteList().get(0);
//                if (isBallInPaddle(ball)) {
//                    this.rectangle = new Rectangle(new Point(ball.getX() + speed, rectangle.getUpperLeft().getY()),
//                            rectangle.getWidth(), rectangle.getHeight());
//                    if (Math.signum(ball.getVelocity().getDx()) == 1.0) {
//                        ball.setVelocity(-ball.getVelocity().getDx(), ball.getVelocity().getDy());
//                    }
//                    ball.moveOneStep();
//                }
//            }
//        }
        if (sprites.getSpriteList().get(0).getClass().getName().equals("gameobjects.Ball")) {
            Ball ball1 = (Ball) sprites.getSpriteList().get(0);
            if (isBallInPaddle(ball1)) {
                this.rectangle = new Rectangle(new Point(ball1.getX() + speed, rectangle.getUpperLeft().getY()),
                        rectangle.getWidth(), rectangle.getHeight());
                if (Math.signum(ball1.getVelocity().getDx()) == 1.0) {
                    ball1.setVelocity(-ball1.getVelocity().getDx(), ball1.getVelocity().getDy());
                }
                ball1.moveOneStep();
            }
        }
        if (sprites.getSpriteList().get(1).getClass().getName().equals("gameobjects.Ball")) {
            Ball ball2 = (Ball) sprites.getSpriteList().get(1);
            if (isBallInPaddle(ball2)) {
                this.rectangle = new Rectangle(new Point(ball2.getX() + speed, rectangle.getUpperLeft().getY()),
                        rectangle.getWidth(), rectangle.getHeight());
                if (Math.signum(ball2.getVelocity().getDx()) == 1.0) {
                    ball2.setVelocity(-ball2.getVelocity().getDx(), ball2.getVelocity().getDy());
                }
                ball2.moveOneStep();
            }
        }
        setAreasList();
    }

    /**
     * moveRight method -- is in charge of moving the paddle right. The method checks
     * whether the next x coordinate is out right edge or not and moves it as it
     * should.
     */
    public void moveRight() {
        this.rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() + speed,
                rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        if (rectangle.getUpperLeft().getX() + rectangle.getWidth() > rightBorder) {
            this.rectangle = new Rectangle(new Point(rightBorder - rectangle.getWidth(),
                    rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        }
        if (sprites.getSpriteList().get(0).getClass().getName().equals("gameobjects.Ball")) {
            Ball ball1 = (Ball) sprites.getSpriteList().get(0);
            if (isBallInPaddle(ball1)) {
                this.rectangle = new Rectangle(new Point(ball1.getX() - speed - rectangle.getWidth(),
                        rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
                if (Math.signum(ball1.getVelocity().getDx()) == -1.0) {
                    ball1.setVelocity(-ball1.getVelocity().getDx(), ball1.getVelocity().getDy());
                }
                ball1.moveOneStep();
            }
        }
        if (sprites.getSpriteList().get(1).getClass().getName().equals("gameobjects.Ball")) {
            Ball ball2 = (Ball) sprites.getSpriteList().get(1);
            if (isBallInPaddle(ball2)) {
                this.rectangle = new Rectangle(new Point(ball2.getX() - speed  - rectangle.getWidth(),
                        rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
                if (Math.signum(ball2.getVelocity().getDx()) == -1.0) {
                    ball2.setVelocity(-ball2.getVelocity().getDx(), ball2.getVelocity().getDy());
                }
                ball2.moveOneStep();
            }
        }
        setAreasList();
        }

    /**
     * isBallInPaddle method -- is in charge of checking if a ball stepping into our object.
     * @param b stands for ball to check if is in paddle.
     * @return true if ball is in the paddle, false otherwise.
     */
    private boolean isBallInPaddle(Ball b) {
        Rectangle rec = rectangle;
        double leftX = rec.getUpperLeft().getX();
        double rightX = leftX + rec.getWidth();
        double lowerY = rec.getUpperLeft().getY();
        double upperY = lowerY + rec.getHeight();
        return (b.getX() <= rightX && b.getX() >= leftX && b.getY() <= upperY && b.getY() >= lowerY);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int xPoint = (int) rectangle.getUpperLeft().getX();
        int yPoint = (int) rectangle.getUpperLeft().getY();
        d.drawRectangle(xPoint, yPoint, rectangle.getWidth(), rectangle.getHeight());
        d.setColor(color);
        d.fillRectangle(xPoint, yPoint, rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (rectangle.getUpperLeft().getY() < collisionPoint.getY()) {
            Block newBlock = new Block(rectangle);
            return newBlock.semiHit(collisionPoint, currentVelocity);
        }
        double angle = findHitAngle(collisionPoint);
        if (angle == 1) {
            Block newBlock = new Block(rectangle);
            return newBlock.semiHit(collisionPoint, currentVelocity);
        } else {
            return Velocity.fromAngleAndSpeed(angle, Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                    + Math.pow(currentVelocity.getDy(), 2)));
        }
    }

    /**
     * findHitAngle method --  checks in what region was the collision and returns the
     * angle accordingly.
     * @param collisionPoint is the collision point of ball with the paddle.
     * @return the right angle in which the ball should move.
     */
    public double findHitAngle(Point collisionPoint) {
        int i;
        for (i = 0; i < 5; i++) {
            if (collisionPoint.getX() >= areas.get(i).start().getX()
                    && collisionPoint.getX() <= areas.get(i).end().getX()) {
                break;
            }
        }
        int angle;
        switch (i) {
            case 0:
                angle = 300;
                break;
            case 1:
                angle = 330;
                break;
            case 2:
                angle = 1;
                break;
            case 3:
                angle = 30;
                break;
            case 4:
                angle = 60;
                break;
            default:
                angle = 0;
        }
        return angle;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}