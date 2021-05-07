package com.zhql.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务端
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);

            while (true) {
                Socket s = ss.accept();
                System.out.println("A Client Connected！");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos= new DataOutputStream(s.getOutputStream());
                String str;
                if((str = dis.readUTF()) != null) {
                    System.out.println(str);
                    System.out.println("From：" + s.getInetAddress() + "，port：" + s.getPort());
                }
                dos.writeUTF("Hello，" + s.getInetAddress() + ", port：" + s.getPort());
                dis.close();
                dos.close();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
