import java.util.Scanner;

public class NBody {
    public static String dataFilePath = "./data/planets.txt";
    public static String imageBackground = "./images/starfield.jpg";

    public static double readRadius(String dataFilePath){
        In in = new In(dataFilePath);

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();

        return secondItemInFile;
    }

    public static Body[] readBodies(String dataFilePath){
        In in = new In(dataFilePath);

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        Body[] bodies = new Body[firstItemInFile];
        for(int i=0; i<firstItemInFile; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xP, yP, xV, yV, m, img);
        }

        return bodies;
    }

    public static void main(String[] args){
        Scanner newObj = new Scanner(System.in);

        System.out.println("Enter T, dt and filename, separate by space (e.g: 1.0 2.0 test.txt)");
        double T = newObj.nextDouble();
        double dt = newObj.nextDouble();
        String fileName = newObj.nextLine().trim();

        double radius = NBody.readRadius(fileName);
        Body[] bodies = NBody.readBodies(fileName);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();

        for (int timeCnt = 0; timeCnt <= T; timeCnt++){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for(int i = 0; i < bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for(int i = 0; i < bodies.length; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, imageBackground);
            for(Body t : bodies) Body.draw(t);

            StdDraw.show();
            int waitTimeMilliseconds = 10;
            StdDraw.pause(waitTimeMilliseconds);

            waitTimeMilliseconds = (int) (waitTimeMilliseconds + dt);

        }

    }
}
