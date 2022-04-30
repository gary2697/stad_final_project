package com.brioal.socket;

import com.brioal.panels.PlayerPanel;
import com.brioal.utool.Point;
import com.brioal.interfaces.Call;
import com.brioal.panels.BoradPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client implements Call {
    private Socket socket = null;
    private BoradPanel panel;
    private int state_color;

    public Client(String host, int port, BoradPanel panel, int state_color) {
        this.panel = panel;
        this.state_color = state_color;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean TestConnection(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
        } catch (IOException e) {
            return false;
        }
        return true;
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
        BufferedReader reader = null; // 获取读入对象
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
                String[] data = str.split(",");
                int x = Integer.valueOf(data[0]);
                int y = Integer.valueOf(data[1]);

                Point point = null;
                if (state_color == 1) {
                    point = new Point(x, y, Point.STATE_WHITE);
                } else {
                    point = new Point(x, y, Point.STATE_BLACK);
                }
               panel.addPoint(point);
            }

        }

    }
}
