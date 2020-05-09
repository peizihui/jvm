package com.bjut.SocketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            ServerSocket server = new ServerSocket(17000);
            Socket socket = server.accept();

            // 获取输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 获取输出流
            pw = new PrintWriter(socket.getOutputStream(), true);

            String s = br.readLine(); // 获取接受的数据
            System.out.println(s);


            pw.println(s);            // 发送相同的数据给客户端

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
