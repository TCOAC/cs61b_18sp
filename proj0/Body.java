public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public static final double G = 6.67e-11;
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        return Math.sqrt((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
    }
    public double calcForceExertedBy(Body b){
        return G * this.mass * b.mass / (this.calcDistance(b) * this.calcDistance(b));
    }
    public double calcForceExertedByX(Body b){
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }
    public double calcForceExertedByY(Body b){
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }
    public double calcNetForceExertedByX(Body[] allBodys){
        double netForceX = 0;
        for (Body b : allBodys){
            if (!this.equals(b)){
                netForceX += this.calcForceExertedByX(b);
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Body[] allBodys){
        double netForceY = 0;
        for (Body b : allBodys){
            if (!this.equals(b)){
                netForceY += this.calcForceExertedByY(b);
            }
        }
        return netForceY;
    }
    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
