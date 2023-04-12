package NetWork;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private Socket socket = null;
    private static final String ServerIP = "127.0.0.1";
    private static final int ServerPort = 9090;
    public TcpEchoClient() throws IOException {
        socket = new Socket(ServerIP,ServerPort);
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            Scanner scannerFromSocket = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            while (true) {
                System.out.print("->:");
                String request = scanner.next();
                printWriter.println(request);
                printWriter.flush();
                String response = scannerFromSocket.next();
                System.out.printf("req %s: resp %s\n",request,response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }
    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient = new TcpEchoClient();
        tcpEchoClient.start();

    }
}
