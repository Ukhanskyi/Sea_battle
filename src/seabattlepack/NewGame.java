package seabattlepack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.SwingConstants.CENTER;

public class NewGame extends JDialog {
    BattlePlace place;

    NewGame(JFrame parent){

        super(parent,"New Game",Dialog.ModalityType.APPLICATION_MODAL);
        setModal(true);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        place = new BattlePlace();
        BuildWindow();
    }

    public BattlePlace getPlace() {
        return place;
    }

    JPanel content;
    JPanel buttons_panel;
    JButton[][] buttons;
    JButton clear;
    JButton auto;

    public void BuildWindow() {
        buttons = new JButton[10][10];
        //GridBagLayout  bl = new GridBagLayout();
        buttons_panel = new JPanel();
        buttons_panel.setLayout(null);
        //Add window controls to the panel pl.

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j<10;j++){
                try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    buttons[i][j] = new JButton();
                    buttons[i][j].setForeground(Color.BLUE);
                    buttons[i][j].setFont(new Font("serif", 0, 2));
                    buttons[i][j].setBounds(j*30, i*30, 30, 30);
                    buttons[i][j].setIcon(new ImageIcon(img));
                    final int I = i;
                    final int J = j;
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            java.awt.Window ancestor = SwingUtilities.getWindowAncestor(NewGame.this);
                            PlaceDialog placeDialog = new PlaceDialog((JFrame)ancestor,place,I,J);
                            if(placeDialog.isPlaced()) {
                                place = placeDialog.getPlace();
                                place.debug_print(buttons);
                                if(place.isFull()) NewGame.this.setVisible(false);
                            }
                        }
                    });
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                buttons_panel.add(buttons[i][j]);
            }
        }
        buttons_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons_panel.setPreferredSize(new Dimension(300,300));
        buttons_panel.setMaximumSize(new Dimension(300,300));
        buttons_panel.setMinimumSize(new Dimension(300,300));
        buttons_panel.setForeground(Color.BLUE);
        clear = new JButton("Очистити");
        auto = new JButton("Автоматично розставити");
        clear.setHorizontalAlignment(CENTER);
        auto.setHorizontalAlignment(CENTER);
        auto.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        clear.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                place.debug_print(buttons);
            }
        });
        auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bot bt = new Bot();
                place = bt.battlePlace();
                place.debug_print(buttons);
                dispose();
            }
        });
        content = new GameWindowPanel();
        content.setLayout(new BoxLayout(content,PAGE_AXIS));
        content.add(buttons_panel);
        content.add(clear);
        content.add(auto);
        setForeground(Color.BLUE);
        setContentPane(content);
        setSize(400,400);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void dispose() {
        if(place.isFull())
        super.dispose();
    }
}
