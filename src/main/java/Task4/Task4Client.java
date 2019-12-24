package Task4;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Task4Client {
    public static void main(String[] args) {
        Cat cat = new Cat("Васька", 5);

        final int PORT = 8191;
        final String IP_ADDRESS = "localhost";
        Socket socket = null;
        ObjectOutputStream objStream = null;

        try {
            socket = new Socket(IP_ADDRESS, PORT);
            objStream = new ObjectOutputStream(socket.getOutputStream());
            objStream.writeObject(cat);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(objStream != null) {
                try {
                    objStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
