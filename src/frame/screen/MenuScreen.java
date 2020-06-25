package frame.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

import frame.MainFrame;
import frame.animation.AnimationManager;
import frame.components.CButtonManager;
import frame.components.CTextButton;

public class MenuScreen extends Screen {
	
	private boolean waitingForBtnPress = true;
	
	private CTextButton btnPlay = new CTextButton("Play", new Point2D.Double(MainFrame.FRAME_WIDTH / 2.0, MainFrame.FRAME_HEIGHT / 2.0), 400, 100, 10)
			{
				@Override
				public void onRelease() {
					if (waitingForBtnPress) {
						waitingForBtnPress = false;
						AnimationManager.addAnimations(
							AnimationManager.getCircleTransition(Color.white, 300)
								.setOnEnd(() -> {
									ScreenManager.switchToScreen("GameScreen");
								}).startAnimation()
						);
					}
				}
			}
			.setColors(Color.gray, Color.gray, Color.gray, Color.white)
			.setShadowWidth(5)
			.setFont("Arial", Font.BOLD);
	
	private CTextButton btnOptions = new CTextButton("Options", new Point2D.Double(MainFrame.FRAME_WIDTH / 2.0, MainFrame.FRAME_HEIGHT / 2.0 + 150), 400, 100, 10)
			{
				@Override
				public void onRelease() {
					if (waitingForBtnPress) {
						waitingForBtnPress = false;
						AnimationManager.addAnimations(
							AnimationManager.getCircleTransition(Color.white, 300)
								.setOnEnd(() -> {
									ScreenManager.switchToScreen("OptionsScreen");
								}).startAnimation()
						);
					}
				}
			}
			.setColors(Color.gray, Color.gray, Color.gray, Color.white)
			.setShadowWidth(5)
			.setFont("Arial", Font.BOLD);
	
	private CTextButton btnQuit = new CTextButton("Quit", new Point2D.Double(MainFrame.FRAME_WIDTH / 2.0, MainFrame.FRAME_HEIGHT / 2.0 + 300), 400, 100, 10)
			{
				@Override
				public void onRelease() {
					if (waitingForBtnPress) {
						waitingForBtnPress = false;
						AnimationManager.addAnimations(
							AnimationManager.getCircleTransition(Color.white, 300)
								.setOnEnd(() -> {
									try { Thread.sleep(500); } catch (Exception e) {}
									MainFrame.getMainFrame().dispatchEvent(new WindowEvent(MainFrame.getMainFrame(), WindowEvent.WINDOW_CLOSING));
								}).startAnimation()
						);
					}
				}
			}
			.setColors(Color.gray, Color.gray, Color.gray, Color.white)
			.setShadowWidth(5)
			.setFont("Arial", Font.BOLD);
	
	public MenuScreen() {
		super("MenuScreen");
	}
	
	@Override
	public void onScreenOpen() {
		waitingForBtnPress = true;
		CButtonManager.addButtons(btnPlay, btnOptions, btnQuit);
		AnimationManager.clearAnimations();
		AnimationManager.addAnimations(AnimationManager.getFadeInTransition(Color.white, 500).startAnimation());
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
