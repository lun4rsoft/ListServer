package info.lun4rsoft.listserv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainServer {
	
	public static boolean exit;
	
	public static List<GameData> games;
	
	public static void main(String[] args) {
		exit = false;
		
		//Program loop.
		while (!exit)
		{
			//Lists
			games = Collections.synchronizedList(new ArrayList<GameData>());
			
			//==================
			//= Setup threads: =
			//==================
			
			//Game Listener
			System.out.println("[LS.inf] Starting Listenthread...");
			GameListenThread thr_glisten = new GameListenThread(games);
			thr_glisten.start();
			
			//Create a scanner object for reading commands.
			Scanner scan = new Scanner(System.in);
			while (true)
			{
				String comm = scan.nextLine();
				
				if (comm.equalsIgnoreCase("exit")||comm.equalsIgnoreCase("stop"))
				{
					exit = true;
					break;
				} else if (comm.equalsIgnoreCase("list")) {
					System.out.println("> "+games.size()+" games listed:");
				}
			}
			System.out.println("[LS.inf] Telling threads to stop...");
			
			//Stop threads.
			thr_glisten.stop_thread=true;
			
			
			//wait until threads stopped;
			while(thr_glisten.isAlive())
			{
				
			}
		}
		
		System.out.println("[LS.inf] Exiting.");
		
	}

}
