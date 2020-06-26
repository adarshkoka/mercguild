package game.mercs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Merc {
	
	private static final String[] baseStatNames = {"Attack", "Leadership", "Speech", "Stealth", "Intelligence"};
	private static int mercIDCounter;
	private static List<Merc> allGlobalMercs = new LinkedList<>();
	
	private String firstName, lastName, gender;
	private int id, age, health;
	
	private List<MercModifier> modifiers;
	private Map<String, Integer> stats;
	
	public Merc(String firstName, String lastName, String gender, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		stats = new TreeMap<>();
		modifiers = new LinkedList<>();
		allGlobalMercs.add(this);
		id = mercIDCounter++;
	}
	
	public void addRandomBaseStats() {
		for (String st : baseStatNames) 
			stats.put(st, (int) (Math.random() * 400 + 300));
	}
	
	public void addMercModifier(MercModifier mm) {
		modifiers.add(mm);
	}
	
	public String toString() {
		StringBuilder stb = new StringBuilder(String.format("%s %s (%s - %d years old): ", firstName, lastName, gender, age));
		stats.forEach((k, v) -> {stb.append("\n\t" + k + "=" + v); });
		stb.append(modifiers.toString().replaceAll("[\\[]|(, )", "\n").replace(']', ' '));
		return stb.toString().trim();
	}
	
	public static List<Merc> getAllMercs() {
		return allGlobalMercs;
	}
	
	public String saveData() {
		String mercData = String.format("%d|%s|%s|%s|%d|%d|%d|%d", id, firstName, lastName, gender, age, health, modifiers.size(), stats.size());
		for (MercModifier mm : modifiers)
			mercData += "\n" + mm.saveData();
		for (Map.Entry<String, Integer> entry : stats.entrySet())
			mercData += "\n" + entry.getKey() + "|" + entry.getValue();
		return mercData;
	}
	
	public static Merc loadOneMerc(Scanner in) {
		String[] data = in.nextLine().trim().split("\\|");
		Merc m = new Merc(data[1], data[2], data[3], Integer.parseInt(data[4]));
		m.id = Integer.parseInt(data[0]);
		m.health = Integer.parseInt(data[5]);
		int numModifiers = Integer.parseInt(data[6]);
		int numStats = Integer.parseInt(data[7]);
		for (int i = 0; i < numModifiers; i++)
			m.modifiers.add(MercModifier.loadData(in));
		for (int i = 0; i < numStats; i++) {
			String[] lin = in.nextLine().trim().split("\\|");
			m.stats.put(lin[0], Integer.parseInt(lin[1]));
		}
		return m;
	}
}
