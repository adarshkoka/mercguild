package frame.components;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import input.Input;

public class CButtonManager {
	
	private static List<CButton> buttons = new ArrayList<>();
	private static List<CButton> pressedButtons = new ArrayList<>();
	
	public static void addButtons(CButton... btns) {
		for (CButton btn : btns)
			buttons.add(btn);
	}
	
	public static void clearButtons() {
		buttons.clear();
		pressedButtons.clear();
	}
	
	public static void removeButtons(CButton... btns) {
		for (CButton btn : btns)
			buttons.remove(btn);
		for (CButton btn : btns)
			pressedButtons.remove(btn);
	}
	
	public static void removeByID(int id) {
		for (int i = buttons.size() - 1; i >= 0; i--) {
			CButton btn = buttons.get(i);
			int[] ids = btn.getIDs();
			if (ids == null)
				continue;
			boolean containsID = false;
			for (int k : ids)
				if (k == id) {
					containsID = true;
					break;
				}
			if (containsID) {
				pressedButtons.remove(buttons.remove(i));
			}
		}
	}
	
	public static List<CButton> getButtons() {
		return buttons;
	}
	
	public static List<CButton> getPressedButtons() {
		return pressedButtons;
	}
	
	public static void updateButtonPresses() {
		checkMouseInside();
		checkNewMousePress();
		checkNewMouseRelease();
	}
	
	private static void checkMouseInside() {
		Point2D mousePos = Input.getMousePos();
		for (int i = 0; i < buttons.size() && mousePos != null; i++) {
			CButton cb = buttons.get(i);
			cb.setMouseInside(cb.contains(mousePos));
		}
	}
	private static long lastMousePressUpdate;
	private static void checkNewMousePress() {
		long lastMousePressTime = Input.getLastMousePressTime();
		if (lastMousePressUpdate == lastMousePressTime)
			return;
		Point2D mousePress = Input.getLastMousePress();
		for (int i = 0; i < buttons.size() && mousePress != null; i++) {
			CButton cb = buttons.get(i);
			if (cb.contains(mousePress)) {
				pressedButtons.add(cb);
				cb.setPressed(true);
				cb.onClick();
			}
		}
		
		lastMousePressUpdate = lastMousePressTime;
	}
	private static long lastMouseReleaseUpdate;
	private static void checkNewMouseRelease() {
		long lastMouseReleaseTime = Input.getLastMouseReleaseTime();
		if (lastMouseReleaseUpdate == lastMouseReleaseTime)
			return;
		Point2D mouseRelease = Input.getLastMouseRelease();
		for (int i = 0; i < buttons.size() && mouseRelease != null; i++) {
			CButton cb = buttons.get(i);
			pressedButtons.remove(cb);
			cb.setPressed(false);
			if (cb.contains(mouseRelease))
				cb.onRelease();
		}
		
		lastMouseReleaseUpdate = lastMouseReleaseTime;
	}
	
	public static void drawAllButtons(Graphics2D g) {
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).drawButton(g);
	}
}
