package ru.geekbrains.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    private static final Logger logger = LogManager.getLogger(Server.class.getSimpleName());

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            DatabaseHandler.connect();
            server = new ServerSocket(8189);
            logger.info("Server started and waiting for clients.");
            while (true) {
                socket = server.accept();
                logger.info("A client connected.");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            logger.error("An IOException occurred: " + e.getMessage());
        } finally {
            logger.info("Server is now stopping.");
            try {
                if(socket != null) {
                    logger.debug("Attempting to close socket.");
                    socket.close();
                    logger.debug("Socket closed as intended.");
                }
            } catch (IOException e) {
                logger.warn("Failed to close socket. " + e.getMessage());
                e.printStackTrace();
            }
            try {
                if(server != null) {
                    logger.debug("Attempting to close server.");
                    server.close();
                    logger.debug("Server closed as intended.");
                }
            } catch (IOException e) {
                logger.warn("Failed to close server. " + e.getMessage());
                e.printStackTrace();
            }
            DatabaseHandler.disconnect();
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                if (o.checkBlackList(from.getNick())) {
                    from.sendMsg("Пользователь '" + nickTo + "' добавил вас в черный список");
                } else {
                    o.sendMsg("from " + from.getNick() + ": " + msg);
                    from.sendMsg("to " + nickTo + ": " + msg);
                    return;
                }
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        logger.debug("Broadcasting clients list.");
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick()).append(" ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }

    public void subscribe(ClientHandler client) {
        logger.debug("Client subscribed.");
        clients.add(client);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        logger.debug("Client unsubscribed.");
        clients.remove(client);
        broadcastClientsList();
    }
}
