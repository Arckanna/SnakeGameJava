import java.awt.*;

public class Segment {
    private int x, y;
    private Segment previousSegment;

    public Segment(int x, int y, Segment previousSegment) {
        this.x = x;
        this.y = y;
        this.previousSegment = previousSegment;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g, Image image) {
        if (previousSegment != null) {
            int dx = x - previousSegment.getX();
            int dy = y - previousSegment.getY();
            double angle = Math.atan2(dy, dx);
            ((Graphics2D) g).rotate(angle, x, y);
        }

        g.drawImage(image, x, y, Graphics.TICK_SIZE, Graphics.TICK_SIZE, null);

        if (previousSegment != null) {
            ((Graphics2D) g).rotate(-angle, x, y);
        }
    }
}
