
import java.util.List;

public class Mission {
	
	private String name;
	private List<Merc> enemies;
	private MissionType missionType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getInfluences() {
		return missionType.getInfluences();
	}

	public List<Merc> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Merc> enemy) {
		this.enemies = enemy;
	}

	public MissionType getMissionType() {
		return missionType;
	}

	public void setMissionType(MissionType missionType) {
		this.missionType = missionType;
	}
	
	public String toString() {
		String infs = "Influences:" + " A: " + getInfluences().get(0) + " L: " + getInfluences().get(1) + " Sp: " + getInfluences().get(2) + " St: " + getInfluences().get(3) + " I: " + getInfluences().get(4);
		for(int i=0;i<enemies.size();i++) {
			infs += "\n" + enemies.get(i);
		}
 		return String.format("Mission Type: %s\n%s\n", getMissionType().getName(), infs);
	}
}
