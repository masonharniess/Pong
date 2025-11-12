public class Ball {
    public Rectangle rectangle;
    public Rectangle leftPaddle, rightPaddle;

    private double velocityY = 400.0;
    private double velocityX = -150.0;

    public Ball(Rectangle rectangle, Rectangle leftPaddle, Rectangle rightPaddle) {
        this.rectangle = rectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double dt) {
        if (velocityX < 0) {
            if (this.rectangle.getX() <= this.leftPaddle.getX() + this.leftPaddle.getWidth() &&
                    this.rectangle.getY() >= this.leftPaddle.getY() &&
                    this.rectangle.getY() <= this.leftPaddle.getY() + this.leftPaddle.getHeight() &&
                    this.rectangle.getX() >= this.leftPaddle.getX())
            {
                this.velocityX *= -1;
                this.velocityY *= -1;
            } else if (this.rectangle.getX() + this.rectangle.getWidth() < this.leftPaddle.getX()) {
                System.out.println("Player has lost 1 point.");
            }
        } else if (velocityX > 0) {
            if (this.rectangle.getX() + this.rectangle.getWidth() >= this.rightPaddle.getX() &&
                    this.rectangle.getX() <= this.rightPaddle.getX() + this.rightPaddle.getWidth() &&
                    this.rectangle.getY() >= this.rightPaddle.getY() &&
                    this.rectangle.getY() <= this.rightPaddle.getY() + this.rightPaddle.getHeight())
            {
            } else if (this.rectangle.getX() + this.rectangle.getWidth() > this.rightPaddle.getX() + this.rightPaddle.getWidth()) {
                System.out.println("AI has lost 1 point.");
            }
        }

        if (velocityY > 0) {
            if (this.rectangle.getY() + this.rectangle.getHeight() > Constants.SCREEN_HEIGHT) {
                this.velocityY *= -1;
            }
        } else if (velocityY < 0) {
            if (this.rectangle.getY() < Constants.TOOLBAR_HEIGHT) {
                this.velocityY *= -1;
            }
        }

        this.rectangle.setX(this.rectangle.getX() + velocityX * dt);
        this.rectangle.setY(this.rectangle.getY() + velocityY * dt);
    }
}