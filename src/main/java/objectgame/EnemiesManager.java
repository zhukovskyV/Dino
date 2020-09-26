package objectgame;

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

    public EnemiesManager (MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        enemies = new ArrayList<Enemy>();
        imageCactus1 = Resourse.getResourceImage("data/cactus1.png");
        imageCactus2 = Resourse.getResourceImage("data/cactus2.png");
        random = new Random();

        Cactus cactus = new Cactus();
        enemies.add(getRandomCactus());
        random = new Random();
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
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

    private Cactus getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus();
        cactus.setX(800);
        if (random.nextBoolean()) {
            cactus.setImage(imageCactus1);
        }
        else {
            cactus.setImage(imageCactus2);
        }
        return cactus;
    }
}
