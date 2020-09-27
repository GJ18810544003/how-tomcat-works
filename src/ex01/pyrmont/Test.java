package ex01.pyrmont;

import java.io.File;

/**
 * @Description:
 * @Author: guojun
 * @Date: 2019/9/10
 */
public class Test {

    public static void main(String[] args) {
        File file = new File("E:\\index.html");
        if (file.exists()) {
            System.out.println("fsfdsf");
        }

    }
}
