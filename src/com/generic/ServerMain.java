package com.generic;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.ServerSocket;

@SuppressWarnings("serial")
public class ServerMain extends Applet implements KeyListener{

	/**
	 * @param args
	 */
   public static WorkflowManager manager ;
    
   public static ServerSocket serverSocket;
   
	MZServer server;
	MZServerSend serverSend;
   public static void main(String[] args) {
        // 5개의 쓰레드를 생성하는 서버를 생성한다.

	}
	
	public void init () {
		
	    try {
			serverSocket = new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addKeyListener(this);
		
		
    	manager = new WorkflowManager();
    	
    	manager.addWorkflow(new Workflow("W3G5MSC_WFL", 5));
    	manager.addWorkflow(new Workflow("W3GCL3P_WFL", 5));
    	manager.addWorkflow(new Workflow("W3G4SMS_WFL", 5));
    	manager.addWorkflow(new Workflow("W4GVLTE_WFL", 5));
    	manager.addWorkflow(new Workflow("W4GCLTE_WFL", 5));
    	manager.addWorkflow(new Workflow("W3GCGMM_WFL", 5));
    	
		
    	
    	
		
		server = new MZServer(5);
		serverSend = new MZServerSend(5);
		server.start();
		serverSend.start();
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("KePressed");
		
		serverSend.send(1);
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("KeyTyped");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("KeyPressed");
		
	}

}
