import javax.swing.*;
import java.awt.*;

public class wikno extends JFrame {
    public wikno() {
        battlefield1 pan = new battlefield1();
        Container cont = getContentPane();
        cont.add(pan);

        setTitle("GAME \" Sea Battle \""); //заголовок вікна

        setBounds(0, 0, 900, 600);//границя вікна

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}





