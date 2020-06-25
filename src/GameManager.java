

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager implements Runnable {
	
	private static GameManager gm;
	private static GuildManager guild;
		
	private static boolean isGameManagerThreadRunning;
	
	public static GameManager startGameThread(GuildManager guildManager) {
		
		isGameManagerThreadRunning = true;
		guild = guildManager;
		
		Thread thread = new Thread(gm = new GameManager());
		thread.start();
		return gm;
	}
	
	public static GameManager getGM() {
		return gm;
	}
	
	@Override
	public void run() {
		Random random = new Random();

		while(isGameManagerThreadRunning) {
			List<Merc> allMercs = new ArrayList<Merc>();
			allMercs.addAll(guild.getAllEnemyMercs());
			allMercs.addAll(guild.getMercs());
			Merc.updateMercs(random, allMercs);
			
			try { Thread.sleep(30); } catch (Exception e) {} 
		}
	}
}
