package geometry;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * A point has an x and a y value,
 * and can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {

    // Fields
    private double x;
    private double y;

    /**
     * constructor.
     * @param x stands for x axe.
     * @param y stand for y axe.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * constructor.
     * @param other stands for other point;
     */
    public Point(Point other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * distance -- return the distance of this point to the other point.
     * @param other stands for point to compare.
     * @return distance between points.
     */
    public double distance(Point other) {
        return Math.sqrt((x - other.getX()) * (x - other.getX())
                + ((y - other.getY()) * (y - other.getY())));
    }

    /**
     * equals -- return true if the points are equal, false otherwise.
     * @param other stands for point to compare.
     * @return true if lines are same, false otherwise.
     */
    public boolean equals(Point other) {

        return ((x == other.getX() && y == other.getY()) || (x == other.getY() && y == other.getX()));
    }

    /**
     * accessor.
     * @return x value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * accessor.
     * @return y value.
     */
    public double getY() {
        return this.y;
    }
}