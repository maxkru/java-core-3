package Task4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Task4Server {
    public static final int PORT_NUMBER = 8191;

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        ObjectInputStream objStream = null;

        try {
            server = new ServerSocket(PORT_NUMBER);
            socket = server.accept();

            objStream = new ObjectInputStream(socket.getInputStream());

            Cat cat = (Cat) objStream.readObject();
            System.out.println(cat);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objStream != null) {
                try {
                    objStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
