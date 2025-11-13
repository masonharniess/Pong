public class Ball {
    public Rectangle rectangle;
    public Rectangle leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double velocityY = 0;
    private double velocityX = -150.0;

    public Ball(Rectangle rectangle, Rectangle leftPaddle, Rectangle rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rectangle = rectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
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

        if (this.rectangle.getX() + this.rectangle.getWidth() < leftPaddle.getX()){
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore;
            this.rectangle.setX(Constants.SCREEN_WIDTH / 2.0);
            this.rectangle.setY(Constants.SCREEN_HEIGHT / 2.0);
            this.velocityY = 0;
            this.velocityX = -150;
            if (rightScore >= Constants.WIN_SCORE) {
                Main.changeState(0);
            }
        } else if (this.rectangle.getX() > rightPaddle.getX() + rightPaddle.getWidth()) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;
            this.rectangle.setX(Constants.SCREEN_WIDTH / 2.0);
            this.rectangle.setY(Constants.SCREEN_HEIGHT / 2.0);
            this.velocityY = 0;
            this.velocityX = 150;
            if (leftScore >= Constants.WIN_SCORE) {
                Main.changeState(0);
            }
        }
    }
}