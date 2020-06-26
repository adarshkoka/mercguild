package game.missions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game.utils.MadLib;

public class MissionType {
	
	private static List<MissionType> allMissionTypes = new ArrayList<>();
	private static int missionTypeIdCount;
	
	private int id;
	private String shortName, displayName;
	private MadLib descriptionMadLibs;
	
	private Map<String, Integer> statInfluence;
	
	private List<Map<String, Integer>> enemyStats;
	private List<String> enemyPositions;
	
	private List<String> futureMissionTypes;
	private List<Integer> futureMissionTypeRatios;
	
	public MissionType(String shortName, String displayName) {
		this.shortName = shortName;
		this.displayName = displayName;
		descriptionMadLibs = new MadLib();
		statInfluence = new TreeMap<>();
		enemyStats = new ArrayList<>();
		enemyPositions = new ArrayList<>();
		futureMissionTypes = new ArrayList<>();
		futureMissionTypeRatios = new ArrayList<>();
		allMissionTypes.add(this);
		id = missionTypeIdCount++;
	}
	
	public MissionType addMadLibDescription(String... descriptions) {
		descriptionMadLibs.addTemplate(descriptions);
		return this;
	}
	
	public MissionType addStatInfluence(String statName, int influenceFactor) {
		statInfluence.put(statName, influenceFactor);
		return this;
	}
	
	public MissionType addEnemy(Map<String, Integer> enemyStat, String enemyPos) {
		enemyStats.add(enemyStat);
		enemyPositions.add(enemyPos);
		return this;
	}
	
	public MissionType addFutureMissionType(String missionShortName, int outputRatio) {
		futureMissionTypes.add(missionShortName);
		futureMissionTypeRatios.add(outputRatio);
		return this;
	}
	
	public String getShortName() {
		return shortName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public MadLib getDescription() {
		return descriptionMadLibs;
	}
}
