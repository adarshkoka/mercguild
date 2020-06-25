
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuildSim {

	static Scanner in = new Scanner(System.in);
	static GuildManager gm;
	static Generator generator;
	
	public static void main(String[]args) throws FileNotFoundException{
		
		//System.out.println("What is the name of your Guild?");
		gm = new GuildManager();
		generator = new Generator();
		//gm.setName(in.nextLine());
		gm.setName("Evil Guild");
		System.out.println("Recruit 3 mercenaries for " + gm.getName() + "!\n");
		
		List<Merc> selectMercs = new ArrayList<>();
		List<Mission> startingMissions = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++) {
			Merc merc = generator.generateMerc();
			selectMercs.add(merc);
			System.out.println(i + ". " + merc);
		}
		for(int i = 1; i <= 3; i++) {
			Mission mission = generator.generateMission();
			gm.addMission(mission);
			startingMissions.add(mission);			
		}
		
		GameManager.startGameThread(gm);
		
		while(true) {
		    
			int selection1 = in.nextInt();
			int selection2 = in.nextInt();
			int selection3 = in.nextInt();
			
			if(selection1 == selection2 || selection2 == selection3 || selection1 == selection3) {
				System.out.println("You may not choose the same mercenary twice!");
			}
			else {
				gm.addMerc(selectMercs.get(selection1 - 1));
				gm.addMerc(selectMercs.get(selection2 - 1));
				gm.addMerc(selectMercs.get(selection3 - 1));
				break;
			}
		}
		
		boolean exit = false;
		while(true) {
			System.out.println("Gold: " + gm.getGold() + "\nRespect: " + gm.getRespect());
			System.out.println("What would you like to do?\n1. Missions\n2. Mercs\n3. Quit\n");
			int option = in.nextInt();
			switch(option) {
			case 1:
				viewMissions();
				break;
			case 2:
				editMercs();
				break;
			
			case 3:
				exit = true;
				break;
			}
			if(exit) {
				break;
			}
		}
		
		System.out.println("\nFarewell");
		in.close();
	}
	
	public static void viewMissions() {
		
		boolean back = false;
		while(true) {
			System.out.println("What would you like to do?\n1. View Missions\n2. Back\n");
			int option = in.nextInt();
			switch(option) {
			case 1:
				for(Mission mission : gm.getMissions()) {
					System.out.println(mission);
				}
			case 2:
				back = true;
				break;	
			}
			if(back) {
				break;
			}
		}
	}
	
	public static void editMercs() {
		while(true) {
			System.out.println("What would you like to do?\n1. View\n2. Recruit\n3. Train\n4. Fire\n5. Back\n");
			int option = in.nextInt();
			
			boolean back = false;
			switch(option) {
			case 1:
				for(Merc merc : gm.getMercs()) {
					System.out.println(merc);
				}
			case 2:
				break;
			case 5:
				back = true;
				break;
			}
			if(back) {
				break;
			}
		}
	}

}
