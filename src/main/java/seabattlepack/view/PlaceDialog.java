package seabattlepack.view;

import seabattlepack.logic.BattlePlace;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BoxLayout.PAGE_AXIS;

public class PlaceDialog extends JDialog {
    BattlePlace place;
    private int dI;
    private int dJ;
    boolean isPlaced = false;

    PlaceDialog(JFrame parent, BattlePlace place, int i, int j) {
        super(parent, "Place", Dialog.ModalityType.APPLICATION_MODAL);
        setModal(true);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(410, 0, 100, 150);//границя вікна
        this.place = place;
        this.dI = i;
        this.dJ = j;
        buildWindow();
    }

    JPanel panel;
    JRadioButton[] radioButton;
    JButton[] button;

    void buttonAction(boolean flag){
        for (int i = 0; i < 4; i++) {
            if (radioButton[i].isSelected()) {
                if (!place.manualPlace(dI, dJ, dI + ((flag)?i:0), dJ +((!flag)?i:0)))
                    JOptionPane.showMessageDialog(PlaceDialog.this,
                            "Invalid position, i: " + dI + " j: " + dJ +
                                    "\nSize: " + i +
                                    "\nDirection left",
                            "New game",
                            JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
        setVisible(false);
        dispose();
    }

    void buildWindow() {
        panel = new GameWindowPanel();
        panel.setLayout(new BoxLayout(panel, PAGE_AXIS));
        radioButton = new JRadioButton[4];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            radioButton[i] = new JRadioButton(Integer.toString(i + 1));
            radioButton[i].setActionCommand(Integer.toString(i + 1));
            radioButton[i].setSize(100, 10);
            group.add(radioButton[i]);
            panel.add(radioButton[i]);
        }
        radioButton[0].setSelected(true);
        button = new JButton[2];
        button[0] = new JButton("Вертикально");
        button[0].setSize(100, 10);
        button[1] = new JButton("Горизонтально");
        button[1].setSize(100, 10);
        button[0].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button[1].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(button[0]);
        panel.add(button[1]);
        button[0].addActionListener(e -> buttonAction(true));
        button[1].addActionListener(e -> buttonAction(false));
        setContentPane(panel);
        setSize(110, 300);
        setResizable(false);
        setVisible(true);
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public BattlePlace getPlace() {
        return place;
    }
}
