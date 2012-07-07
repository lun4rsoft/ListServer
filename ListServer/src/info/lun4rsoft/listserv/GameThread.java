package info.lun4rsoft.listserv;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class GameThread extends Thread {
	
	private GameData gamedata;
	private Socket sock;
	
	private String name;
	private String host;
	private int port;
	private int players;
	private int maxplayers;
	
	
	public GameThread(GameData data, Socket s)
	{
		this.gamedata = data;
		this.sock = s;
	}
	
	public synchronized void updateData()
	{
		gamedata.setName(name);
		gamedata.setPlayers(players);
		gamedata.setMaxplayers(maxplayers);
	}
	
	public void run()
	{
		InputStream input = null;
		
		try {
			input = sock.getInputStream();
		} catch (IOException e) {
			System.out.println("[GT.err] Error retrieveing inputstream from socket...");
			e.printStackTrace();
			gamedata.setBroken(true);
		}
		
		
		while(!gamedata.isBroken())
		{
			byte buffer[] = new byte[64]; //should be enough for now.
			
			//Attempt a read.
			try {
				input.read(buffer, 0, buffer.length);
				
				
			} catch (IOException e) {
				System.out.println("[GT.err] Error while waiting for next message...");	
				e.printStackTrace();
				gamedata.setBroken(true);
			}
		}
	}
}
