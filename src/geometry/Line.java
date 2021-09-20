package geometry;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * A line connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    private static final double EPSILON = 0.00001;
    // Fields
    private Point start;
    private Point end;

    /**
     * constructor.
     * @param start stands for start point.
     * @param end stand for end point.
     */
    public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
    }
    /**
     * constructor.
     * @param x1 stands for first point's x axe.
     * @param y1 stand for first point's y axe.
     * @param x2 stand for second point's x axe.
     * @param y2 stand for second point's y axe.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 <= x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else if (x1 > x2) {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
    }

    /**
     * length method -- Return the length of the line.
     * @return length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * middle method -- Returns the middle point of the line.
     * @return middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * start method -- Returns the start point of the line.
     * @return start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end method -- Returns the end point of the line.
     * @return end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * getSlope method -- Returns slope of line.
     * @return slope of the line.
     */
    private double getSlope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * isParallel method -- checks if lines are parallel by slope.
     * @param other stands for another line to compare.
     * @return true if the lines parallel, false otherwise.
     */
    private boolean isParallel(Line other) {
        double m1, m2;
        m1 = (start.getY() - end.getY()) / (start.getX() - end.getX());
        m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        return m1 == m2;
    }

    /**
     * isRally method -- checks if lines are rally.
     * @param other stands for another line to compare.
     * @return true if the lines rally, false otherwise.
     */
    private boolean isRally(Line other) {
        if (equals(other)) {
            return true;
        }
        if (!isParallel(other) || ((this.start.getX() == other.end().getX())
                && (this.start.getY() == other.end().getY()) || (this.end.getX() == other.start().getX())
                    && (this.end.getY() == other.start().getY()))) {
            return false;
        }
        if (!((other.start().getX() <= this.end.getX()) && (this.end.getX() <= other.end.getX()))
                && !((other.start().getX() <= this.start.getX()) && (this.start.getX() <= other.end.getX()))) {
            return false;
        }
        if (!((other.start().getY() <= this.end.getY()) && (this.end.getY() <= other.end.getY()))
                && !((other.start().getX() <= this.start.getY()) && (this.start.getY() <= other.end.getY()))) {
            return false;
        }
        // calculate slope and constant value
        double m1, m2, c1, c2, interX, interY;
        m1 = (start.getY() - end.getY()) / (start.getX() - end.getX());
        m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        c1 = start.getY() - m1 * start.getX();
        c2 = other.start().getY() - m2 * other.start().getX();
        return (m1 == m2 && c1 == c2);
    }

    /**
     * isIntersecting method -- checks if lines intersects.
     * @param other stands for another line to compare.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (isRally(other)) {
            return true;
        }
        double m1, m2;
        m1 = getSlope();
        m2 = other.getSlope();
        if ((Double.isInfinite(m1) && Double.isInfinite(m2) || (m1 == 0 && m2 == 0))) {
            return (this.start.equals(other.start())) || (this.start.equals(other.end()))
                    || (this.end.equals(other.end())) || (this.end.equals(other.start()));
        }
        return getIntersection(other) != null;
    }

    /**
     * intersectionWith method -- returns intersection point.
     * @param other stands for another line to compare.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (isRally(other) || !isIntersecting(other)) {
            return null;
        }
        double m1, m2;
        m1 = getSlope();
        m2 = other.getSlope();
        Point p;
        if ((Double.isInfinite(m1) && Double.isInfinite(m2) || (m1 == 0 && m2 == 0))) {
            if ((this.start.equals(other.start()))) {
                p = new Point(start.getX(), start.getY());
                return p;
            }
            if ((this.start.equals(other.end()))) {
                p = new Point(start.getX(), start.getY());
                return p;
            }
            if ((this.end.equals(other.end()))) {
                p = new Point(end.getX(), end.getY());
                return p;
            }
            if ((this.end.equals(other.start()))) {
                p = new Point(end.getX(), end.getY());
                return p;
            }
            return null;
        }
        p = getIntersection(other);
        return p;
    }

    /**
     * equals method -- checks if lines are equal.
     * @param other stands for another line to compare.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((((other.start().getX() == start.getX()) && (other.end().getX() == end.getX()))
                || ((other.end().getX() == start.getX()) && (other.start().getX() == end.getX())))
                && (((other.start().getY() == start.getY()) && (other.end().getY() == end.getY()))
                || ((other.end().getY() == start.getY()) && (other.start().getY() == end.getY()))));
    }

    /**
     * intersectionWith method -- returns intersection point.
     * @param other stands for another line to compare.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    private Point getIntersection(Line other) {
        // calculate slope and constant value
        double m1, m2, c1, c2, interX, interY;
        m1 = getSlope();
        m2 = other.getSlope();
        c1 = start.getY() - m1 * start.getX();
        c2 = other.start().getY() - m2 * other.start().getX();
        interX = (c2 - c1) / (m1 - m2);
        interY = m1 * interX + c1;
        if (Double.isInfinite(m1)) {
            interX = this.start.getX();
            interY = m2 * interX + c2;
        }
        if (Double.isInfinite(m2)) {
            interX = other.start().getX();
            interY = m1 * interX + c1;
        }
        Point p = new Point(interX, interY);

        // check validity of intersection point
        if ((Math.abs(p.distance(start) + p.distance(end) - start.distance(end)) < EPSILON)
                && (Math.abs(p.distance(other.start()) + p.distance(other.end())
                - other.start().distance(other.end())) < EPSILON)) {
            return p;
        }
        return null;
    }

    /**
     * closestIntersectionToStartOfLine method -- If this line does not intersect with the rectangle,
     * return null. Otherwise, return the closest intersection point to the start of the line.
     * @param rect stands for the rectangle which we check if line intersecting with.
     * @return closest intersection point to line's start.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(this.start, this.end);
        java.util.List<Point> pointsList = rect.intersectionPoints(line);
        if (pointsList.isEmpty()) {
            return null;
        }
        Point closestPoint = new Point(pointsList.get(0));
        for (Point point : pointsList) {
            if (point.distance(line.start) < closestPoint.distance(line.start)) {
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    /**
     * accessor.
     * @return end point.
     */
    public Point getEnd() {
        return end;
    }

    /**
     * accessor.
     * @return start point.
     */
    public Point getStart() {
        return start;
    }
}