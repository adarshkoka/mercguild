package frame.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import frame.MainFrame;
import frame.animation.AnimationManager;
import frame.components.CButtonManager;
import frame.components.CTextButton;

public class OptionsScreen extends Screen {
	
	private boolean waitingForBtnPress = true;
	
	private CTextButton btnBack = new CTextButton("Go Back", new Point2D.Double(MainFrame.FRAME_WIDTH / 2.0, MainFrame.FRAME_HEIGHT / 2.0 - 80), 400, 100, 10)
			{
				@Override
				public void onRelease() {
					if (waitingForBtnPress) {
						waitingForBtnPress = false;
						AnimationManager.addAnimations(
							AnimationManager.getCircleTransition(Color.white, 300)
								.setOnEnd(() -> {
									ScreenManager.switchToScreen("MenuScreen");
								}).startAnimation()
						);
					}
				}
			}
			.setColors(Color.gray, Color.gray, Color.gray, Color.white)
			.setShadowWidth(5)
			.setFont("Arial", Font.BOLD);
	
	private CTextButton btnFullScreen = new CTextButton("", new Point2D.Double(MainFrame.FRAME_WIDTH / 2.0, MainFrame.FRAME_HEIGHT / 2.0 + 80), 800, 100, 10)
			{
				@Override
				public void onRelease() {
					if (waitingForBtnPress) {
						waitingForBtnPress = false;
						if (MainFrame.isFullScreen()) {
							MainFrame.setWindowed();
							super.setText("Switch To Full Screen");
						} else {
							MainFrame.setFullScreen();
							super.setText("Switch To Windowed");
						}
						waitingForBtnPress = true;
					}
				}
			}
			.setColors(Color.gray, Color.gray, Color.gray, Color.white)
			.setShadowWidth(5)
			.setFont("Arial", Font.BOLD);
	
	public OptionsScreen() {
		super("OptionsScreen");
	}
	
	@Override
	public void onScreenOpen() {
		waitingForBtnPress = true;
		CButtonManager.addButtons(btnBack, btnFullScreen);
		AnimationManager.clearAnimations();
		AnimationManager.addAnimations(AnimationManager.getFadeInTransition(Color.white, 500).startAnimation());
		btnFullScreen.setText(MainFrame.isFullScreen() ? "Switch To Windowed" : "Switch To Full Screen");
	}
	
	@Override
	public void onScreenClose() {
		CButtonManager.clearButtons();
		AnimationManager.clearAnimations();
	}
	
	@Override
	public void drawScreen(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
		CButtonManager.drawAllButtons(g);
		AnimationManager.drawAllAnimations(g);
	}
}
