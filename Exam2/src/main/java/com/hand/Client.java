package com.hand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chenjin on 2018/7/20.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建客户端对象，并指明连接捏服务器端的主机和端口
        Socket client = new Socket("localhost",8766);
        //获取客户端的输入流对象
        Scanner in = new Scanner(client.getInputStream());
        String data = "";
        while (in.hasNextLine()){
            String line = in.nextLine();
            data = data +line;

        }
        System.out.println("data:");
        FileOutputStream os = new FileOutputStream(new File("Exam2/temp/SampleChapter1.pdf"));
        os.write(data.getBytes());

        os.close();
        in.close();
        client.close();
    }
}
