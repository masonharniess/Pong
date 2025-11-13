public class AIController {
    public PlayerController playerController;
    public Rectangle ball;

    public AIController(PlayerController playerController, Rectangle ball){
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update(double dt) {
        playerController.update(dt);

        if (ball.getY() < playerController.rectangle.getY()) {
            this.playerController.moveUp(dt);
        } else if (ball.getY() + ball.getHeight() > playerController.rectangle.getY() + playerController.rectangle.getHeight()) {
            this.playerController.moveDown(dt);
        }
    }
}