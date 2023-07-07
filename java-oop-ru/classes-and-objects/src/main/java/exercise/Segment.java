package exercise;

// BEGIN
public class Segment {

    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        var x = (getBeginPoint().getX() + getEndPoint().getX()) / 2;
        var y = (getBeginPoint().getY() + getEndPoint().getY()) / 2;
        return new Point(x, y);
    }
}
// END
