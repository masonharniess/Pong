import java.awt.event.KeyEvent;

public class PlayerController {
    public Rectangle rectangle;
    public KL keyListener;

    public PlayerController(Rectangle rectangle, KL keyListener) {
        this.rectangle = rectangle;
        this.keyListener = keyListener;
    }

    public PlayerController(Rectangle rectangle){
        this.rectangle = rectangle;
        this.keyListener = null;
    }

    public void update(double dt) {
        if (keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(dt);

            } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(dt);
            }
        }
    }

    public void moveUp(double dt) {
        if (rectangle.getY() + Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT) {
            this.rectangle.setY(this.rectangle.getY() - Constants.PADDLE_SPEED * dt);
        }
    }
    public void moveDown(double dt){
        if ((rectangle.getY() + Constants.PADDLE_SPEED * dt) + rectangle.getHeight() < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
            this.rectangle.setY(this.rectangle.getY() + Constants.PADDLE_SPEED * dt);
        }
    }
}
