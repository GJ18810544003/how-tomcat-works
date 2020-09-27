package ex02.pyrmont.first;

import ex02.pyrmont.Request;
import ex02.pyrmont.Response;
import ex02.pyrmont.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Author: guojun
 * @Date: 2019/9/16
 */
public class HttpServer1 {

    public static final String SHUT_DOWN = "SHUTDOWN";

    public static void main(String[] args) {
        HttpServer1 httpServer = new HttpServer1();
        httpServer.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(80, 1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e) {
            System.out.println(e);
        }

        boolean shutdown = false;
        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 servletProcessor1 = new ServletProcessor1();
                    servletProcessor1.process(request, response);
                }else {
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request, response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUT_DOWN);
            }catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
