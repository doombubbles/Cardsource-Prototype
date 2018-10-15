/**
 * Screen.java
 *
 * A JPanel window that serves as a certain section of the Studiam program
 * Managed by the {@link MainFrame}
 *
 * @version 09/17/18
 */

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    protected String screenId = "";

    public Screen() {
        setPreferredSize(new Dimension(800, 600));
    }


    public String getScreenId() {
        return screenId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Screen) {
            return screenId.equals(((Screen) obj).getScreenId());
        } else return super.equals(obj);
    }

    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getRootPane().getSize();
        g.drawImage(new ImageIcon("img/background1.png").getImage(),0,0, size.width, size.height,
                this);
        g.fillRect(0, 0, size.width, size.height);
    }
    */
}
