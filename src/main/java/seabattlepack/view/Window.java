package seabattlepack.view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        BattlePlaceView pan = new BattlePlaceView(this);
        Container cont = getContentPane();
        cont.add(pan);
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon1.png"));
        setIconImage(icon.getImage());
        setTitle("GAME \" Sea Battle \""); //заголовок вікна
        setBounds(0, 0, 900, 600);//границя вікна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pan.playAudio();
        //music.timerMusic(5);
    }
    //превірка
}



