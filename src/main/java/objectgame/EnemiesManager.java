package objectgame;

import userinterface.GameScreen;
import util.Resourse;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemiesManager {
    private List<Enemy> enemies;
    private Random random;

    private BufferedImage imageCactus1, imageCactus2;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen;

    public EnemiesManager (MainCharacter mainCharacter, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.mainCharacter = mainCharacter;
        enemies = new ArrayList<Enemy>();
        imageCactus1 = Resourse.getResourceImage("data/enemy.png");
        imageCactus2 = Resourse.getResourceImage("data/cactus_2.png");
        random = new Random();

        Cactus cactus = new Cactus(mainCharacter);
        enemies.add(getRandomCactus());
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update(gameScreen);
            if (e.isOver() && !e.isScoreGot()) {
                gameScreen.plusScore(20);
                e.setIsScoreGot(true);
            }
            if (e.getBound().intersects(mainCharacter.getBound())) {
                mainCharacter.setAlive(false);
            }
        }
        Enemy firstEnemy = enemies.get(0);
        if (firstEnemy.isOutofScreen()) {
            enemies.remove(firstEnemy);
            enemies.add(getRandomCactus());
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    public void reset() {
        enemies.clear();
        enemies.add(getRandomCactus());
    }

    private Cactus getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus(mainCharacter);
        cactus.setX(800);
        if (random.nextBoolean()) {
            cactus.setImage(imageCactus1);
            cactus.setY(100);
        }
        else {
            cactus.setImage(imageCactus2);
        }
        return cactus;
    }
}
