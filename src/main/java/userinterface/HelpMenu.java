package userinterface;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelpMenu {
    public void helpMenu(Graphics g) {
        Font fnt0 = new Font("standard 07_57", Font.BOLD, 30);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("Press Space to    ->   JUMP", 120, 100);
        g.drawString("Press Key-UP to ->   COVER", 120, 180);
    }
}
