import javax.swing.JFrame;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text startGame, exitGame, title;
    public boolean isRunning = true;


    public MainMenu () {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.title = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 50), Constants.SCREEN_WIDTH / 2.0 - 50.0, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
        this.startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 30), Constants.SCREEN_WIDTH / 2.0 - 68, Constants.SCREEN_HEIGHT / 2.0 + 75, Color.WHITE);
        this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 25), Constants.SCREEN_WIDTH / 2.0 - 20.0, Constants.SCREEN_HEIGHT / 2.0 + 110, Color.WHITE);

        g2 = (Graphics2D) getGraphics();
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbGraphics = dbImage.getGraphics();
        draw(dbGraphics);
        g2.drawImage(dbImage, 0, 0, this);

//        System.out.println(mouseListener.getMouseX());
//        System.out.println(mouseListener.getMouseY());

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width && mouseListener.getMouseY() > startGame.y - startGame.height / 2.0 && mouseListener.getMouseY() < startGame.y + startGame.height / 2.0) {
            startGame.colour = new Color(158, 158, 158);

            if (mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        } else {
            startGame.colour = Color.WHITE;
        }

        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width && mouseListener.getMouseY() > exitGame.y - exitGame.height / 2.0 && mouseListener.getMouseY() < exitGame.y + exitGame.height / 2.0) {
            exitGame.colour = new Color(158, 158, 158);
            if (mouseListener.isMousePressed()) {
                Main.changeState(2);
            }
        } else {
            exitGame.colour = Color.WHITE;
        }

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        title.draw(g2);
        startGame.draw(g2);
        exitGame.draw(g2);

    }

    public void stop() {
        isRunning = false;
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
        this.dispose();
        return;
    }
}
