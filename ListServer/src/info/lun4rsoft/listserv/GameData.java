package info.lun4rsoft.listserv;

import java.net.InetAddress;

public class GameData {
	private InetAddress address;
	private String name;
	private byte gamemode;
	private int maxplayers;
	private int players;
	private boolean passworded;
	private boolean broken; //Connection broken, remove this instance ASAP.
	
	public synchronized boolean isBroken() {
		return broken;
	}


	public synchronized void setBroken(boolean broken) {
		this.broken = broken;
	}
	
	public synchronized String getName() {
		return name;
	}


	public synchronized void setName(String name) {
		this.name = name;
	}


	public GameData() {
		super();
		
		gamemode = 0;
		maxplayers = 255;
		players = 255;
		
		passworded = false;
		broken = false;
	}
	
	
	public synchronized InetAddress getAddress() {
		return address;
	}
	public synchronized void setAddress(InetAddress address) {
		this.address = address;
	}
	public synchronized byte getGamemode() {
		return gamemode;
	}
	public synchronized void setGamemode(byte gamemode) {
		this.gamemode = gamemode;
	}
	public synchronized int getMaxplayers() {
		return maxplayers;
	}
	public synchronized void setMaxplayers(int maxplayers) {
		this.maxplayers = maxplayers;
	}
	public synchronized int getPlayers() {
		return players;
	}
	public synchronized void setPlayers(int players) {
		this.players = players;
	}
	public synchronized boolean isPassworded() {
		return passworded;
	}
	
	
}
