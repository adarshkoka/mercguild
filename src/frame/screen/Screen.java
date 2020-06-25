package frame.screen;

import java.awt.Graphics2D;

public abstract class Screen {
	
	private String screenName;
	
	public Screen(String screenName) {
		this.screenName = screenName;
	}
	
	public void onScreenOpen() {}
	
	public void onScreenClose() {}
	
	public void reset() {}
	
	public String getScreenName() {
		return screenName;
	}
	
	public abstract void drawScreen(Graphics2D gr);
}
