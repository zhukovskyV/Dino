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

    private int state = RUN;

    private float x = 0;
    private float y = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private float speedY = 0;
    private Animation characterRun;
    private BufferedImage downRunAnim;
    private BufferedImage jumping;
    private BufferedImage death;
    private Rectangle rect;
    private boolean isAlive = true;
    private boolean soundCount = false;
    private boolean score = true;


    public MainCharacter() {
        characterRun = new Animation(50);
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

    public void sound() {
        String soundName = null;

        switch (state) {
            case JUMP:
                soundName = "data/JUMP2.WAV";
                break;
            case DOWN:
                soundName = "data/RUNAWAY.WAV";
                break;
            case DEATH:
                soundName = "data/KAMIKAZE.WAV";
                break;
        }

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
//        if (soundCount) {
            clip.start();
//        }
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
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = characterRun.getFrame().getWidth() - 20;
        rect.height = characterRun.getFrame().getHeight();
    }
    public  Rectangle getBound() {
        return rect;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
//        System.out.println(state);

       // g.drawRect((int)x, (int)y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
        switch (state) {
            case RUN:
                g.drawRect((int)x, (int)y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
                g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
                break;
            case JUMP:
                g.drawImage(jumping, (int) x, (int) y, null);
                break;
            case DOWN:
                g.drawRect((int)x, (int)y + 40, downRunAnim.getWidth(), downRunAnim.getHeight());
                g.drawImage(downRunAnim, (int) x, (int) y + 20, null);
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
                sound();
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
           sound();
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
