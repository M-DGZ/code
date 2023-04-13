package NetWork01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private Socket socket = null;
    private String ServerIP = "127.0.0.1";
    private int ServerPort = 9090;

    public TcpClient() throws IOException {
        socket = new Socket(ServerIP, ServerPort);  //这个操作会和tcp服务器建立了连接  三次握手发生在这个阶段
    }

    /**
     * 客户端启动的主逻辑
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            PrintWriter printWriter = new PrintWriter(outputStream);
            Scanner scannerFromSocket = new Scanner(inputStream);
            while (true) {
                System.out.print("->:");
                //用户从输入请求，然后构造成数据报，然后发送，最后接收服务器的响应
                String request = scanner.next();
                printWriter.println(request);  //写网卡
                printWriter.flush();
                String response = scannerFromSocket.next();
                System.out.printf("req:%s resp:%s\n", request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpClient tcpClient = new TcpClient();
        tcpClient.start();
    }
}
