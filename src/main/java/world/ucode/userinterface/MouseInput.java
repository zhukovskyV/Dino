package world.ucode.userinterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx >= 350 && mx <= 570) {
            if (my >= 120 && my <= 170) {
                GameScreen.gameState = GameScreen.GAME_PLAY_STATE;
            }
        }
        if (mx >= 350 && mx <= 570) {
            if (my >= 260 && my <= 310) {
                System.exit(0);
            }
        }
        if (mx >= 350 && mx <= 570) {
            if (my >= 190 && my <= 240) {
                GameScreen.gameState = GameScreen.HELP_MENU;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
