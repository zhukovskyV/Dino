package objectgame;



import util.Animation;
import util.Resourse;

import javax.sound.sampled.*;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainCharacter {
    private static final int RUN = 0;
    private static final int JUMP = 1;
    private static final int DOWN = 2;
    private static final int DEATH = 3;
    private static final int SCORE = 4;

    private int state = RUN;

    private float x = 0;
    private float y = 0;
    private float speedY = 0;

    private Animation characterRun;

    private BufferedImage downRunAnim;
    private BufferedImage jumping;
    private BufferedImage death;

    private Rectangle rect;

    private boolean isAlive = true;
    private boolean soundCount = false;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public MainCharacter() {
        characterRun = new Animation(45);
        characterRun.addFrame(Resourse.getResourceImage("data/character_move1.png"));
        characterRun.addFrame(Resourse.getResourceImage("data/character_move2.png"));
        characterRun.addFrame(Resourse.getResourceImage("data/character_move3.png"));
        characterRun.addFrame(Resourse.getResourceImage("data/character_move4.png"));
        characterRun.addFrame(Resourse.getResourceImage("data/character_move5.png"));
        characterRun.addFrame(Resourse.getResourceImage("data/character_move6.png"));
        jumping = Resourse.getResourceImage("data/jump.png");
        downRunAnim = Resourse.getResourceImage("data/down.png");
        death = Resourse.getResourceImage("data/death.png");

        rect = new Rectangle();

    }

    public void sound(int soundState) {
        String soundName = null;

        switch (soundState) {
            case JUMP:
                soundName = "data/JUMP2.WAV";
                break;
            case DOWN:
                soundName = "data/RUNAWAY.WAV";
                break;
            case DEATH:
                soundName = "data/UH-OH.WAV";
                break;
            case SCORE:
                soundName = "data/good_one.wav";
                break;
        }

        AudioInputStream audioInputStream = null;
        try {
            assert soundName != null;
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert clip != null;
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    public void update() {
        characterRun.update();
        if(y >= GROUNDY - characterRun.getFrame().getHeight()) {
            speedY = 0;
            y = GROUNDY - characterRun.getFrame().getHeight();
        }
        else {
            speedY += GRAVITY;
            y += speedY;
        }

        switch (state) {
            case RUN:
                rect.x = (int) x;
                rect.y = (int) y;
                rect.width = characterRun.getFrame().getWidth() - 20;
                rect.height = characterRun.getFrame().getHeight() - 20;
                break;
            case JUMP:
                rect.x = (int) x;
                rect.y = (int) y;
                rect.width = jumping.getWidth() - 20;
                rect.height = jumping.getHeight() - 20;
                break;
            case DOWN:
                rect.x = (int) x;
                rect.y = (int) y + 40;
                rect.width = downRunAnim.getWidth();
                rect.height = downRunAnim.getHeight();
                break;
        }
    }

    public  Rectangle getBound() {
        return rect;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        switch (state) {
            case RUN:
                g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
                break;
            case JUMP:
                g.drawImage(jumping, (int) x, (int) y, null);
                break;
            case DOWN:
                g.drawImage(downRunAnim, (int) x, (int) y + 10, null);
                break;
            case DEATH:
                g.drawImage(death, (int) x, (int) y , null);
                break;
        }
    }

    public void Down(Boolean isDown) {
        if (isDown && speedY == 0) {
            state = DOWN;
            if (soundCount) {
                sound(2);
                soundCount = false;
            }
        }
        else {
            state = RUN;
            soundCount = true;
        }
    }

    public void jump() {
        if (speedY == 0) {
           speedY = -6;
           y += speedY;
           state = JUMP;
           sound(1);
       }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setAlive (boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }
}
