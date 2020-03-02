/**
 * @author YC 02/18/2020
 */

package bearmaps;
import java.util.Arrays;
import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> points;

    // Construct NaivePointSet
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point nearestPoint = null;
        Point o = new Point(x, y);

        double minDistance = 0;
        for (Point p:points) {
            double distanct = distance(p, o);
            if (nearestPoint == null || Double.compare(distanct, minDistance) < 0) {
                minDistance = distanct;
                nearestPoint = p;
            }
        }
        return nearestPoint;
    }

    private static double distance(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(0, 5);
        Point p5 = new Point(4, 0);
        Point p6 = new Point(2, 7);
        Point p7 = new Point(-2, 0);

        NaivePointSet nn = new NaivePointSet(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
//        Point ret = nn.nearest(3.0, 4.0); // returns p2
//        Point ret = nn.nearest(-1.0, 8.0); // returns p4
        Point ret = nn.nearest(-1.0, 2.0); // returns p7
        System.out.println(ret);
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
    }
}
