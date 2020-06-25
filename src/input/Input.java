package input;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import frame.utils.GUIUtils;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
	
	private static boolean[] keys = new boolean[512];
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	private static Point mousePos, lastMousePress, lastMouseRelease;
	private static long lastMousePressTime, lastMouseReleaseTime;
	private static boolean mousePressed;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos = e.getPoint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = e.getPoint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		lastMousePress = e.getPoint();
		lastMousePressTime = System.currentTimeMillis();
		mousePressed = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		lastMouseRelease = e.getPoint();
		lastMouseReleaseTime = System.currentTimeMillis();
		mousePressed = false;
	}
	
	public static boolean[] getKeys() {
		return keys;
	}
	public static Point2D getMousePos() {
		return GUIUtils.convMousePressToFramePoint(mousePos);
	}
	public static Point2D getLastMousePress() {
		return GUIUtils.convMousePressToFramePoint(lastMousePress);
	}
	public static Point2D getLastMouseRelease() {
		return GUIUtils.convMousePressToFramePoint(lastMouseRelease);
	}
	public static long getLastMousePressTime() {
		return lastMousePressTime;
	}
	public static long getLastMouseReleaseTime() {
		return lastMouseReleaseTime;
	}
	public static boolean isMousePressed() {
		return mousePressed;
	}
	
	public static void setLastMousePressTime(long lastMousePressTime) {
		Input.lastMousePressTime = lastMousePressTime;
	}
	public static void setLastMouseReleaseTime(long lastMouseReleaseTime) {
		Input.lastMouseReleaseTime = lastMouseReleaseTime;
	}
}
