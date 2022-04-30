
import com.brioal.frames.BoradFrame;
import com.brioal.socket.Server;
import com.brioal.utool.Point;
import com.brioal.interfaces.Call;
import com.brioal.panels.BoradPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

//mimic the behavior of the server class
public class ServerTest {
    private static final int PORT = 9528;

    private OutputStream serverOut;
    private InputStream serverIn;
    private Semaphore lock = new Semaphore(0);

    @Test
    // testing the connection by asserting the strings matches
    // here we mimic the behavior of the server and the client class
    //instead of using a mock testing, during which we were not able to successfully connect to our server
    // we instead create a new connection and mimic the behavior of the server and client classes
    //we also made some changes to the original ip address of the localhost, it was 127.1.0.0 which makes it unable to play the game
    //we change it to string "localhost" and we were able to play
    public void testClientServer() throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(PORT);

        start(server);

        Socket client = new Socket("localhost", PORT);
        OutputStream clientOut = client.getOutputStream();
        InputStream clientIn = client.getInputStream();
        lock.acquire();
        printWrite(clientOut, "Connected to client printWrite");
        assertRead(serverIn, "Connected to client printWrite");
        printWrite(serverOut, "Connected to server printWrite");
        assertRead(clientIn, "Connected to server printWrite");
        client.close();
        server.close();
    }

    private void printWrite(OutputStream out, String str) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.print(str);
        pw.flush();
    }
    private void assertRead(InputStream in, String expected) throws IOException {
        byte[] buf = new byte[expected.length()];
        in.read(buf);
        System.out.println(in);
        System.out.println(expected);
        Assertions.assertEquals(expected, new String(buf));
    }
    //start the server
    private void start(ServerSocket server) {
        new Thread(() -> {
            try {
                Socket socket = server.accept();
                serverOut = socket.getOutputStream();
                serverIn = socket.getInputStream();
                lock.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
