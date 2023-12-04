package view;

import MyTools.Tool;
import service.FileClientService;
import service.MessageClientService;
import service.UserClientService;

import java.io.IOException;

/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class QQView {
    public static boolean loopFirst = true;
    public static boolean loopSecond = false;
    public static String username = new String();
    public MessageClientService messageClientService = new MessageClientService();
    public FileClientService fileClientService = new FileClientService();

    public static void main(String[] args) {
        System.out.println("==========正在初始化系统==========");
        while (loopFirst) {
            // loopFirst controls menu
            new QQView().viewFirst();
        }
        System.out.println("==========正在退出系统，感谢您的使用！==========");
    }

    public void viewFirst() {
        System.out.println("==========欢迎登陆网络通讯系统（一级菜单）==========");
        System.out.println(username);
        System.out.println("\t\t1 登陆系统");
        System.out.println("\t\t2 退出系统");
        System.out.print("请输入您的选择：");
        int choose;
        try {
            choose = Tool.readIntKeyboard(1, 2);
        } catch (NumberFormatException e) {
            System.out.println("请输入数字！");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        String password;
        switch (choose) {
            case 1:
                System.out.print("请输入用户编号：");
                username = Tool.readStringKeyboard();
                System.out.print("请输入用户密码：");
                password = Tool.readStringKeyboard();
                try {
                    //use UserAuthentication to check username and password
                    loopSecond = UserClientService.authentication(username, password);
                } catch (IOException e) {
                    System.out.println("错误提示：网络错误，请稍后再试！");
                } catch (ClassNotFoundException e) {
                }
                if (!loopSecond) {
                    System.out.println("错误提示：请检查用户名和密码！");
                } else if (loopSecond) {
                    System.out.println("==========登录成功，欢迎用户" + username + "==========");
                    while (loopSecond) {
                        System.out.println("==========欢迎登录网络通讯系统（二级菜单），你好：" + username + "==========");
                        System.out.println("\t\t1 显示在线用户列表");
                        System.out.println("\t\t2 群发消息");
                        System.out.println("\t\t3 私聊消息");
                        System.out.println("\t\t4 发送文件");
                        System.out.println("\t\t5 返回登录界面");
                        System.out.print("请输入您的选择：");
                        String content;
                        String receiverID;
                        int choose2;
                        try {
                            choose2 = Tool.readIntKeyboard(1, 5);
                        } catch (NumberFormatException e) {
                            System.out.println("错误提示：请输入数字！");
                            return;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            return;
                        }
                        switch (choose2) {
                            case 1:
                                UserClientService.getOnlineFriendList(username);
                                break;
                            case 2:
                                UserClientService.getOnlineFriendList(username);
                                System.out.print("请输入想要发送的信息：");
                                content = Tool.readStringKeyboard();
                                messageClientService.sendMessageToAll(content, username);
                                System.out.println("信息已经成功发送给在线的所有人！");
                                break;
                            case 3:
                                UserClientService.getOnlineFriendList(username);
                                System.out.print("请输入想要发送信息的对象：");
                                receiverID = Tool.readStringKeyboard();
                                System.out.print("请输入想要发送的信息：");
                                content = Tool.readStringKeyboard();
                                messageClientService.sendMessageToOne(content, username, receiverID);
                                System.out.println("信息已发送请耐心等待对方回复！");
                                break;
                            case 4:
                                UserClientService.getOnlineFriendList(username);
                                System.out.print("请输入想要发送信息的对象：");
                                receiverID = Tool.readStringKeyboard();
                                System.out.print("请输入想要发送文件的路径：");
                                String filePath = Tool.readStringKeyboard();
                                fileClientService.sendFileRequest(username, receiverID, filePath);
                                System.out.println("已传递发送文件请求，等待对方接受！");
                                break;
                            case 5:
                                loopSecond = false;
                                break;
                        }
                    }
                }
                break;
            case 2:
                loopFirst = false;
                UserClientService.logOut(username);
                break;
        }
    }

}
