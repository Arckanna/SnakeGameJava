import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public void actionPerformed(ActionEvent e) {
        repaint();
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
}
