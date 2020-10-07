package world.ucode.userinterface;

import java.awt.*;

public class HelpMenu {
    public void helpMenu(Graphics g) {
        Font fnt0 = new Font("standard 07_57", Font.BOLD, 30);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("Press Space to    ->   JUMP", 120, 100);
        g.drawString("Press Key-UP to ->   COVER", 120, 180);
        g.drawString("Press Escape for exit to the main menu", 80, 260);
    }
}
