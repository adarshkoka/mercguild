package game.utils;

import java.io.File;
import java.io.InputStream;

public class FileUtils {
	
	public static File getAppDataPath() {
		String workingDirectory;
		String OS = (System.getProperty("os.name")).toUpperCase();
		
		if (OS.contains("WIN"))
			workingDirectory = System.getenv("AppData");
		else
		    workingDirectory = System.getProperty("user.home") + "/Library/Application Support";
		File fl = new File(workingDirectory + "/testgame/");
		if (!fl.exists())
			fl.mkdirs();
		return fl;
	}
	
	
	private static FileUtils fu = new FileUtils();
	public static InputStream getResource(String path) {
//		System.out.println(fu.getClass().getClassLoader().getResourceAsStream("resources/" + path));
		return fu.getClass().getClassLoader().getResourceAsStream("resources/" + path);
	}
}
