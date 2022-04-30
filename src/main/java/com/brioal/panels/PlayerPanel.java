package com.brioal.panels;

import com.brioal.frames.BoradFrame;
import com.brioal.interfaces.DiyViews;
import com.brioal.socket.Client;
import com.brioal.socket.PlayerClient;
import com.brioal.utool.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class PlayerPanel extends JPanel implements DiyViews, MouseListener {
    private PlayerClient client = null; // Client����
    private java.util.List<com.brioal.utool.Point> points;
    private int[][] datas = new int[20][20];
    private int state_color  ;

    public PlayerPanel(String host, int port,  int state_color) {
        this.state_color = state_color;
        points = new ArrayList<>();
        initViews();
        setViews();
        addViews();
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[0].length; j++) {
                datas[i][j] = 0;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Start with Client");
        client = new PlayerClient(host, port, PlayerPanel.this, state_color);  // ����Client����
        new Thread() {
            @Override
            public void run() {
                client.Get();
            } // �������У�����ȡ����
        }.start();

    }


    public void addPoint(Point point) {
        if (datas[point.getX()][point.getY()] == 0) {
            points.add(point);  // ������ӵ�list��
            if (state_color == BoradFrame.STATE_BLACK) {
                datas[point.getX()][point.getY()] = 1;
            } else {
                datas[point.getX()][point.getY()] = -1;
            }
            updateUI();
            client.Put(point.getX() + "," + point.getY());
        }

    }

    public void receivePoint(Point point) {
        points.add(point);
        updateUI();
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setViews() {
        addMouseListener(this);
    }

    @Override
    public void addViews() {

    }

    //�����Ʒ���
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon board = new ImageIcon("drawable/board.png");
        g.drawImage(board.getImage(), 0, 0, 740, 740, null);

//        ImageIcon icon1 = new ImageIcon("drawable/white.png");
//        g.drawImage(icon1.getImage(),18 ,  18, 36, 36, null);
//        g.drawImage(icon1.getImage(), 19 * 36 -5, 19 *36-5, 36, 36, null);
//        g.drawImage(icon1.getImage(), 19 * 33+26 , 19 * 33+23, 36, 36, null);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Point point : points) {
            ImageIcon icon = null;
            if (point.getState() == com.brioal.utool.Point.STATE_WHITE) {
                icon = new ImageIcon("drawable/white.png");
            } else {
                icon = new ImageIcon("drawable/black.png");
            }

            g.drawImage(icon.getImage(),point.getX()*36-9, 710-point.getY()*36-5, 36, 36, null);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + "," + y);
        Point point = null;
        int X = (x)/ 36;
        int Y = (745-y) / 36;
        System.out.println(X+":"+Y);
        if (X > 0 && X < 20 && Y > 0 && Y < 20) {
            System.out.println("Send "+X + ":" + Y);
            if ((state_color == BoradFrame.STATE_WHITE)) {
                point = new Point(X, Y, Point.STATE_WHITE);
            } else {
                point = new Point(X, Y, Point.STATE_BLACK);
            }
            points.add(point);
            addPoint(point);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PlayerPanel playerPanel = new PlayerPanel(null, 0, 0);
        frame.setContentPane(playerPanel);
        frame.setSize(740, 740);
        frame.setVisible(true);
    }
}
