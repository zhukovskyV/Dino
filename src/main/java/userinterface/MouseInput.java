package userinterface;

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
        /*
        public Rectangle playButton = new Rectangle(350, 150, 100, 50);
        public Rectangle quitButton = new Rectangle(350, 220, 100, 50);
         */

        //Play
        if (mx >= 350 && mx <= 570) {
            if (my >= 120 && my <= 170) {
//                GameScreen.State = GameScreen.STATE.GAME;
                GameScreen.gameState = GameScreen.GAME_PLAY_STATE;
            }
        }
        if (mx >= 350 && mx <= 570) {
            if (my >= 260 && my <= 310) {
                System.exit(1);
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
