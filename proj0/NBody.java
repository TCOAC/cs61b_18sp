public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int N = in.readInt();
        return in.readDouble();
    }
    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int N = in.readInt();
        Body[] bodies = new Body[N];
        in.readDouble();
        for (int i = 0; i < N; i++){
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return bodies;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Body b : bodies){
            b.draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t < T; t += dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (Body body : bodies) {
            StdOut.printf("%.4e %.4e %.4e %.4e %.4e %s\n", body.xxPos, body.yyPos, body.xxVel, body.yyVel, body.mass, body.imgFileName);
        }
    }
}