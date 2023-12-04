package service;

import java.util.HashMap;

/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class ManageClientConnectServerThread {
    private static HashMap<String, ClientConnectServerThread> hashMap = new HashMap<>();

    public static void addManageClientConnectServerThread(String userID, ClientConnectServerThread clientConnectServerThread) {
        hashMap.put(userID, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userID) {
        return hashMap.get(userID);
    }
}
