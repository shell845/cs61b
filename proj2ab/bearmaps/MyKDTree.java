/**
 * @author YC 02/18/2020
 */
/*

package bearmaps;
import java.util.*;

public class KDTree {
    private List<Point> pList;
    private KDNode root;

    Comparator<Point> cmpX = (Point p1, Point p2) -> (Double.compare(p1.getX(), p2.getX()));
    Comparator<Point> cmpY = (Point p1, Point p2) -> (Double.compare(p1.getY(), p2.getY()));

    private class KDNode {
        Point point;
        KDNode leftChild;
        KDNode rightChild;
        boolean axisX; // true for x, false for y

        private KDNode(Point p) {
            this.point = p;
            leftChild = null;
            rightChild = null;
        }
    }
    // Constructor. Assume points has at least size 1.
    public KDTree(List<Point> points) {
        if (points.size() == 0) {
            throw new IllegalArgumentException();
        }
        this.pList = points;
        buildKDTree();
    }

    private void buildKDTree() {
        root = new KDNode(pList.get(0));
        root.axisX = true; // start for X axis

        for (int i = 1; i < pList.size(); i++) {
            KDNode n = new KDNode(pList.get(i));
            buildHelper(root, n);
        }
    }

    private void buildHelper(KDNode k, KDNode n) {
        if (k.axisX) { // compare x
            if (Double.compare(n.point.getX(), k.point.getX()) < 0) { // new node < parent node in x
                if (k.leftChild == null) {
                    k.leftChild = n;
                    n.axisX = false; // compare next with y
                    return;
                } else {
                    buildHelper(k.leftChild, n);
                }
            } else {                                                  // new node >= parent node in x
                if (k.rightChild == null) {
                    k.rightChild = n;
                    n.axisX = false; // compare next with y
                    return;
                } else {
                    buildHelper(k.rightChild, n);
                }
            }
        } else {      // compare y
            if (Double.compare(n.point.getY(), k.point.getY()) < 0) { // new node < parent node in y
                if (k.leftChild == null) {
                    k.leftChild = n;
                    n.axisX = true;  // compare next with x
                    return;
                } else {
                    buildHelper(k.leftChild, n);
                }
            } else {                                                  // new node >= parent node in y
                if (k.rightChild == null) {
                    k.rightChild = n;
                    n.axisX = true;  // compare next with x
                    return;
                } else {
                    buildHelper(k.rightChild, n);
                }
            }
        }
    }


    private double minDis = -1;
    private Point nearestPoint;

    // Returns the closest point to the inputted coordinates. Take O(logN) time on average.
    public Point nearest(double x, double y) {
        KDNode n = root;
        Point goal = new Point(x, y);
        findNearest(n, goal);
        return nearestPoint;
    }

    private void findNearest(KDNode n, Point goal) {
        */
/*//*
/ Simply traverses entire tree
        if (n.leftChild != null) {
            findNearest(n.leftChild, goal);
        }
        if (n.rightChild != null) {
            findNearest(n.rightChild, goal);
        }
        double newDis = distance(n.point, goal);
        if (minDis < 0 || newDis < minDis) {
            minDis = newDis;
            nearestPoint = n.point;
        }*//*


        // visits the “good” child before the “bad” child
        if (n.axisX) { // compare x
            if (goal.getX() < n.point.getX()) {
                if (n.leftChild == null) {
                    updateNearest(n.point, goal);
                } else {
                    findNearest(n.leftChild, goal);
                }
            } else {
                if (n.rightChild == null) {
                    updateNearest(n.point, goal);
                } else {
                    findNearest(n.rightChild, goal);
                }
            }
        } else {  // compare y
            if (goal.getY() < n.point.getY()) {
                if (n.leftChild == null) {
                    updateNearest(n.point, goal);
                } else {
                    findNearest(n.leftChild, goal);
                }
            } else {
                if (n.rightChild == null) {
                    updateNearest(n.point, goal);
                } else {
                    findNearest(n.rightChild, goal);
                }
            }
        }
    }

    private void updateNearest(Point p, Point g) {
        double dis = distance(p, g);
        System.out.println("***");
        if (minDis < 0 || dis < minDis) {
            minDis = dis;
            nearestPoint = p;
        }
    }

    // Return squared distance.
    private double distance(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
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
        System.out.println("X axis: " + n.axisX);
        if (n.leftChild != null) {
            System.out.println("Left -- x: " + n.leftChild.point.getX() + ", y: " + n.leftChild.point.getY());
        }
        if (n.rightChild != null) {
            System.out.println("Right -- x: " + n.rightChild.point.getX() + ", y: " + n.rightChild.point.getY());
        }
    }

    public static void main(String[] args) {
//        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
//        Point p2 = new Point(3.3, 4.4);
//        Point p3 = new Point(-2.9, 4.2);
//        Point p4 = new Point(0, 5);
//        Point p5 = new Point(4, 0);
//        Point p6 = new Point(2, 7);
//        Point p7 = new Point(-2, 0);

        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree nn = new KDTree(Arrays.asList(p1, p2, p3, p4, p5, p6));
//        nn.printKD();
//        KDTree nn = new KDTree(Arrays.asList(p1, p2, p3));
//        Point ret = nn.nearest(3.0, 4.0); // returns p2
//        Point ret = nn.nearest(-1.0, 2.0); // returns p7
        Point ret = nn.nearest(0, 7); // returns p7
        System.out.println(ret);
    }

}
*/
