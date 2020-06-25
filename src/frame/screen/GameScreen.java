package frame.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import frame.MainFrame;
import frame.animation.AnimationManager;
import frame.components.CButtonManager;
import game.GameManager;

public class GameScreen extends Screen {
	
	private GameManager gm;
	
	public GameScreen() {
		super("GameScreen");
		gm = new GameManager();
	}
	
	public void updateScreen() {
		gm.update();
	}
	
	@Override
	public void onScreenOpen() {
		CButtonManager.addButtons();
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
