package game;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import game.mercs.Merc;
import game.mercs.MercModifier;
import game.utils.FileUtils;

public class GameManager {
	
	private static GameManager gm;
	
	public GameManager() {
		gm = this;
	}
	
	private boolean initiated;
	public void init() {
		initiated = true;
		generateInitialMercs();
	}
	private int numMaleNames, numFemaleNames;
	private ArrayList<String> firstNames, lastNames;
	private void readInNames() {
		if (lastNames != null)
			return;
		firstNames = new ArrayList<>();
		lastNames = new ArrayList<>();
		Scanner in = new Scanner(FileUtils.getResource("male_first_names.txt"));
		while (in.hasNext())
			firstNames.add(in.next().trim());
		numMaleNames = firstNames.size();
		in.close();
		in = new Scanner(FileUtils.getResource("female_first_names.txt"));
		while (in.hasNext())
			firstNames.add(in.next().trim());
		numFemaleNames = firstNames.size() - numMaleNames;
		in.close();
		in = new Scanner(FileUtils.getResource("last_names.txt"));
		while (in.hasNext())
			lastNames.add(in.next().trim());
		in.close();
	}
	private void generateInitialMercs() {
		readInNames();
		for (int i = 0; i < 10; i++) {
			String gender = Math.random() <= .5 ? "Male" : "Female";
			String fn = firstNames.get(gender.equals("Male") ? 
					(int) (Math.random() * numMaleNames) : 
					(int) (Math.random() * numFemaleNames + numMaleNames));
			Merc m = new Merc(fn, lastNames.get((int) (Math.random() * numFemaleNames)), gender, (int) (Math.random() * 50 + 15));
			m.addRandomBaseStats();
			if (i == 0) {
				m.addMercModifier(new MercModifier("Sword", "A Mighty Sword").addStatModifier("Attack", 12));
			}
		}
	}
	
	public void update() {
		if (!initiated)
			init();
	}
	
	public void saveGameState() {
		String path = FileUtils.getAppDataPath().getAbsolutePath() + "/saves/";
		File savesFolder = new File(path);
		if (!savesFolder.exists())
			savesFolder.mkdirs();
		
		File savesFile = null;
		while (savesFile == null || savesFile.exists())
			savesFile = new File(String.format("%ssave%05d.sav", path, (int) (Math.random() * 100000)));
		try (PrintWriter pw = new PrintWriter(savesFile)) {
			pw.println("initiated=" + (initiated ? "t" : "f"));
			List<Merc> allMercs = Merc.getAllMercs();
			pw.println(allMercs.size() + "");
			for (int i = 0; i < allMercs.size(); i++)
				pw.println(allMercs.get(i).saveData());
		} catch (Exception e) {}
	}
	public static GameManager loadGameState(String saveFile) {
		GameManager gm = new GameManager();
		try (Scanner in = new Scanner(new File(FileUtils.getAppDataPath().getAbsolutePath() + "/saves/" + saveFile))) {
			gm.initiated = in.nextLine().trim().split("=")[1].equals("t");
			int numMercs = Integer.parseInt(in.nextLine().trim());
			for (int i = 0; i < numMercs; i++)
				Merc.loadOneMerc(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}
	
	public static GameManager getGM() {
		return gm;
	}
}
