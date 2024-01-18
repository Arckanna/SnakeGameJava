import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Graphics extends JPanel implements ActionListener {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int TICK_SIZE = 50;
    static final int BOARD_SIZE =(WIDTH * HEIGHT) / (TICK_SIZE * TICK_SIZE);

    int[] snakePosX = new int[BOARD_SIZE];
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;
    final Timer timer = new Timer(150, this);

    char direction = 'R';
    boolean isMoving = false;
    public Graphics() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.CYAN);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(isMoving){
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT:
                            if(direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if(direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if(direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if(direction != 'U') {
                                direction = 'D';
                            }
                            break;
                    }
                }
            }
        });
        start();
    }
    protected void start() {
        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        isMoving = true;
        snakeLength = 5;
        direction ='R';
        timer.start();
    }
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (isMoving){
            g.setColor(Color.BLACK);
            for (int i=0;i< snakeLength;i++){
                g.fillRect(snakePosX[i],snakePosY[i], TICK_SIZE,TICK_SIZE);

            }
        }
    }
    protected void move() {
        for (int i = snakeLength; i> 0; i--){
            snakePosX[i] = snakePosX[i-1];
            snakePosY[i] = snakePosY[i-1];
        }
        switch (direction) {
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isMoving){
            move();
        }
    }
}
