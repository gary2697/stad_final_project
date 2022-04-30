package com.brioal.socket;

import com.brioal.interfaces.Call;
import com.brioal.panels.BoradPanel;
import com.brioal.panels.PlayerPanel;
import com.brioal.utool.Point;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class PlayerClient implements Call {
    private Socket socket = null;
    private PlayerPanel panel;
    private int state_color;

    public PlayerClient(String host, int port, PlayerPanel panel, int state_color) {
        this.panel = panel;
        this.state_color = state_color;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Send(String s) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(s);
        writer.flush();
    }


    public void Close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void Put(String s) {
//        System.out.println("Clent_Put" + s);
        Send(s);
    }


    @Override
    public void Get() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str != null) {
//                System.out.println("Client Get Messgae" + str);
                String[] data = str.split(",");
                System.out.println("PlayClient get "+str);
                int x = Integer.valueOf(data[0]);
                int y = Integer.valueOf(data[1]);

                Point point = null;
                if (state_color == 1) {

                    point = new Point(x, y, Point.STATE_WHITE);
                } else {
                    point = new Point(x, y, Point.STATE_BLACK);
                }
                panel.receivePoint(point);
                System.out.println("Client get "+point.getX()+":"+point.getY());
            }

        }

    }
}
