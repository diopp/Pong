import java.awt.Color;
import java.awt.Graphics;


public class Paddle1 {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 230;
    public static final int dx = 0;
    int x;
    int y;
    int dy;




    Paddle1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int amount) {
        y += amount;

    }

    public void movePaddle(int dx, int dy) {

        y += dy;

        if (y <= 0) {
            y = 0;
        } else if (y + HEIGHT >= Pong.HEIGHT) {
            y = Pong.HEIGHT - HEIGHT;
        }
    }


    //Game state
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}