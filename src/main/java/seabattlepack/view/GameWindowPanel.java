package seabattlepack.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameWindowPanel extends JPanel {
    private transient Image background;

    @Override
    public void paintComponent(Graphics gr) {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/img/background.png"));
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.SEVERE, "an exception was thrown", ex);
        }
        gr.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null);
    }
}