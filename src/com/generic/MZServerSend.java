package com.generic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MZServerSend implements Runnable {
	ServerSocket serverSocket;
	Thread[] threadArr;
	private String sClientAddress;
	private MZServer server;

	public MZServerSend(int num) {

		// 서버 소켓을 생성하여 7777번 포트와 바인딩.

		System.out.println("hello");
		serverSocket = ServerMain.serverSocket;
		System.out.println(getTime() + " 서버가 준비되었습니다.");

		threadArr = new Thread[num];

	}

	public void start() {
	
//		for (int i = 0; i < threadArr.length; i++) {
//			threadArr[i] = new Thread(this);
//			threadArr[i].start();
//		}
	}
	
	
	public void send( int whatToSend){
		
		try {
			System.out.println("create Socket");
			Socket socket = new Socket("192.168.0.19", 9998);
			OutputStream out = socket.getOutputStream();
			
			DataOutputStream dos = new DataOutputStream(out);
			System.out.println("sendOutputStream");
			dos.writeUTF("1");
			System.out.println("sendOutputStream2");
			dos.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(getTime() + " 가 연결 요청을 기다립니다.");

				Socket socket = new Socket("192.168.0.19", 9998);

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
