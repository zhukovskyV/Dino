package world.ucode.objectgame;

import world.ucode.userinterface.GameScreen;

import java.awt.*;

public abstract class Enemy {
    public abstract Rectangle getBound();
    public abstract void draw(Graphics g);
    public abstract void update(GameScreen gameScreen);
    public abstract boolean isOutofScreen();
    public abstract boolean isOver();
    public abstract boolean isScoreGot();
    public abstract void setIsScoreGot(boolean isScoreGot);
}
