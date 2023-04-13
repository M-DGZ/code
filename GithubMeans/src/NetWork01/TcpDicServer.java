package NetWork01;

import java.io.IOException;
import java.util.HashMap;

public class TcpDicServer extends TcpServer {

    private HashMap<String,String> map = new HashMap<>();

    public TcpDicServer(int port) throws IOException {
        super(port);
        map.put("dog","小狗");
        map.put("cat","猫");
        map.put("car","车");
        map.put("pig","猪");
        map.put("hello","你好");
    }

    @Override
    public String process(String request) {
        return map.getOrDefault(request,"该单词没有到词库中");
    }

    public static void main(String[] args) throws IOException {
        TcpDicServer tcpDicServer = new TcpDicServer(9090);
        tcpDicServer.start();
    }
}
