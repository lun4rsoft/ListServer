package info.lun4rsoft.listserv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class GameListenThread extends Thread {
	
	private List<GameData> games;
	public volatile boolean stop_thread;
	
	public GameListenThread(List<GameData> games)
	{
		this.games = games;
		stop_thread=false;
	}
	
	public void run()
	{
		ServerSocket sock;
		
		try {
			sock = new ServerSocket(10054);
			sock.setSoTimeout(600);
		} catch (IOException e) {
			System.out.println("[LT.err] Unable to open serverside socket...");
			e.printStackTrace();
			return;
		}
		
		while (!sock.isClosed() && !stop_thread)
		{
			try {
				//Listen for new connections.
				Socket s = sock.accept();
				
				//If the socket is null, skip it.
				if (s == null)
				{
					System.out.println("[LT.err] Invalid socket received - skipping it...");					
					continue;
				}
				System.out.println("[LT.inf] Incoming connection from \""+s.getInetAddress()+"\".");
				GameData dat = new GameData();
				GameThread thr_gt = new GameThread(dat,s);
				games.add(dat);
				System.out.println("[LT.inf] Added client.");
				thr_gt.run();
				System.out.println("[LT.inf] Launched thread for client.");
				
			} catch (Exception e) {
				if (e instanceof SocketTimeoutException)
				{
					//Just a timeout, proceed.
				} else {
					//Random error, nag about it.
					System.out.println("[LT.err] Error on waiting for new connections...");
					e.printStackTrace();
					return;
				}
			}
		}
		System.out.println("[LT.inf] Stopping thread...");

	}

}
