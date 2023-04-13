package NetWork01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public  class TcpServer {
    private ServerSocket serverSocket = null;
    public TcpServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }
    /**
     * 服务器启动的主逻辑
     */
    public void start() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("服务器启动");
        //接收客户端发来的连接
        while (true) {
            //通过clientSocket和客户端进行通信
            Socket clientSocket = serverSocket.accept(); //接收一个连接
            //此时三次握手已经完成
           executorService.submit(new Runnable() {  //使用线程池
               @Override
               public void run() {
                   try {
                       ProcessConnection(clientSocket);   //处理这个客户端发来的连接
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });

           /* Thread t = new Thread(()->{   //使用多个线程
                try {
                    ProcessConnection(clientSocket);   //处理这个客户端发来的连接
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();*/
        }
    }
    public void ProcessConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s: %d]客户端上线\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
        //通过clientSocket拿个一对stream对象 InputStream(读网卡)   OutputStream(写网卡)
        //try可以通过;的方式来写多个stream对象，在try代码块执行完成之后自动进行close
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            //进行封装
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            //进行处理数据的操作
            while (true) {
                if (!scanner.hasNext()) {
                    System.out.printf("[%s: %d]客户端下线", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    break;
                }
                String request = scanner.next();
               String response = process(request);
                printWriter.println(response);
                printWriter.flush();
                System.out.printf("[%s :%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }
    }
    public  String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = new TcpServer(9090);
        tcpServer.start();
    }
}
