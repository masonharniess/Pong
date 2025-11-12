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

        double ballLeft   = rectangle.getX();
        double ballRight  = ballLeft + rectangle.getWidth();
        double ballTop    = rectangle.getY();
        double ballBottom = ballTop + rectangle.getHeight();

        double leftPaddleLeft   = leftPaddle.getX();
        double leftPaddleRight  = leftPaddleLeft + leftPaddle.getWidth();
        double leftPaddleTop    = leftPaddle.getY();
        double leftPaddleBottom = leftPaddleTop + leftPaddle.getHeight();

        double rightPaddleLeft   = rightPaddle.getX();
        double rightPaddleRight  = rightPaddleLeft + rightPaddle.getWidth();
        double rightPaddleTop    = rightPaddle.getY();
        double rightPaddleBottom = rightPaddleTop + rightPaddle.getHeight();

        if (velocityX < 0) {
            boolean touchingLeftPaddleHoriz = ballLeft <= leftPaddleRight && ballRight >= leftPaddleLeft;
            boolean touchingLeftPaddleVert = ballBottom >= leftPaddleTop && ballTop <= leftPaddleBottom;

            if (touchingLeftPaddleHoriz && touchingLeftPaddleVert) {
                bounceOff(leftPaddle);
            } else if (ballRight < leftPaddleLeft) {
                System.out.println("Player has lost 1 point.");
            }

        } else if (velocityX > 0) {
            boolean touchingRightPaddleHoriz = ballRight >= rightPaddleLeft && ballLeft <= rightPaddleRight;
            boolean touchingRightPaddleVert = ballBottom >= rightPaddleTop && ballTop <= rightPaddleBottom;

            if (touchingRightPaddleHoriz && touchingRightPaddleVert) {
                bounceOff(rightPaddle);
            } else if (ballLeft > rightPaddleRight) {
                System.out.println("AI has lost 1 point.");
            }
        }

        boolean hitBottom = (velocityY > 0) && (ballBottom > Constants.SCREEN_HEIGHT);
        boolean hitTop    = (velocityY < 0) && (ballTop    < Constants.TOOLBAR_HEIGHT);

        if (hitBottom || hitTop) {
            velocityY *= -1;
        }

        this.rectangle.setX(this.rectangle.getX() + velocityX * dt);
        this.rectangle.setY(this.rectangle.getY() + velocityY * dt);
    }
}