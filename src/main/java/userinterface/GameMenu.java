package userinterface;

import java.awt.*;

public class GameMenu {
    public Rectangle playButton = new Rectangle(350, 120, 100, 50);
    public Rectangle helpButton = new Rectangle(350, 190, 100, 50);
    public Rectangle quitButton = new Rectangle(350, 260, 100, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("standard 07_57", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Peppo RUN!", 265, 80);

        Font fnt1 = new Font("standard 07_57", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x + 19, playButton.y + 30);
        g.drawString("HELP", helpButton.x + 19, helpButton.y + 30);
        g.drawString("QUIT", quitButton.x + 19, quitButton.y + 30);
    }
}
