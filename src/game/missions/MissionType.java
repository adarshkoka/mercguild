package game.missions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MissionType {
	
	private String shortName, displayName, description;
	
	private Map<String, Integer> statInfluence;
	
	private List<Map<String, Integer>> enemyStats;
	private List<String> enemyPositions;
	
	private List<String> futureMissionTypes;
	private List<Integer> futureMissionTypeRatios;
	
	public MissionType(String shortName, String displayName, String description) {
		this.shortName = shortName;
		this.displayName = displayName;
		this.description = description;
		statInfluence = new TreeMap<>();
		enemyStats = new ArrayList<>();
		enemyPositions = new ArrayList<>();
		futureMissionTypes = new ArrayList<>();
		futureMissionTypeRatios = new ArrayList<>();
	}
	
	
}
