package seabattlepack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameWindowPanel extends JPanel {
    private Image background;

    public void paintComponent(Graphics gr) {
        try {
            background = ImageIO.read(new File("img/background.png"));
        } catch (Exception ex) {
        }
        gr.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null);
    }
}
