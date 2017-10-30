package seabattlepack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.PAGE_AXIS;

public class PlaceDialog extends JDialog {
    BattlePlace place;
    int I,J;
    boolean isPlaced = false;
    PlaceDialog(JFrame parent, BattlePlace place, int i, int j){
        super(parent,"Place", Dialog.ModalityType.APPLICATION_MODAL);
        setModal(true);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(375, 0, 100, 150);//границя вікна

        this.place = place;
        this.I = i; this.J = j;
        BuildWindow();
    }

    JPanel panel;
    JRadioButton radioButton[];
    JButton button[];

    void BuildWindow(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,PAGE_AXIS));
        radioButton = new JRadioButton[4];
        ButtonGroup group = new ButtonGroup();
        for(int i=0;i<4;i++){
            radioButton[i] = new JRadioButton(Integer.toString(i+1));
            radioButton[i].setActionCommand(Integer.toString(i+1));
            radioButton[i].setSize(100,10);
            group.add(radioButton[i]);
            panel.add(radioButton[i]);
        }
        radioButton[0].setSelected(true);
        button = new JButton[2];
        button[0] = new JButton("Горизонтально");
        button[0].setSize(100,10);
        button[1] = new JButton("Вертикально");
        button[1].setSize(100,10);
        panel.add(button[0]); panel.add(button[1]);
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<4;i++){
                    if(radioButton[i].isSelected()) {
                        try {
                            place.SetSized(I,J,i+1,false);
                            isPlaced = true;
                        } catch (BattlePlace.InvalidPosition invalidPosition) {
                           break;
                        }
                    }
                }
                setVisible(false);
                dispose();
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<4;i++){
                    if(radioButton[i].isSelected()) {
                        try {
                            place.SetSized(I,J,i+1,true);
                            isPlaced = true;
                        } catch (BattlePlace.InvalidPosition invalidPosition) {
                            break;
                        }
                    }
                }
                setVisible(false);
                dispose();
            }
        });
        setContentPane(panel);
        setSize(110,300);
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
