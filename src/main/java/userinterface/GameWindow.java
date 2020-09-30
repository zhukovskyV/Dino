package userinterface;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GameWindow extends JFrame {

    private GameScreen gameScreen;

    public GameWindow() {
        super("Peepo Run!");

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

    public static void main(String args[]) {
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }
}
