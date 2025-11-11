import java.awt.*;

public class Rectangle {
    private int x, y, width, height;
    private Color colour;

    public Rectangle(int x, int y, int width, int height, Color colour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(colour);
        g2.fillRect(x, y, width, height);
    }
}
