package world.ucode.objectgame;

import world.ucode.userinterface.GameScreen;
import world.ucode.util.Resourse;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static world.ucode.userinterface.GameScreen.GROUNDY;

public class Land {

    private final List<ImageLand> listImage;
    private BufferedImage imageLand1, imageLand2, imageLand3;
    private Random random;
    private GameScreen gameScreen;

    public Land(GameScreen game) {
        this.gameScreen = game;
        random = new Random();
        imageLand1 = Resourse.getResourceImage("src/main/resources/sand1.png");
        imageLand2 = Resourse.getResourceImage("src/main/resources/sand2.png");
        imageLand3 = Resourse.getResourceImage("src/main/resources/sand3.png");
        listImage = new ArrayList<>();
        int numberOfLandTitle = 800 / imageLand1.getWidth() + 2;

        for (int i = 0; i < numberOfLandTitle; i++) {
            ImageLand imageLand = new ImageLand();
            imageLand.posX = (int)(i * imageLand1.getWidth());
            imageLand.image = getImageLand();
            listImage.add(imageLand);
        }
    }

    public void update() {
        for (ImageLand imageLand : listImage) {
            if (gameScreen.getScore() > 100)
                imageLand.posX -= 9;
//            if (gameScreen.getScore() > 200)
//                imageLand.posX -= 2;
            else
                imageLand.posX -= 6;
        }
        ImageLand firstElement = listImage.get(0);
        if (listImage.get(0).posX + imageLand1.getWidth() < 0) {
            firstElement.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
            listImage.add(listImage.get(0));
            listImage.remove(0);
        }
    }

    public void draw(Graphics g) {
        for (ImageLand imageLand:listImage) {
            g.drawImage(imageLand.image,imageLand.posX, (int)GROUNDY - 20, null);
        }
    }

    private BufferedImage getImageLand() {
        int i = random.nextInt(10);
        switch (i) {
            case 0: return imageLand1;
            case 1: return imageLand3;
            default: return imageLand2;
        }
    }

    private static class ImageLand {
        int posX;
        BufferedImage image;
    }
}
