public class Ball {
    public Rectangle rectangle;
    public Rectangle leftPaddle, rightPaddle;

    private double velocityY = 0;
    private double velocityX = -150.0;

    public Ball(Rectangle rectangle, Rectangle leftPaddle, Rectangle rightPaddle) {
        this.rectangle = rectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    // compute rebound angle in radians based on where the ball struck the paddle
    public double calculateNewVelocityAngle(Rectangle paddle) {
        double relativeIntersectY = (paddle.getY() + (paddle.getHeight() / 2.0)) - (this.rectangle.getY() + (this.rectangle.getHeight() / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.getHeight() / 2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    // flip horizontal direction to bounce ball away
    public void bounceOff(Rectangle paddle) {
        double theta = calculateNewVelocityAngle(paddle);
        double newVelocityX = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
        double newVelocityY = (-Math.sin(theta)) * Constants.BALL_SPEED;
        double oldSign = Math.signum(velocityX);
        this.velocityX = newVelocityX * (- 1.0 * oldSign);
        this.velocityY = newVelocityY;
    }

    public void update(double dt) {
        if (velocityX < 0) {
            if (this.rectangle.getX() <= this.leftPaddle.getX() + this.leftPaddle.getWidth() &&
                    this.rectangle.getY() >= this.leftPaddle.getY() &&
                    this.rectangle.getY() <= this.leftPaddle.getY() + this.leftPaddle.getHeight() &&
                    this.rectangle.getX() >= this.leftPaddle.getX())
            {
                bounceOff(leftPaddle);
            } else if (this.rectangle.getX() + this.rectangle.getWidth() < this.leftPaddle.getX()) {
                System.out.println("Player has lost 1 point.");
            }
        } else if (velocityX > 0) {
            if (this.rectangle.getX() + this.rectangle.getWidth() >= this.rightPaddle.getX() &&
                    this.rectangle.getX() <= this.rightPaddle.getX() + this.rightPaddle.getWidth() &&
                    this.rectangle.getY() >= this.rightPaddle.getY() &&
                    this.rectangle.getY() <= this.rightPaddle.getY() + this.rightPaddle.getHeight())
            {
                bounceOff(rightPaddle);
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