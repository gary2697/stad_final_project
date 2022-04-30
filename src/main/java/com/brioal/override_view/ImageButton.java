package com.brioal.override_view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by null on 15-11-9.
 */
public class ImageButton extends JButton {
    ImageIcon icon = new ImageIcon("drawable/buttonback.jpg");
    private String state = "normal";
    private String content = null;

    public ImageButton(String s) {
        content = s;
        this.setText(s);
        setMargin(new Insets(0,0,0,0));
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == "normal"){
                    state = "selectied";
                    repaint();
                }else{
                    state = "normal";
                    repaint();
                }

            }

//            @Override
//            public void mouseReleased(MouseEvent e) {
//                state = "normal";
//                repaint();
//            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(content,20, 20);
    }

    @Override
    protected void paintBorder(Graphics g) {

        if (state == "normal") {
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                    RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setColor(Color.WHITE);
//            g2d.setStroke(new BasicStroke(3));
            g.drawImage(icon.getImage(), 1,1,getWidth(),getHeight(), null);
//            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            g2d.setColor(new Color(79, 196, 122));
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        }


    }


}
