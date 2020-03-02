/**
 * @author YC 02/18/2020
 * Sample Answer
 */

package bearmaps;
import java.util.*;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private KDNode root;

    private class KDNode {
        Point point;
        KDNode leftChild;
        KDNode rightChild;
        boolean axis; // true for x, false for y

        private KDNode(Point p, boolean axis) {
            this.point = p;
            this.axis = axis;
        }
    }
    // Constructor. Assume points has at least size 1.
    public KDTree(List<Point> points) {
        if (points.size() == 0) {
            throw new IllegalArgumentException();
        }
        for (Point p:points) {
            root = buildKDTree(p, root, HORIZONTAL);
        }
    }

    private KDNode buildKDTree(Point p, KDNode n, boolean axis) {
        if (n == null) {
            return new KDNode(p, axis);
        }

        if (p.equals(n.point)) {
            return n;
        }

        int cmp = cmpPoint(p, n.point, axis);
        if (cmp < 0) {
            n.leftChild = buildKDTree(p, n.leftChild, !axis);
        } else {
            n. rightChild = buildKDTree(p, n.rightChild, !axis);
        }
        return n;
    }

    private int cmpPoint(Point p1, Point p2, boolean axis) {
        if (axis == HORIZONTAL) {
            return Double.compare(p1.getX(), p2.getX());
        } else {
            return Double.compare(p1.getY(), p2.getY());
        }
    }

    // Returns the closest point to the inputted coordinates. Take O(logN) time on average.
    @Override
    public Point nearest(double x, double y) {
        return findNearest(root, new Point(x, y), root.point);
    }

    private Point findNearest(KDNode n, Point goal, Point best) {
       if (n == null) {
           return best;
       }

       if (Point.distance(n.point, goal) < Point.distance(best, goal)) {
           best = n.point;
       }

       int cmp = cmpPoint(goal, n.point, n.axis);
       KDNode goodSide, badSide;
       if (cmp < 0) {
           goodSide = n.leftChild;
           badSide = n.rightChild;
       } else {
           goodSide = n.rightChild;
           badSide = n.leftChild;
       }

       best = findNearest(goodSide, goal, best);
       if (worthLooking(n, goal, best)) {
           best = findNearest(badSide, goal, best);
       }

       return best;
    }

    private boolean worthLooking(KDNode n, Point goal, Point best) {
        double distToBest = Point.distance(goal, best);
        double distToBad;
        if (n.axis == HORIZONTAL) {
            distToBad = Point.distance(new Point(goal.getX(), n.point.getY()), goal);
        } else {
            distToBad = Point.distance(new Point(n.point.getX(), goal.getY()), goal);
        }
        return distToBad < distToBest;
    }

    // Printing
    private void printKD() {
//        printTreeHelper(root); //print tree
        printNode(root.leftChild); //print node
    }

    private void printTreeHelper(KDNode n) {
        System.out.println("x: " + n.point.getX() + ", y: " + n.point.getY());
        if (n.leftChild != null) {
            printTreeHelper(n.leftChild);
        }
        if (n.rightChild != null) {
            printTreeHelper(n.rightChild);
        }
    }

    private void printNode(KDNode n) {
        System.out.println("Node -- x: " + n.point.getX() + ", y: " + n.point.getY());
        System.out.println("X axis: " + n.axis);
        if (n.leftChild != null) {
            System.out.println("Left -- x: " + n.leftChild.point.getX() + ", y: " + n.leftChild.point.getY());
        }
        if (n.rightChild != null) {
            System.out.println("Right -- x: " + n.rightChild.point.getX() + ", y: " + n.rightChild.point.getY());
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree nn = new KDTree(Arrays.asList(p1, p2, p3, p4, p5, p6));
        Point ret = nn.nearest(0, 7); // returns p7
        System.out.println(ret);
    }

}
