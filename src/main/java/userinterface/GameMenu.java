package userinterface;

import java.awt.*;

public class GameMenu {
    public Rectangle playButton = new Rectangle(350, 150, 100, 50);
    public Rectangle quitButton = new Rectangle(350, 220, 100, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Peppo RUN!", 260, 100);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x + 19, playButton.y + 30);
        System.out.println();
        g2d.draw(playButton);
        g.drawString("QUIT", quitButton.x + 19, quitButton.y + 30);
        g2d.draw(quitButton);
    }
}
