package service;

import qqcommon.Message;
import qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;


/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class MessageClientService {
    public void sendMessageToOne(String content, String senderID, String receiverID) {
        Message message = new Message(senderID, receiverID, new Date().toString(), content, MessageType.MESSAGE_COMM_MES);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(senderID).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }

    }

    public void sendMessageToAll(String content, String senderID) {
        Message message = new Message(senderID, content, MessageType.MESSAGE_TO_ALL);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(message.getSender()).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }
}
