package ru.geekbrains.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private String login = null;

    private final static Logger logger = LogManager.getLogger(ClientHandler.class.getSimpleName());

    List<String> blackList;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        logger.debug("Received client message: " + str);
                        if (str.startsWith("/auth")) { // /auth login72 pass72
                            String[] tokens = str.split(" ");
                            String newNick = DatabaseHandler.getNickByLoginAndPass(tokens[1], tokens[2]);
                            if (newNick != null) {
                                if (!server.isNickBusy(newNick)) {
                                    login = tokens[1];
                                    logger.info("Auth-OK for login '" + login + "'.");
                                    sendMsg("/authok");
                                    nick = newNick;
                                    try {
                                        this.blackList = DatabaseHandler.fetchBlacklistForNick(nick);
                                    } catch (NoSuchUserInDBException e) {
                                        e.printStackTrace();
                                    }
                                    sendBlacklist();
                                    server.subscribe(this);
                                    break;
                                } else {
                                    logger.info("Auth-FAIL for login '" + tokens[1] + "' (nick already in use).");
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                logger.info("Auth-FAIL for login '" + tokens[1] + "' (wrong password).");
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }
                    while (true) {
                        String str = in.readUTF();
                        logger.debug("Msg from nick '" + getNick() + "': " + str);
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                logger.info("Received '/end' from nick '" + getNick() + "'.");
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w ")) { // /w nick3 lsdfhldf sdkfjhsdf wkerhwr
                                String[] tokens = str.split(" ", 3);
                                String m = str.substring(tokens[1].length() + 4);
                                server.sendPersonalMsg(this, tokens[1], tokens[2]);
                            }
                            if (str.startsWith("/blacklist ")) { // /blacklist nick3
                                String[] tokens = str.split(" ");
                                if (tokens.length > 1 && !tokens[1].isEmpty()) {
                                    String nickToBL = tokens[1];
                                    try {
                                        if (nickToBL.equals(this.nick)) {
                                            sendMsg("Нельзя добавить самого себя в черный список");
                                        } else {
                                            if (DatabaseHandler.toggleNickInClientsBlacklistInDatabase(this, nickToBL)) {
                                                blackList.add(nickToBL);
                                                sendMsg("Вы добавили пользователя \'" + tokens[1] + "\' в черный список");
                                                logger.info("User (login = " + login + ") added nick '" + nickToBL + "' to blacklist.");
                                                sendBlacklist();
                                            } else {
                                                blackList.remove(nickToBL);
                                                sendMsg("Вы удалили пользователя \'" + tokens[1] + "\' из черного списка");
                                                logger.info("User (login = " + login + ") removed nick '" + nickToBL + "' from blacklist.");
                                                sendBlacklist();
                                            }
                                        }
                                    } catch (NoSuchUserInDBException e) {
                                        sendMsg("Такого пользователя не существует");
                                    }
                                }
                            }
                            if (str.startsWith("/register")) {
                                String[] tokens = str.split(" ");
                                if (tokens.length > 3) {
                                    try {
                                        logger.info("Trying to create user (nick = " + tokens[3] + ", login = " + tokens[1] + ") (request by nick '" + getNick() + "').");
                                        DatabaseHandler.addUser(tokens[1], tokens[2], tokens[3]);
                                        sendMsg("Создан пользователь с ником \'" + tokens[3]  + "\', логином \'" + tokens[1] + "\' и паролем \'" + tokens[2] + '\'');
                                        logger.info("User (nick = " + tokens[3] + ", login = " + tokens[1] + ") successfully created (request by nick '" + getNick() + "').");
                                    } catch (BadCredentialException e) {
                                        sendMsg(e.getMessage());
                                        logger.info("User (nick = " + tokens[3] + ", login = " + tokens[1] + ") creation failed: bad credential - " + e.getCredential() + " (request by nick '" + getNick() + "').");
                                    }
                                }
                            }
                        } else {
                            server.broadcastMsg(this, nick + ": " + str);
                        }
                    }
                } catch (IOException e) {
                    logger.error("Error for nick '" + nick + "': " + e.getMessage());
                } finally {
                    logger.info("Disconnecting client, login '" + getLogin() + "', nick '" + getNick() + "'.");
                    server.unsubscribe(this);
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    public void sendMsg(String msg) {
        try {
            logger.debug("Sending msg to nick '" + getNick() + "': " + msg);
            out.writeUTF(msg);
        } catch (IOException e) {
            logger.error("Error while sending message '" + msg + "' to nick '" + getNick() + "': " + e.getMessage());
        }
    }

    public void sendBlacklist() {
        logger.debug("Sending blacklist to nick '" + getNick() + "'.");
        StringBuilder sb = new StringBuilder();
        sb.append("/blacklisted ");
        for (String o : blackList) {
            sb.append(o).append(" ");
        }
        sendMsg(sb.toString());
    }

    private String getLogin() {
        return login;
    }
}
