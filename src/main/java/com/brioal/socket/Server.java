package com.brioal.socket;


import com.brioal.frames.BoradFrame;
import com.brioal.utool.Point;
import com.brioal.interfaces.Call;
import com.brioal.panels.BoradPanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Call {
    private BoradPanel panel;
    ServerSocket serverSocket;
    private Socket socket = null;
    private int state_color ;

    public Server(int port, BoradPanel panel ,  int state_color) {
        this.panel = panel;
        this.state_color = state_color;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public vpodsetColor(int p)
//    {
//        this.state_color = p;
//    }



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

    public void addFirstPoint() {
        Put("10,10");
        panel.JustAdd();
    }

    @Override
    public void Put(String s) {
//        System.out.println("Service Put" + s);
        Send(s);
    }


    @Override
    public void Get() {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String str = null;
                try {
                    str = reader.readLine();
//                    System.out.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] data = str.split(",");
                int x = Integer.valueOf(data[0]);
                int y = Integer.valueOf(data[1]);
                Point point = null;
                if (state_color == BoradFrame.STATE_BLACK) {
                    point = new Point(x, y, Point.STATE_WHITE) ;
                    System.out.println("Service get White");
                } else {
                    point = new Point(x, y, Point.STATE_BLACK);
                    System.out.println("Service get Black");

                }
                panel.addPoint(point);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
