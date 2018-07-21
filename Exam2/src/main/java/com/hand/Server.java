package com.hand;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenjin on 2018/7/20.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        String data = "";
        //首先服务端进行读取数据
        FileInputStream fs = new FileInputStream(new File("Exam1/temp/SampleChapter1.pdf"));
        int len = 0;
        byte[] buf = new byte[2048];
        while((len=fs.read(buf))!=-1){
            String str = new String(buf,0,len);
            System.out.println(str);
            data = data + str;
        }
        System.out.println("data:"+data);
        fs.close();

        ServerSocket server = new ServerSocket(8766);
        System.out.println("服务器已经准备就绪");
        //接收该服务器端的客户端对象
        boolean accept = true;
        while (accept){
            Socket client  = server.accept();
            System.out.println("当前链接过来的客户端是:"+client.getInetAddress());
            //获取客户端传出的输出流，给客户端输出数据
            PrintStream out = new PrintStream(client.getOutputStream());



            out.println(data);
            out.close();
        }
        server.close();
    }
}
