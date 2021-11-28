import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class gameField  extends JPanel implements ActionListener {
    private final int size = 320;
    private final int DotSize = 16;
    private final int allDots = 400;
    private Image dot;
    private Image aplle;
    private int appX;
    private int appY;
    private int[] snakeX = new int[allDots];
    private int[] snakeY = new int[allDots];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean rigth = true;
    private boolean down = false;
    private boolean up = false;
    private boolean inGame = true;

    public gameField(){
        setBackground(Color.BLACK);
        loadImages();
        inItGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }
    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        aplle = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }
    public void inItGame(){
        dots = 3;
        for( int i = 0; i <= dots; i++){
            snakeX[i] = 64 - i*DotSize;
            snakeY[i] = 64;

        }
        timer = new Timer(250,this);
        timer.start();
        createApple();

    }
    public void createApple(){
        appX = new Random().nextInt(20)*DotSize;
        appY = new Random().nextInt(20)*DotSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            g.drawImage(aplle, appX, appY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, snakeX[i], snakeY[i], this);

            }
        } else {
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str, 125, size/2);
        }
    }

    public void move(){
        for (int i = dots; i > 0 ; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        if(left){
            snakeX[0] -= DotSize;
        }
        if(rigth){
            snakeX[0] += DotSize;
        }
        if(up){
            snakeY[0] -= DotSize;
        }
        if(down){
            snakeY[0] += DotSize;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkEnds();
            move();

        }
        repaint();

    }

    private void checkEnds() {
        for (int i = dots; i > 0 ; i--) {
            if(i > 4 && snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                inGame = false;

            }
        }
        if(snakeX[0] > size){
            inGame = false;
        }
        if(snakeX[0] < 0){
            inGame = false;
        }
        if(snakeY[0] > size){
            inGame = false;
        }
        if(snakeY[0] < 0){
            inGame = false;
        }
    }

    private void checkApple() {
        if(snakeX[0] == appX && snakeY[0] == appY){
            dots++;
            createApple();
        }
    }
    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !rigth){
                left = true;
                up = false;
                down = false;

            }
            if(key == KeyEvent.VK_RIGHT && !left){
                rigth = true;
                up = false;
                down = false;

            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                up = true;
                rigth = false;

            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                rigth = false;
                down = true;

            }
        }
    }
}
