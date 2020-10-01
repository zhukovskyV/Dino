package userinterface;

import objectgame.*;
import util.Resourse;

import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GRAVITY = 0.2f;
    public static final float GROUNDY = 250;

    private Thread thread;
    private MainCharacter mainCharacter;
    private Land land;
    private Clouds clouds;
    private EnemiesManager enemiesManager;
    private int score;

    private int gameState = GAME_FIRST_STATE;

    private BufferedImage imageGameOverText;

    public GameScreen() {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        land = new Land(this);
        clouds = new Clouds();
        enemiesManager = new EnemiesManager(mainCharacter, this);
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

    public static void jumpSound() {
        String soundName = "data/JUMP2.WAV";
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
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

    public void plusScore(int score) {
        this.score += score;
    }

    @Override
    public void paint(Graphics g) {
        //g.setColor(Color.decode("#f7f7f7"));
        g.setColor(Color.decode("#9ce8fb"));
        g.fillRect(0, 0, getWidth(), getHeight());
//        g.setColor(Color.red);
//        g.drawLine(0, (int)GROUNDY, getWidth(), (int)GROUNDY);

        switch (gameState) {
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawString("HI " + String.valueOf(score), 720, 20);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                mainCharacter.setState(3);
                g.drawImage(imageGameOverText, 300, 110, null);
                break;
        }
    }

    private void resetGame() {
        mainCharacter.setAlive(true);
        mainCharacter.setX(50);
        mainCharacter.setY(70);
        enemiesManager.reset();
      }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if (gameState == GAME_FIRST_STATE)
                    gameState = GAME_PLAY_STATE;
                else if (gameState == GAME_PLAY_STATE) {
                    mainCharacter.jump();
                    jumpSound();
                }
                else if (gameState == GAME_OVER_STATE) {
                    resetGame();
                    gameState = GAME_PLAY_STATE;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (gameState == GAME_PLAY_STATE)
                    mainCharacter.Down(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                mainCharacter.Down(false);
            case KeyEvent.VK_SPACE:
                mainCharacter.setState(0);
        }
    }
}
