package game;

import java.util.ArrayList;
import java.util.Scanner;

import game.mercs.Merc;
import game.mercs.MercModifier;
import game.utils.FileUtils;

public class GameManager {
	
	public GameManager() {
		
	}
	
	private boolean initiated;
	public void init() {
		initiated = true;
		generateInitialMercs();
		System.out.println(Merc.getAllMercs().toString().replaceAll(", ", "\n").replaceAll("[\\[\\]]", ""));
	}
	private int numMaleNames, numFemaleNames;
	private ArrayList<String> firstNames, lastNames;
	private void generateInitialMercs() {
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
}
