package MyTools;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class Tool {
    // get int from keyboard
    public static int readIntKeyboard(int startNum, int endNum) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int ans;
        try {
            ans = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw e;
        }
        if (ans < startNum || ans > endNum) {
            throw new Exception("错误提示：没有该菜单！");
        }
        return ans;
    }
    //get String from keyboard
    public static String readStringKeyboard(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
