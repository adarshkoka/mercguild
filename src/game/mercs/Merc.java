package game.mercs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Merc {
	
	private static final String[] baseStatNames = {"Attack", "Leadership", "Speech", "Stealth", "Intelligence"};
	private static List<Merc> allGlobalMercs = new LinkedList<>();
	
	protected String firstName, lastName, gender;
	protected int age, health;
	
	protected List<MercModifier> modifiers;
	protected Map<String, Integer> stats;
	
	public Merc(String firstName, String lastName, String gender, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		stats = new TreeMap<>();
		modifiers = new LinkedList<>();
		allGlobalMercs.add(this);
	}
	
	public void addRandomBaseStats() {
		for (String st : baseStatNames) 
			stats.put(st, (int) (Math.random() * 400 + 300));
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
}
