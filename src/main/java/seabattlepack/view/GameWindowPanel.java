package seabattlepack.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class GameWindowPanel extends JPanel {
    private transient Image background;

    public void paintComponent(Graphics gr) {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/img/background.png"));
        } catch (Exception ex) {
        }
        gr.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null);
    }
}