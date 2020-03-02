public class Body {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body t){
        double x = 0;
        double y = 0;
        if (t != null){
            x = t.xxPos - this.xxPos;
            y = t.yyPos - this.yyPos;
            return Math.sqrt(x*x + y*y);
        }
        return -1;
    }

    public double calcForceExertedBy(Body t){
        if (t != null){
            return (G * this.mass * t.mass) / Math.pow(this.calcDistance(t), 2);
        }
        return -1;
    }

    public double calcForceExertedByX(Body t){
        if (t != null){
            return this.calcForceExertedBy(t) * (t.xxPos - this.xxPos) / this.calcDistance(t);
        }
        return -1;
    }

    public double calcForceExertedByY(Body t){
        if (t != null){
            return this.calcForceExertedBy(t) * (t.yyPos - this.yyPos) / this.calcDistance(t);
        }
        return -1;
    }

    public double calcNetForceExertedByX(Body[] t){
        double netX = 0;
        for (int i = 0; i < t.length; i++){
            if (t[i] != null && !t[i].equals(this)) {
                netX = netX + this.calcForceExertedByX(t[i]);
            }
        }
        return netX;
    }

    public double calcNetForceExertedByY(Body[] t){
        double netY = 0;
        for (int i = 0; i < t.length; i++){
            if (t[i] != null && !t[i].equals(this)) {
                netY = netY + this.calcForceExertedByY(t[i]);
            }
        }
        return netY;
    }

    public void update(double dt, double fX, double fY){
        this.xxVel = this.xxVel + dt * fX/this.mass;
        this.yyVel = this.yyVel + dt * fY/this.mass;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public static void draw(Body t){
        StdDraw.picture(t.xxPos, t.yyPos, "./images/"+t.imgFileName);
    }

}
