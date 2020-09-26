package objectgame;

import util.Resourse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {

        private BufferedImage image;
        private int posX, posY;
        private Rectangle rect;

        public Cactus() {
            image = Resourse.getResourceImage("data/cactus1.png");
            posX = 300;
            posY = 207;
            rect = new Rectangle();
        }

        public void update() {
            posX -= 4;
            rect.x = posX;
            rect.y = posY;
            rect.width = image.getWidth();
            rect.height = image.getHeight();
        }

        @Override
        public Rectangle getBound () {
            return rect;
        }
        @Override
        public void draw(Graphics g) {
            g.drawImage(image, posX, posY, null);
        }

        public void setX(int x) {
            posX = x;
        }

        public void setY(int y) {
            posY = y;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
        @Override
        public boolean isOutofScreen() {
            return (posX + image.getWidth() < 0);
        }
}

