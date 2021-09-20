package geometry;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx stands for velocity's dx.
     * @param dy stand for velocity's dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * accessor.
     * @return dx value.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * accessor.
     * @return dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * applyToPoint method -- Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     * @param p stands for change point.
     * @return point after change of velocity.
     */
    public Point applyToPoint(Point p)  {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * fromAngleAndSpeed method -- static method that creates new velocity by angle and speed.
     * with position (x+dx, y+dy).
     * @param angle stands for angle of velocity.
     * @param speed stands for change in units.
     * @return new velocity after change.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = Math.cos(angle) * -speed;
        return new Velocity(dx, dy);
    }
}