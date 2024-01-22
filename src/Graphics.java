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
    static final int BOARD_SIZE = (WIDTH * HEIGHT) / (TICK_SIZE * TICK_SIZE);

    final Font font = new Font("Arial", Font.BOLD, 40);
    Color myYellow = new Color(230, 235, 197);
    Color myGreen = new Color(6, 65, 35);
    int[] snakePosX = new int[BOARD_SIZE];
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;
    Food food;
    final Timer timer = new Timer(150, this);
    // Charger l'image de la pomme
    ImageIcon appleIcon = new ImageIcon("image/pomme.png");
    ImageIcon headSnake = new ImageIcon("image/headSnake.png");
    ImageIcon corpsSnake = new ImageIcon("image/corpsSnake.png");
    ImageIcon queueSnake = new ImageIcon("image/queueSnake.png");
    Image appleImage = appleIcon.getImage();
    Image headSnakeImage = headSnake.getImage();
    Image corpsSnakeImage = corpsSnake.getImage();
    Image queueSnakeImage = queueSnake.getImage();

    char direction = 'R';
    boolean isMoving = false;

    public Graphics() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(myYellow);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isMoving) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                    }
                } else {
                    start();
                }
            }
        });
        start();
    }

    protected void start() {
        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        snakeLength = 5;
        direction = 'R';
        isMoving = true;
        spawnFood();
        timer.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (isMoving) {
            // draw food
            g.drawImage(appleImage, food.getPosX(), food.getPosY(), TICK_SIZE, TICK_SIZE, this);
            // draw snake
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.drawImage(headSnakeImage, snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE, this);
                } else if (i == snakeLength - 1) {
                    g.drawImage(queueSnakeImage, snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE, this);
                } else {
                    g.drawImage(corpsSnakeImage, snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE, this);
                }
                

            }
        } else {
            String scoreText = "Game Over! Score: " + snakeLength;
            g.setFont(font);
            g.drawString(scoreText, (WIDTH - g.getFontMetrics().stringWidth(scoreText)) / 2, HEIGHT / 2);
        }
    }

    protected void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakePosX[i] = snakePosX[i - 1];
            snakePosY[i] = snakePosY[i - 1];
        }
        switch (direction) {
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }

    protected void spawnFood() {
        food = new Food();
    }

    protected void eatFood() {
        if ((snakePosX[0] == food.getPosX()) && (snakePosY[0] == food.getPosY())) {
            snakeLength++;

            spawnFood();
        }
    }

    protected void collisionTest() {
        for (int i = snakeLength; i > 0; i--) {
            if ((snakePosX[0] == snakePosX[i]) && (snakePosY[0] == snakePosY[i])) {
                isMoving = false;
                break;
            }
        }

        if (snakePosX[0] < 0 || snakePosX[0] > WIDTH - TICK_SIZE || snakePosY[0] < 0
                || snakePosY[0] > HEIGHT - TICK_SIZE) {
            isMoving = false;
        }

        if (!isMoving) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (isMoving) {
            move();
            collisionTest();
            eatFood();
        }
        repaint();
    }
}
