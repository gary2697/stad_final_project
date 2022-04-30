package com.brioal.frames;

import com.brioal.interfaces.DiyViews;
import com.brioal.override_view.ImageButton;
import com.brioal.override_view.BlankPanel;
import com.brioal.override_view.SelectButton;
//import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class WelcomeFrame extends JFrame implements DiyViews, ActionListener {

    public static int port = 9000;

    public static String host = "localhost";
    private JButton btnStart , btnClose;
    private SelectButton selectService, selectClient, selectBlack, selectWhite;
    private JLabel lable_select_state, label_select_color;
    private JLabel BackLabel , label_message;
    private ImageIcon icon = new ImageIcon("drawable/welcome.png");
    private int select_state = BoradFrame.STATE_SERVICE;
    private int select_color = BoradFrame.STATE_BLACK;
    int mx = 0, my = 0, jfx = 0, jfy = 0;
    private BlankPanel blankPanel ;
    private int Frame_width = 1000 ;
    private int Frame_height = 500 ;

    public WelcomeFrame() {
        Font f = new Font("��Բ", Font.PLAIN, 15);
        UIManager.put("Label.font", f);
        UIManager.put("Label.foreground", Color.black);
        UIManager.put("Button.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        UIManager.put("List.font", f);
        UIManager.put("CheckBox.font", f);
        UIManager.put("RadioButton.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("TextArea.font", f);
        UIManager.put("EditorPane.font", f);
        UIManager.put("ScrollPane.font", f);
        UIManager.put("ToolTip.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("TableHeader.font", f);
        UIManager.put("Table.font", f);
        initViews();
        setViews();
        addViews();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - mx + jfx, e.getYOnScreen() - my + jfy);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getXOnScreen();
                my = e.getYOnScreen();
                jfx = getX();
                jfy = getY();
            }
        });
    }


    @Override
    public void initViews() {

        BackLabel = new JLabel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                icon.paintIcon(this, g, 0, 0);
            }

        };

        lable_select_state = new JLabel("Mode: ");
        label_select_color = new JLabel("Stone Color: ");
        //JButton
        selectService = new SelectButton("Server");
        selectClient = new SelectButton("Client");
        selectBlack = new SelectButton("Black");
        selectWhite = new SelectButton("White");
        btnStart = new ImageButton("Start");

        btnClose = new ImageButton("  Close  ");
        //JPanel
        blankPanel = new BlankPanel(180);
        if (select_state == 1) {
            selectService.setState("slectied");
            selectClient.setState("normal");
        } else {
            selectService.setState("normal");
            selectClient.setState("slectied");
        }
        if (select_color == 1) {
            selectBlack.setState("slectied");
            selectWhite.setState("normal");
        } else {
            selectBlack.setState("normal");
            selectWhite.setState("slectied");
        }
    }

    @Override
    public void setViews() {
//        JLabel
        lable_select_state.setBounds(169, 187, 100, 35);
        label_select_color.setBounds(169, 240, 100, 35);
//       JButton
        selectService.setBounds(260, 187, 100, 35);
        selectClient.setBounds(400, 187, 100, 35);

        selectBlack.setBounds(260, 240, 100, 35);
        selectWhite.setBounds(400, 240, 100, 35);
        btnStart.setBounds(200, 320, 100, 35);
        btnClose.setBounds(360,320,100,35);
        //JPanle
        blankPanel.setBounds(5, 5, Frame_width-5, Frame_height-5);

        selectService.addActionListener(this);
        selectClient.addActionListener(this);
        selectBlack.addActionListener(this);
        selectWhite.addActionListener(this);
        btnStart.addActionListener(this);

    }

    @Override
    public void addViews() {
        this.add(lable_select_state);
        this.add(label_select_color);
        this.add(selectService);
        this.add(selectClient);
        this.add(selectBlack);
        this.add(selectWhite);
        this.add(BackLabel);
        this.add(btnStart);
        this.add(btnClose);
//        this.add(blankPanel);
        this.add(BackLabel);
        setUndecorated(true);
        setSize(Frame_width, Frame_height);
        //AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectService) {
            selectClient.setState("normal");

            select_state = BoradFrame.STATE_SERVICE;
        }
        if (e.getSource() == selectClient) {
            selectService.setState("normal");

            select_state = BoradFrame.STATE_CLIENT;
        }
        if (e.getSource() == selectBlack) {

            selectWhite.setState("normal");
            select_color = BoradFrame.STATE_BLACK;
        }

        if (e.getSource() == selectWhite) {
            selectBlack.setState("normal");

            select_color = BoradFrame.STATE_WHITE;
        }

        if (e.getSource() == btnStart) {


            if (select_state == BoradFrame.STATE_SERVICE) {

                WaitClient frame = new WaitClient(WelcomeFrame.port, select_color);
                System.out.println("Server Mode, Black Stone");
                WelcomeFrame.this.dispose();
            } else {

                ConnectService clientFrame;
                if (select_color == BoradFrame.STATE_BLACK) {

                    clientFrame = new ConnectService(BoradFrame.STATE_BLACK);
                } else {
                    clientFrame = new ConnectService(BoradFrame.STATE_WHITE);
                }

                WelcomeFrame.this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        WelcomeFrame frame = new WelcomeFrame();
    }

}
