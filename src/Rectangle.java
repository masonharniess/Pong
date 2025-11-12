import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle {
    private double x, y, width, height;
    private Color colour;

    public Rectangle(double x, double y, double width, double height, Color colour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(colour);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }
}
