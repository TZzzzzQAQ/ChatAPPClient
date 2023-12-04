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
public class FileClientService {
    public void sendFileRequest(String senderID, String receiverID, String filePath) {
        Message message = new Message(senderID, receiverID, new Date().toString(), "", MessageType.MESSAGE_SEND_REQUEST, filePath);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(senderID).getSocket().getOutputStream()
            );
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }

    public static void receiveFile(String sender, String receiver, String fileDesPath, String fileSourcePath) {
        Message message = new Message(sender, receiver, new Date().toString(), fileDesPath, MessageType.MESSAGE_AGREE_REQUEST, fileSourcePath);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(sender).getSocket().getOutputStream()
            );
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }

    public static void rejectFile(String sender, String receiver, int choose) {
        Message message = new Message(sender, receiver, new Date().toString(), choose + "", MessageType.MESSAGE_REJECT_REQUEST);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(sender).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }

    public static void sendFile(String senderID, String receiverID, String desPath, byte[] fileContent, int length) {
        Message message = new Message(senderID, receiverID, new Date().toString(), desPath, MessageType.MESSAGE_FILE, fileContent, length);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(senderID).getSocket().getOutputStream()
            );
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("网络异常请稍后再试！");
        }
    }
}
