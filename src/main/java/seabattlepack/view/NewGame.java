package seabattlepack.view;

import seabattlepack.logic.BattlePlace;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.SwingConstants.CENTER;

public class NewGame extends JDialog {
    private BattlePlace place;
    private Utils utils = new Utils();
    private static final String EXEP = "an exception was thrown";

    NewGame(JFrame parent) {

        super(parent, "New Game", Dialog.ModalityType.APPLICATION_MODAL);
        setModal(true);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        place = new BattlePlace();
        buildWindow();
    }

    public BattlePlace getPlace() {
        return place;
    }

    public int getMode() {
        return mode[0].isSelected() ? 1 : 2;
    }

    JPanel content;
    JPanel buttonsPanel;
    JPanel modelPanel;
    ButtonGroup modeGroup;
    JButton[][] buttons;
    JButton clear;
    JButton auto;
    JRadioButton[] mode;

    public void buildWindow() {
        buttons = new JButton[10][10];
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);         //Add window controls to the panel pl.

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                    buttons[i][j] = new JButton();
                    buttons[i][j].setForeground(Color.BLUE);
                    buttons[i][j].setFont(new Font("serif", 0, 2));
                    buttons[i][j].setBounds(j * 30, i * 30, 30, 30);
                    buttons[i][j].setIcon(new ImageIcon(img));
                    final int dI = i;
                    final int dJ = j;
                    buttons[i][j].addActionListener(e -> {
                        java.awt.Window ancestor = SwingUtilities.getWindowAncestor(NewGame.this);
                        PlaceDialog placeDialog = new PlaceDialog((JFrame) ancestor, place, dI, dJ);
                        if (placeDialog.isPlaced()) {
                            place = placeDialog.getPlace();
                        }
                        if (place.isFull()) NewGame.this.setVisible(false);
                        utils.refreshBattlePlace(place, buttons);
                    });
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, EXEP, e);
                }
                buttonsPanel.add(buttons[i][j]);
            }
        }
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.setPreferredSize(new Dimension(300, 300));
        buttonsPanel.setMaximumSize(new Dimension(300, 300));
        buttonsPanel.setMinimumSize(new Dimension(300, 300));
        buttonsPanel.setForeground(Color.BLUE);
        clear = new JButton("Очистити");
        auto = new JButton("Автоматично розставити");
        modelPanel = new JPanel();
        mode = new JRadioButton[2];
        mode[0] = new JRadioButton("Простий бот");
        mode[0].setSelected(true);
        mode[0].setHorizontalAlignment(CENTER);
        mode[0].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        mode[0].setOpaque(false);
        mode[0].setBackground(new Color(0, 0, 0, 0));
        mode[1] = new JRadioButton("Складний бот");
        mode[1].setSelected(false);
        mode[1].setHorizontalAlignment(CENTER);
        mode[1].setAlignmentX(JComponent.CENTER_ALIGNMENT);
        mode[1].setOpaque(false);
        mode[1].setBackground(new Color(0, 0, 0, 0));
        modeGroup = new ButtonGroup();
        modeGroup.add(mode[0]);
        modeGroup.add(mode[1]);
        modelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        modelPanel.setPreferredSize(new Dimension(200, 60));
        modelPanel.setMaximumSize(new Dimension(200, 60));
        modelPanel.setMinimumSize(new Dimension(200, 60));
        modelPanel.setOpaque(false);
        modelPanel.setBackground(new Color(0, 0, 0, 0));
        modelPanel.add(mode[0]);
        modelPanel.add(mode[1]);
        clear.setHorizontalAlignment(CENTER);
        auto.setHorizontalAlignment(CENTER);
        auto.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        clear.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        clear.addActionListener(e -> utils.refreshBattlePlace(place, buttons));
        auto.addActionListener(e -> {
            place.autoGen();
            utils.refreshBattlePlace(place, buttons);
            dispose();
        });
        content = new GameWindowPanel();
        content.setLayout(new BoxLayout(content, PAGE_AXIS));
        content.add(buttonsPanel);
        content.add(clear);
        content.add(auto);
        content.add(mode[0]);
        content.add(mode[1]);
        content.add(modelPanel);
        setForeground(Color.BLUE);
        setContentPane(content);
        setSize(400, 450);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void dispose() {
        if (place.isFull())
            super.dispose();
    }
}