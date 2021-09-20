package geometry;
import java.util.ArrayList;
import java.util.List;

// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for creating rectangles by height, width and upper left point.
 */

public class Rectangle {
    //fields
    private int width;
    private int height;
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line upperSide;
    private Line lowerSide;
    private Line leftSide;
    private Line rightSide;

    /**
     * constructor.
     * @param upperLeft stands for upper left point.
     * @param width stands for rectangle's width.
     * @param height stands for rectangle's height.
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.height = height;
        this.width = width;
        this.upperLeft = new Point(upperLeft);
        //calculate other fields by current information
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperSide = new Line(this.upperLeft, this.upperRight);
        this.lowerSide = new Line(this.lowerLeft, this.lowerRight);
        this.leftSide = new Line(this.upperLeft, this.lowerLeft);
        this.rightSide = new Line(this.upperRight, this.lowerRight);
    }


    /**
     * intersectionPoints method -- Return a (possibly empty) List of intersection points
     *                              with the specified line.
     * @param line stands for point on desirable line.
     * @return list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pointsList = new ArrayList<>();
        addToList(this.lowerSide.intersectionWith(line), pointsList);
        addToList(this.upperSide.intersectionWith(line), pointsList);
        addToList(this.leftSide.intersectionWith(line), pointsList);
        addToList(this.rightSide.intersectionWith(line), pointsList);
        return pointsList;
    }

    /**
     * addToList method -- add intersection points between line and rectangle's side to list.
     * @param p stands for point on desirable line.
     * @param pointsList stands for points list.
     */
    private void addToList(Point p, List<Point> pointsList) {
        if (p != null) {
            int flag = 0;
            for (Point point : pointsList) {
                if (point.equals(p)) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                pointsList.add(p);
            }
        }
    }

    /**
     * getSideByPoint method -- get rectangle's side by point on it.
     * @param p stands for point on desirable line.
     * @return line that p is laying on.
     */
    public Line getSideByPoint(Point p) {
        if ((p.getX() == leftSide.start().getX()) && (p.getY() >= leftSide.start().getY())
                && (p.getY() <= leftSide.end().getY())) {
            return leftSide;
        }
        if ((p.getX() == rightSide.start().getX()) && (p.getY() >= rightSide.start().getY())
                && (p.getY() <= rightSide.end().getY())) {
            return rightSide;
        }
        if (p.getX() >= upperSide.start().getX() && p.getX() <= upperSide.end().getX()
                && p.getY() == upperSide.end().getY()) {
            return upperSide;
        }
        if (p.getX() >= lowerSide.start().getX() && p.getX() <= lowerSide.end().getX()
                && p.getY() == lowerSide.end().getY()) {
            return lowerSide;
        }
        return null;
    }

    /**
     * accessor.
     * @return width of the rectangle.
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * accessor.
     * @return height of the rectangle.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * accessor.
     * @return upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * accessor.
     * @return lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * accessor.
     * @return upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * accessor.
     * @return lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * accessor.
     * @return right side line of the rectangle.
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * accessor.
     * @return left side line of the rectangle.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * accessor.
     * @return upper side line of the rectangle.
     */
    public Line getUpperSide() {
        return this.upperSide;
    }

    /**
     * accessor.
     * @return lower side line of the rectangle.
     */
    public Line getLowerSide() {
        return this.lowerSide;
    }
}