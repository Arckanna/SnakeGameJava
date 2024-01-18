import javax.swing.*;
import java.awt.*;

public class Graphics extends JPanel {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;

    public Graphics() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.CYAN);
        this.setFocusable(true);
    }
}
