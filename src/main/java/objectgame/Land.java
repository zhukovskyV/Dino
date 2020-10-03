package objectgame;

import userinterface.GameScreen;
import util.Resourse;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static userinterface.GameScreen.GROUNDY;

public class Land {

    private List<ImageLand> listImage;
    private BufferedImage imageLand1, imageLand2, imageLand3;
    private Random random;

    public Land(GameScreen game) {
        random = new Random();
        imageLand1 = Resourse.getResourceImage("data/sand1.png");
        imageLand2 = Resourse.getResourceImage("data/sand2.png");
        imageLand3 = Resourse.getResourceImage("data/sand3.png");
        listImage = new ArrayList<ImageLand>();
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
//        g.drawImage(imageLand1,0, (int)GROUNDY - 15, null);
//        g.drawImage(imageLand2,0 + imageLand2.getWidth(), (int)GROUNDY - 15, null);
//        g.drawImage(imageLand3,0 + imageLand3.getWidth() * 2, (int)GROUNDY - 15, null);
//        g.drawImage(imageLand1,0 + imageLand1.getWidth() * 3, (int)GROUNDY - 15, null);
    }

    private BufferedImage getImageLand() {
        int i = random.nextInt(10);
        switch (i) {
            case 0: return imageLand1;
            case 1: return imageLand3;
            default: return imageLand2;
        }
    }

    private class ImageLand {
        int posX;
        BufferedImage image;
    }
}
