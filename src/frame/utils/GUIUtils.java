package frame.utils;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import frame.MainFrame;

public class GUIUtils {
	
	public static Point2D convMousePressToFramePoint(Point p) {
		if (p == null)
			return null;
		Container k = MainFrame.getMainFrame().getContentPane();
		return new Point2D.Double(1.0 * p.x * MainFrame.FRAME_WIDTH / k.getWidth(), 1.0 * p.y * MainFrame.FRAME_HEIGHT / k.getHeight());
	}
	
	public static void drawCenteredString(Graphics2D g, String text, Point2D center, Font font, Color textColor) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = (int) Math.round(center.getX() - metrics.stringWidth(text) / 2.0);
	    int y = (int) Math.round(center.getY() - metrics.getHeight() / 2.0 + metrics.getAscent());
	    g.setFont(font);
	    g.setColor(textColor);
	    g.drawString(text, x, y);
	}
	
	public static void drawBorderedCenteredString(Graphics2D g, String text, Point2D center, Font font, Color borderColor, Color textColor) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = (int) Math.round(center.getX() - metrics.stringWidth(text) / 2.0);
	    int y = (int) Math.round(center.getY() - metrics.getHeight() / 2.0 + metrics.getAscent());
	    g.setFont(font);
	    g.setColor(borderColor);
	    int borderDiff = 3;
	    g.drawString(text, x - borderDiff, y);
	    g.drawString(text, x, y - borderDiff);
	    g.drawString(text, x + borderDiff, y);
	    g.drawString(text, x, y + borderDiff);
	    g.setColor(textColor);
	    g.drawString(text, x, y);
	}
	
	public static Point2D addPoints(Point2D a, Point2D b) {
		return new Point2D.Double(a.getX() + b.getX(), a.getY() + b.getY());
	}
	public static Point2D subtractPoints(Point2D a, Point2D b) {
		return new Point2D.Double(a.getX() - b.getX(), a.getY() - b.getY());
	}
	public static Point2D mulPoint(Point2D a, double factor) {
		return new Point2D.Double(a.getX() * factor, a.getY() * factor);
	}
}
