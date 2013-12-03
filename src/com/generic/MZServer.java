package com.generic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MZServer implements Runnable {
    ServerSocket serverSocket;
    Thread[] threadArr;
 
 
    public MZServer(int num) {
       
            // 서버 소켓을 생성하여 7777번 포트와 바인딩.
        	
        	System.out.println("hello");
            serverSocket = ServerMain.serverSocket;
            System.out.println(getTime() + " 서버가 준비되었습니다.");

            threadArr = new Thread[num];
      
    }
 
    public void start() {
        for (int i = 0; i < threadArr.length; i++) {
            threadArr[i] = new Thread(this);
            threadArr[i].start();
        }
    }
 
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(getTime() + " 가 연결 요청을 기다립니다.");
 
                Socket socket = serverSocket.accept();
                System.out.println(getTime() + " " + socket.getInetAddress()
                        + "로부터 연결요청이 들어왔습니다.");
                
                InputStream in = socket.getInputStream();
                DataInputStream dis = new DataInputStream(in);
                
                System.out.println("클라이언트로부터 받은 메세지 : " + dis.readUTF());
 
                OutputStream out = socket.getOutputStream();
                //DataOutputStream dos = new DataOutputStream(out);
                
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                
//                objectOut.writeObject(MZ.getAllWorkflow().get(0));
                objectOut.writeObject(ServerMain.manager);
                
                
 
               // dos.writeUTF("[Notice] Test Message1 from Server");
               // System.out.println(getTime() + " 데이터를 전송하였습니다.");
 
                //dos.close();
                objectOut.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    static String getTime() {
        String name = Thread.currentThread().getName();
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date()) + name;
    }


    
}
