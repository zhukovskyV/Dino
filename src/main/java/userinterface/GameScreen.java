package userinterface;

import objectgame.Clouds;
import objectgame.Land;
import objectgame.MainCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 250;

    private Thread thread;
    private MainCharacter mainCharacter;
    private Land land;
    private Clouds clouds;

    public GameScreen() {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        land = new Land(this);
        clouds = new Clouds();
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                mainCharacter.update();
                land.update();
                clouds.update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawLine(0, (int)GROUNDY, getWidth(), (int)GROUNDY);
        clouds.draw(g);
        land.draw(g);
        mainCharacter.draw(g);

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mainCharacter.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
