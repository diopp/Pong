import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;



public class Pong extends JPanel {
    int WIDTH = 1000;

    public static final int HEIGHT = 500;
    private static Pong theGame;
    private static JFrame frame;

    int speedx = 1;
    Ball ball;
    Paddle1 paddle1;
    int score = 0;
    private int winScore = 15;


    public Pong() {
        ball = new Ball(400, 400);
        paddle1 = new Paddle1(0, 20);
        addKeyListener(new PongKeyListener());
        setFocusable(true);
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.fillRect(10, 10, 1000, 500);
        ball.draw(g);
        paddle1.draw(g);
        String sc = "Papa: " + score;
        g.drawString(sc, getWidth() - 1000, 50);



    }
    class PongKeyListener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            System.out.println(e);
            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    paddle1.movePaddle(0, -12);
                    break;

                case KeyEvent.VK_DOWN:
                    paddle1.movePaddle(0, 12);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Pong Game");
        theGame = new Pong();
        //theGame.addKeyListener(new PongKeyListener());
        frame.getContentPane().add(theGame);
        frame.setSize(1000, 500);
        //frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startBallMoving();

    }



    public static void startBallMoving() {
        while (true) {

            theGame.ball.updatePosition();
            theGame.checkBounds();
            frame.repaint();
            //System.out.println("requested repaint");
            try {
                Thread.sleep(30);
            } catch (InterruptedException ie) {

            }
        }
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over, ", JOptionPane.YES_NO_OPTION);

    }

    public void gameWon() {
        JOptionPane.showMessageDialog(this, "Game Won!", "Game Won", JOptionPane.YES_NO_OPTION);

    }


    public void checkBounds() {


        if (hitRightSide()) {
            ball.X = WIDTH - Ball.DIAMETER;
            ball.speedx = -ball.speedx;


        }

        if (hitLeftSide()) {
            ball.X = WIDTH + Ball.DIAMETER;
            score = 0;
            theGame.gameOver();


        } else if (hitBottom()) {
            ball.Y = HEIGHT - Ball.DIAMETER;
            ball.speedy = -ball.speedy;
        } else if (hitTop()) {
            ball.Y = 0;
            ball.speedy = -ball.speedy;

        } else if (hitPaddle()) {

            ball.X = Paddle1.WIDTH;
            ball.speedx = -ball.speedx;
            score = score + 1;

        } else if (score == winScore) {
            score = 0;
            theGame.gameWon();


        }

    }


    public boolean hitRightSide() {
        return ball.X + Ball.DIAMETER >= WIDTH;
    }
    public boolean hitLeftSide() {
        return ball.X <= 0;
    }

    public boolean hitBottom() {
        return ball.Y + Ball.DIAMETER >= HEIGHT;
    }

    public boolean hitTop() {
        return ball.Y <= 0;
    }

    public boolean hitPaddle() {
        int contactX = ball.X;
        int contactY = ball.Y + Ball.DIAMETER / 2;
        boolean madeContact = contactX <= Paddle1.WIDTH && contactY >= paddle1.y && contactY <= paddle1.y + Paddle1.HEIGHT;
        return madeContact;

    }


}