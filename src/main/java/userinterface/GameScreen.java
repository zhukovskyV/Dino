package userinterface;

import objectgame.*;
import util.Resourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 250;

    private Thread thread;
    private MainCharacter mainCharacter;
    private Land land;
    private Clouds clouds;
    private EnemiesManager enemiesManager;

    private int gameState = GAME_FIRST_STATE;

    private BufferedImage imageGameOverText;

    public GameScreen() {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        land = new Land(this);
        clouds = new Clouds();
        enemiesManager = new EnemiesManager(mainCharacter);
        imageGameOverText = Resourse.getResourceImage("data/gameover_text.png");
  }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        switch (gameState) {
            case GAME_PLAY_STATE:
                mainCharacter.update();
                land.update();
                clouds.update();
                enemiesManager.update();
                if (!mainCharacter.getAlive()) {
                    gameState = GAME_OVER_STATE;
                }
                break;
        }

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawLine(0, (int)GROUNDY, getWidth(), (int)GROUNDY);

        switch (gameState) {
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
               // mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawImage(imageGameOverText, 300, 110, null);
                break;
        }


    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // mainCharacter.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
            if (gameState == GAME_FIRST_STATE)
                gameState = GAME_PLAY_STATE;
            else if (gameState == GAME_PLAY_STATE)
                mainCharacter.jump();
            else if (gameState == GAME_OVER_STATE) {
                gameState = GAME_PLAY_STATE;
            }
            break;
        }
    }
}
