package frame.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import frame.utils.GUIUtils;

public class CTextButton extends CButton {
	
	private Font font;
	private String text;
	private Color c1 = Color.red.brighter(), c2 = Color.red.darker(), borderColor = Color.black, textColor = Color.white;
	private int shadowWidth = 15;
	
	public CTextButton(String text, Point2D center, int width, int height, int borderRadius) {
		this.text = text;
		this.center = center;
		shape = new RoundRectangle2D.Double(-width / 2.0, -height / 2.0, width, height, borderRadius, borderRadius);
		font = new Font("TimesRoman", Font.BOLD, (int) (shape.getBounds2D().getHeight() * .7));
	}
	
	public CTextButton setColors(Color innerGradient, Color outerGradient, Color borderColor, Color textColor) {
		c1 = innerGradient;
		c2 = outerGradient;
		this.borderColor = borderColor;
		this.textColor = textColor;
		return this;
	}
	
	public CTextButton setText(String text) {
		this.text = text;
		return this;
	}
	
	public CTextButton setFont(Font font) {
		this.font = font;
		return this;
	}
	
	public CTextButton setFont(String fontName, int fontKeys) {
		font = new Font(fontName, fontKeys, (int) (shape.getBounds2D().getHeight() * .7));
		return this;
	}
	
	public CTextButton setShadowWidth(int shadowWidth) {
		this.shadowWidth = shadowWidth;
		return this;
	}
	
	@Override
	public void drawButton(Graphics2D gr) {
		Graphics2D g = (Graphics2D) gr.create();
		g.translate(center.getX(), center.getY());
		if (!isPressed || !mouseInside) {
			g.setColor(Color.darkGray);
			g.fill(shape);
			g.translate(shadowWidth, -shadowWidth);
		}
		LinearGradientPaint gradientPaint = new LinearGradientPaint(center, new Point2D.Double(center.getX(), center.getY() - shape.getBounds2D().getHeight() / 2.0), 
				new float[] {0.0f, 1.0f}, new Color[] {mouseInside ? c1.brighter() : c1, mouseInside ? c2 : c2.darker()}, 
				MultipleGradientPaint.CycleMethod.REFLECT);
		g.setPaint(gradientPaint);
		g.fill(shape);
		g.setColor(borderColor);
		g.setStroke(new BasicStroke(5));
		g.draw(shape);
		
		GUIUtils.drawBorderedCenteredString(g, text, new Point2D.Double(), font, Color.black, textColor);
	}
}
