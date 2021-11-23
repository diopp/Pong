import java.awt.Color;
import java.awt.Graphics;


public class Ball {
    public static final int DIAMETER = 50;
    public int speedx = 10;
    public int speedy = 10;
    public int X;
    public int Y;

    public Ball(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(X, Y, DIAMETER, DIAMETER);
    }

    public void updatePosition() {
        X += speedx;
        Y += speedy;
    }

}
