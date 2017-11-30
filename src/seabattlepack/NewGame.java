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
        buildWindow();
    }

    public BattlePlace getPlace() {
        return place;
    }

    public int getMode(){
        return mode[0].isSelected()?1:2;
    }

    JPanel content;
    JPanel buttons_panel;
    JPanel model_panel;
    ButtonGroup mode_group;
    JButton[][] buttons;
    JButton clear;
    JButton auto;
    JRadioButton[] mode;

    public void buildWindow() {
        buttons = new JButton[10][10];
        //GridBag
        // Layout  bl = new GridBagLayout();
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
                                if(place.isFull()) NewGame.this.setVisible(false);
                            }
                            Utils.refreshBattlePlace(place,buttons);
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
        model_panel = new JPanel();
        mode = new JRadioButton[2];
        mode[0] = new JRadioButton("Простий бот");
        mode[0].setSelected(true);
        mode[0].setHorizontalAlignment(CENTER);
        mode[0].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        mode[0].setOpaque(false);
        mode[0].setBackground(new Color(0,0,0,0));
        mode[1] = new JRadioButton("Складний бот");
        mode[1].setSelected(false);
        mode[1].setHorizontalAlignment(CENTER);
        mode[1].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        mode[1].setOpaque(false);
        mode[1].setBackground(new Color(0,0,0,0));
        mode_group = new ButtonGroup();
        mode_group.add(mode[0]);
        mode_group.add(mode[1]);
        model_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        model_panel.setPreferredSize(new Dimension(200,60));
        model_panel.setMaximumSize(new Dimension(200,60));
        model_panel.setMinimumSize(new Dimension(200,60));
        model_panel.setOpaque(false);
        model_panel.setBackground(new Color(0,0,0,0));
        model_panel.add(mode[0]);
        model_panel.add(mode[1]);
        clear.setHorizontalAlignment(CENTER);
        auto.setHorizontalAlignment(CENTER);
        auto.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        clear.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        clear.addActionListener(e -> Utils.refreshBattlePlace(place,buttons));
        auto.addActionListener(e -> {
            place.AutoGen(() -> Utils.refreshBattlePlace(place,buttons));
            Utils.refreshBattlePlace(place,buttons);
            dispose();
        });
        content = new GameWindowPanel();
        content.setLayout(new BoxLayout(content,PAGE_AXIS));
        content.add(buttons_panel);
        content.add(clear);
        content.add(auto);
        content.add(mode[0]);
        content.add(mode[1]);
        content.add(model_panel);
        setForeground(Color.BLUE);
        setContentPane(content);
        setSize(400,450);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void dispose() {
        if(place.isFull())
            super.dispose();
    }
}
