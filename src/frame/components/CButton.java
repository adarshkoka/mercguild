package frame.components;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract class CButton {
	
	protected int[] ids;
	
	protected Point2D center;
	protected Shape shape;
	
	protected boolean isPressed, mouseInside;
	
	protected CButton() {}
	
	/** @param ids Special data attributes stored inside the CButton that can be accessed later
	 */
	public void setIDs(int... ids) {
		this.ids = ids;
	}
	public int[] getIDs() {
		return ids;
	}
	
	/** @param p A point to be tested
	 * @return Whether the shape of this button contains the point
	 */
	public boolean contains(Point2D p) {
		Point2D p2 = new Point2D.Double(p.getX() - center.getX(), p.getY() - center.getY());
		return shape.contains(p2);
	}
	
	/** Method that can be overriden. Runs when the CButton is pressed. 
	 */
	public void onClick() {}
	
	/** Method that can be overriden. Runs when the CButton is released. 
	 */
	public void onRelease() {}
	
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	
	public void setMouseInside(boolean mouseInside) {
		this.mouseInside = mouseInside;
	}
	
	/** Method that can be overriden 
	 */
	public void drawButton(Graphics2D g) {}
}
