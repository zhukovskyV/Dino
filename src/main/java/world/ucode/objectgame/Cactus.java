package world.ucode.objectgame;

import world.ucode.userinterface.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {

        private BufferedImage image;
        private int posX, posY;
        private Rectangle rect;
        private MainCharacter mainCharacter;
        private boolean isScoreGot = false;
        private GameScreen gameScreen;

        public Cactus(MainCharacter mainCharacter) {
            this.mainCharacter = mainCharacter;
            posX = 300;
            posY = 165;
            rect = new Rectangle();
        }

        public void update(GameScreen gameScreen) {
            if (gameScreen.getScore() > 100)
                posX -= 9;
//            if (gameScreen.getScore() > 200)
//                posX -= 2;
            else
                posX -= 6;
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
//            g.drawRect(posX, posY, image.getWidth(), image.getHeight());
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

        @Override
        public boolean isOver() {
            return (mainCharacter.getX() > posX);
        }

        @Override
        public boolean isScoreGot() {
            return isScoreGot;
        }

        @Override
        public void setIsScoreGot(boolean isScoreGot) {
            this.isScoreGot = isScoreGot;
        }
}

