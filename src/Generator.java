import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.text.Highlighter.Highlight;

public class Generator {
	
	static List<String> femaleFirstNames = new ArrayList<>();
	static List<String> maleFirstNames = new ArrayList<>();
	static List<String> lastNames = new ArrayList<>();
	static List<MissionType> missionTypes = new ArrayList<>();

	public Generator() throws FileNotFoundException {
		File femaleFirstNamesFile = new File("src/resources/female_first_names.txt");
		for (Scanner femaleFirstNameScan = new Scanner(femaleFirstNamesFile); femaleFirstNameScan.hasNext();) {
			femaleFirstNames.add(femaleFirstNameScan.nextLine());
		}
		File maleFirstNamesFile = new File("src/resources/male_first_names.txt");
		for (Scanner maleFirstNameScan = new Scanner(maleFirstNamesFile); maleFirstNameScan.hasNext();) {
			maleFirstNames.add(maleFirstNameScan.nextLine());
		}
		File lastNamesFile = new File("src/resources/last_names.txt");
		for (Scanner lastNamesScan = new Scanner(lastNamesFile); lastNamesScan.hasNext();) {
			lastNames.add(lastNamesScan.nextLine());
		}
		File missionTypesFile = new File("src/resources/mission_types.txt");
		for (Scanner missionTypeFileScan = new Scanner(missionTypesFile); missionTypeFileScan.hasNext();) {
			String [] line = missionTypeFileScan.nextLine().split(" ", 2);
			List<Integer> influences = Arrays.stream(line[1].split(" ")).map(Integer::parseInt).collect(Collectors.toList());
			
			MissionType missionType = new MissionType();
			missionType.setName(line[0]);
			missionType.setInfluences(influences);
			missionTypes.add(missionType);
		}
	}
	
	public Merc generateMerc() {
		Merc merc = new Merc();
		String firstName = "";
		String lastName = "";
		
		Random rand = new Random();
				
		int randomGenderNum = new Random().nextBoolean() ? 0 : 1;
		if(randomGenderNum == 0) {
			merc.setGender("Female");
			firstName = femaleFirstNames.get(rand.nextInt(femaleFirstNames.size()));
		}
		else {
			merc.setGender("Male");
			firstName = maleFirstNames.get(rand.nextInt(maleFirstNames.size()));
		}
		
		lastName = lastNames.get(rand.nextInt(lastNames.size()));
		
		merc.setFullName(String.format("%s %s", firstName, lastName));
		merc.setFirstName(firstName);
		merc.setLastName(lastName);
		
		return merc;
	}
	
	public Mission generateMission() {
		Mission mission = new Mission();
		
		Random rand = new Random();
		mission.setMissionType(missionTypes.get(rand.nextInt(missionTypes.size())));	
		
		List<Merc> enemies = new ArrayList<>();
		enemies.add(generateMerc());
		mission.setEnemies(enemies);
		
		return mission;
	}
	
 }
