import java.awt.event.KeyEvent;

public class PlayerController {
    private Rectangle rectangle;
    public KL keyListener;

    public PlayerController(Rectangle rectangle, KL keyListener) {
        this.rectangle = rectangle;
        this.keyListener = keyListener;
    }

    public void update (double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            if ((rectangle.getY() + Constants.PADDLE_SPEED * dt) + rectangle.getHeight() < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
                this.rectangle.setY(this.rectangle.getY() + Constants.PADDLE_SPEED * dt);
            }

        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            if (rectangle.getY() + Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT) {
                this.rectangle.setY(this.rectangle.getY() - Constants.PADDLE_SPEED * dt);
            }
        }
    }
}
