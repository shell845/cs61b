public class TestBody {
    public static void main(String[] args){
        Body b1 = new Body(1.0, 0.0, 3.0, 4.0, 10.0, "jupiter.gif");
        Body b2 = new Body(3.0, 3.0, 3.0, 4.0, 5.0, "mars.gif");

        System.out.println (b1.calcForceExertedByX(b2));
    }
}
