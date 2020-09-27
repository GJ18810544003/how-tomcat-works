package ex02.pyrmont;

import java.io.IOException;

/**
 * @Description:
 * @Author: guojun
 * @Date: 2019/9/17
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        }catch (IOException e) {
            System.out.println(e);
        }
    }
}
