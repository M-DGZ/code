package NetWork;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoServer {
    private ServerSocket serverSocket = null;
    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            //接收到客户端发来的连接
            Socket clientSocket = serverSocket.accept();
            //处理这个连接
            processConnection(clientSocket);
        }
    }
    private void processConnection(Socket clientSocket) {
        //读取请求
        //计算响应
        //返回客户端
        System.out.printf("[%s:%d] 客户端上线\n",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream); //使用Scanner来控制输入流  读取
            PrintWriter printWriter = new PrintWriter(outputStream);  //使用PrintWriter来进行网卡的写入，写回给客户端
            while (true) {
                if(!scanner.hasNext()) {
                    //说明客户端没有发来的流了
                    System.out.printf("[%s:%d] 客户端下线\n",clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    break;
                }
                //直接使用Scanner来读取请求
                String request = scanner.next();
                //根据请求计算响应
                String response = process(request);
                //把响应写回给客户端  写回的响应应该带上换行符
                printWriter.println(response);
                //打印日志
                System.out.printf("[%s:%d] req:%s,resp:%s\n",clientSocket.getInetAddress().toString()
                ,clientSocket.getPort(),request,response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据请求计算响应
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer tcpEchoServer = new TcpEchoServer(9090);
        tcpEchoServer.start();
    }
}
