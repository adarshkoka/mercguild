
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Merc {

	private String firstName;
	private String lastName;
	private String fullName;
	private int age;
	private int health;
	private String gender;
	
	private List<Weapon> weapons;
	private List<Spell> spells;
	
	private Map<String, Integer> stats; 

	public Merc() {
		
		stats = new HashMap<String, Integer>();
		stats.put("Attack", 500); // Attack
		stats.put("Leadership", 500); // Leadership
		stats.put("Speech", 500); // Speech
		stats.put("Stealth", 500); // Stealth
		stats.put("Intelligence", 500); // Intelligence
	}
	
	public Map<String, Integer> getStats() {
		return stats;
	}

	public void setStats(Map<String, Integer> stats) {
		this.stats = stats;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Weapon> getWeapons() {
		return weapons;
	}
	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}
	public List<Spell> getSpells() {
		return spells;
	}
	public void setSpells(List<Spell> spells) {
		this.spells = spells;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String toString() {
		
		String mercString = String.format("%s\n%s\n", getFullName(), getGender());
		
		for (Map.Entry<String,Integer> entry : stats.entrySet())  {
			mercString = String.format("%s%s", mercString, String.format("%s: %s\n", entry.getKey(), entry.getValue()));
    	} 
		
		return mercString;
	}
	
	
	
	
	private static long lastUpdateTime;
	public static void updateMercs(Random random, List<Merc> mercs) {
		if (System.currentTimeMillis() - lastUpdateTime < 5000)
			return;
		
		for (int i = 0; i < mercs.size(); i++) {
			Merc merc = mercs.get(i);
			Map<String,Integer> stats = merc.getStats();
			for (String key : stats.keySet()) {
				stats.put(key, stats.get(key) + random.nextInt(24) - 12);
			}
		}
		
		lastUpdateTime = System.currentTimeMillis();
	}
}
