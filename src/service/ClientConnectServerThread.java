package service;

import MyTools.Tool;
import qqcommon.Message;
import qqcommon.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class ClientConnectServerThread extends Thread {
    public Socket socket;

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
                if (message.getMessageType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    String[] content = message.getContent().split(" ");
                    System.out.println("\n==========在线用户==========");
                    for (int i = 0; i < content.length; i++) {
                        System.out.println(content[i]);
                    }
                } else if (message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n==========收到新消息，请注意查收==========");
                    System.out.println("用户：" + message.getSender() + "对您说：\n" + message.getContent());
                    System.out.println("请根据菜单做出您的选择：");
                } else if (message.getMessageType().equals(MessageType.MESSAGE_TO_ALL)) {
                    System.out.println("\n==========收到群发消息，请注意查收==========");
                    System.out.println("用户：" + message.getSender() + "对所有人说：\n" + message.getContent());
                    System.out.println("请根据菜单做出您的选择：");
                } else if (message.getMessageType().equals(MessageType.MESSAGE_SEND_REQUEST)) {
                    System.out.println("\n==========收到文件接收请求，请注意查收==========");
                    System.out.println("用户：" + message.getSender() + "想要向您传输文件：\n" + message.getFilePath());
                    System.out.println("请选择：");
                    System.out.println("1 接受");
                    System.out.println("2 拒绝");
                    int choose = 0;
                    try {
                        choose = Tool.readIntKeyboard(1, 2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (choose == 1) {
                        System.out.println("请输入保存目录：");
                        String fileSavePath = Tool.readStringKeyboard();
                        FileClientService.receiveFile(message.getReceiver(), message.getSender(), fileSavePath, message.getFilePath());
                        System.out.println("已接受文件，请稍后在目标目录查看！");
                        System.out.println("请根据菜单做出您的选择：");
                    } else {
                        System.out.println("已选择拒绝\n请根据菜单做出您的选择：");
                        FileClientService.rejectFile(message.getReceiver(), message.getSender(), choose);
                    }
                } else if (message.getMessageType().equals(MessageType.MESSAGE_AGREE_REQUEST)) {
                    System.out.println("对方已同意接受文件！");
                    System.out.println("开始传输文件：");

                    FileInputStream fileInputStream = new FileInputStream(message.getFilePath());
                    long length = new File(message.getFilePath()).length();
                    byte[] fileContent = new byte[(int) length];
                    fileInputStream.read(fileContent);
                    fileContent.clone();

                    FileClientService.sendFile(message.getSender(), message.getReceiver(), message.getContent(), fileContent, (int) length);
                } else if (message.getMessageType().equals(MessageType.MESSAGE_FILE)) {
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getContent());
                    fileOutputStream.write(message.getFileContent());
                    fileOutputStream.close();
                }
            } catch (ClassNotFoundException e) {
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }
}
