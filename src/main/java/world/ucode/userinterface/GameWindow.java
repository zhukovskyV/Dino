package world.ucode.userinterface;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    private final GameScreen gameScreen;

    public GameWindow() {
        super("Peppo Run!");

        setSize(800, 350);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }

    public void startGame() {
        gameScreen.startGame();
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }
}
