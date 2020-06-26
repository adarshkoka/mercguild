package game.mercs;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MercModifier {
	
	private String shortName, description;
	private boolean isActive;
	private Map<String, Integer> stats;
	
	public MercModifier(String shortName, String description) {
		this.shortName = shortName;
		this.description = description;
		stats = new TreeMap<>();
	}
	
	// statName=Attack
	// modifyFactor=5
	// This would represent a modifier of +5% for the attack stat
	public MercModifier addStatModifier(String statName, int modifyFactor) {
		stats.put(statName, modifyFactor);
		return this;
	}
	
	public String toString() {
		StringBuilder stb = new StringBuilder(String.format("%s - %s\n\t%s", shortName, description, isActive ? "Currently Active" : "Currently Inactive"));
		stats.forEach((k, v) -> {stb.append(String.format("\n\t%-15s %+d%%", k, v));});
		return stb.toString().trim();
	}
	
	public String saveData() {
		StringBuilder mmData = new StringBuilder(String.format("%s|%s|%s|%d", shortName, description, isActive ? "true" : "false", stats.size()));
		stats.forEach((k, v) -> {mmData.append("\n" + k + "|" + v);});
		return mmData.toString();
	}
	
	public static MercModifier loadData(Scanner in) {
		String[] data = in.nextLine().trim().split("\\|");
		MercModifier m = new MercModifier(data[0], data[1]);
		m.isActive = data[2].equals("true");
		int numStats = Integer.parseInt(data[3]);
		for (int i = 0; i < numStats; i++) {
			String[] lin = in.nextLine().trim().split("\\|");
			m.addStatModifier(lin[0], Integer.parseInt(lin[1]));
		}
		return m;
	}
}
