package game.mercs;

import java.util.Map;
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
}
