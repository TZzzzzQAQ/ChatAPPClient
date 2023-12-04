package service;

import qqcommon.Message;
import qqcommon.MessageType;
import qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class UserClientService {
    public static Boolean authentication(String username, String password) throws IOException, ClassNotFoundException {
        //create socket using port 9999
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        //create stream to get object
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(new User(username, password));

        //create stream to input message
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Message message = (Message) objectInputStream.readObject();

        if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
            //create new thread to listen continuously
            ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
            clientConnectServerThread.start();
            //use hashmap to manage thread
            ManageClientConnectServerThread.addManageClientConnectServerThread(username, clientConnectServerThread);
            return true;
        }
        return false;
    }

    public static void getOnlineFriendList(String userName) {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(userName).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }

    public static void logOut(String userName) {
        Message message = new Message(userName, MessageType.MESSAGE_CLIENT_EXIT);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(userName).getSocket()
                            .getOutputStream());
            objectOutputStream.writeObject(message);
            System.out.println("正在退出登录，请稍后！");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }
}
