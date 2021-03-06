package frame.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import frame.MainFrame;
import frame.animation.AnimationManager;
import frame.components.CButtonManager;
import game.GameManager;
import input.Input;

public class GameScreen extends Screen {
	
	private static String gameLoadFile = "";
	public static void setGameLoadFile(String file) {
		gameLoadFile = file;
	}
	
	private GameManager gm;
	
	public GameScreen() {
		super("GameScreen");
	}
	
	public void updateScreen() {
		if (Input.getKeys()[KeyEvent.VK_ESCAPE])
			MainFrame.getMainFrame().dispatchEvent(new WindowEvent(MainFrame.getMainFrame(), WindowEvent.WINDOW_CLOSING));
		gm.update();
	}
	
	@Override
	public void onScreenOpen() {
		CButtonManager.addButtons();
		AnimationManager.clearAnimations();
		AnimationManager.addAnimations(AnimationManager.getFadeInTransition(Color.white, 500).startAnimation());
		if (gameLoadFile.equals(""))
			gm = new GameManager();
		else
			gm = GameManager.loadGameState(gameLoadFile);
	}
	
	@Override
	public void onScreenClose() {
		CButtonManager.clearButtons();
		AnimationManager.clearAnimations();
		gm.saveGameState();
	}
	
	@Override
	public void drawScreen(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
		CButtonManager.drawAllButtons(g);
		AnimationManager.drawAllAnimations(g);
	}
}
