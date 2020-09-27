package ex01.pyrmont;

import java.io.InputStream;

/**
 * @Description:
 * @Author: guojun
 * @Date: 2019/9/10
 */
public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        StringBuffer stringBuffer = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        }catch (Exception e) {
            System.out.println(e);
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            stringBuffer.append((char)buffer[j]);
        }
        System.out.println(stringBuffer.toString());
        uri = parseUri(stringBuffer.toString());
    }

    public String parseUri(String request) {
        int index1;
        int index2;

        index1 = request.indexOf(" ");
        if (index1 != -1) {
            index2 = request.indexOf(" ", index1 + 1);
            if (index2 > index1) {
                return request.substring(index1 + 1, index2);
            }
        }

        return null;
    }

    public String getUri() {
        return this.uri;
    }
}
