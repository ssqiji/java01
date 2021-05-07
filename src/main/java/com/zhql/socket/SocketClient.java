package com.zhql.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Socket客户端
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 6666);

            DataOutputStream dos= new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Hello Server！");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println(dis.readUTF());

            dos.flush();
            dos.close();
            dis.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
