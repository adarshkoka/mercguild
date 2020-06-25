package frame.screen;

import java.awt.Graphics2D;

import frame.MainFrame;

public class ScreenManager {
	
	private static ScreenManager scrm;
	private Screen[] screens = { new MenuScreen(), new OptionsScreen() };
	private int currentScreen = 0;
	
	public ScreenManager() {
		scrm = this;
		screens[currentScreen].onScreenOpen();
	}
	
	public void drawScreen() {
		Graphics2D gr = MainFrame.getFrameGraphics();
		screens[currentScreen].drawScreen(gr);
	}
	
	
	public static void switchToScreen(String screenName) {
		if (scrm == null)
			return;
		for (int i = 0; i < scrm.screens.length; i++) {
			if (scrm.screens[i].getScreenName().equals(screenName)) {
				scrm.screens[scrm.currentScreen].onScreenClose();
				scrm.currentScreen = i;
				scrm.screens[scrm.currentScreen].onScreenOpen();
			}
		}
	}
}

