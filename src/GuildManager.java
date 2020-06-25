
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.ArrayType;

public class GuildManager {

	private List<Merc> mercs;
	private List<Mission> missions;
	
	private String name;	
	private int gold;
	private int respect;

	public GuildManager() {
		missions = new ArrayList<>();
		mercs = new ArrayList<>();
		gold = 100;
		respect = 50;
	}
	
	public void addGold(int gold) {
		this.gold += gold;
	}

	public void addRespect(int respect) {
		this.respect += respect;
	}
	
	public int getRespect() {
		return respect;
	}

	public void setRespect(int respect) {
		this.respect = respect;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public List<Merc> getMercs() {
		return mercs;
	}
	
	public void addMerc(Merc merc) {
		mercs.add(merc);
	}
	
	public void setMercs(List<Merc> mercs) {
		this.mercs = mercs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	public List<Mission> getMissions() {
		return missions;
	}
	public void addMission(Mission mission) {
		missions.add(mission);
	}
	public List<Merc> getAllEnemyMercs() {
		List<Merc> enemies = new ArrayList<>();
		
		for(Mission mission : missions) {
			for(Merc enemy : mission.getEnemies()) {
				enemies.add(enemy);
			}
		}
		
		return enemies;
	}
}
