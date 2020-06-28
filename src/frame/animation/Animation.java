package frame.animation;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import frame.utils.GTimer;
import frame.utils.GUIUtils;

/**
 * Awesome Pawsome KeyFrame Animation Class. Specify the start and end configurations for an image.  
 * @author Shanth
 */
public class Animation {
	
	/** Uniform transform BufferedImage that is transformed by the Animation class
	 * @see BufferedImage
	 */
	private BufferedImage img;
	
	/** Start and ending positions for the center of the Animation. 
	 * @see Point2D 
	 */
	private Point2D startPos, endPos;
	/** Start and ending rotations for the Animation in radians. 
	 */
	private double startRot, endRot;
	/** Start and ending sizes for the Animation. 
	 */
	private int startWidth, startHeight, endWidth, endHeight;
	
	/** Awesome custom timer object that can be paused/resumed in the middle of the Animation. 
	 * @see GTimer
	 */
	private GTimer timer;
	
	/** Duration of the Animation. 
	 */
	private long duration;
	
	/** Flag for whether the onEnd method has been run. 
	 */
	private boolean ranOnEnd;
	
	/** Flag for whether this animation should be removed from the AnimationManager once it finishes.  
	 */
	private boolean removeOnEnd;
	
	/** Optional Runnable task to be run at the end of the Animation.
	 * @see Runnable 
	 */
	private Runnable onEnd;
	
	public Animation() {
		timer = new GTimer();
	}
	
	/** @param durationMillis The duration of the Animation in milliseconds
	 */
	public Animation(long durationMillis) {
		timer = new GTimer();
		this.duration = durationMillis;
	}
	
	public Animation setImg(BufferedImage img) {
		this.img = img;
		return this;
	}
	
	public Animation setPos(Point2D pos) {
		this.startPos = pos;
		this.endPos = pos;
		return this;
	}
	public Animation setStartPos(Point2D startPos) {
		this.startPos = startPos;
		return this;
	}
	public Animation setEndPos(Point2D endPos) {
		this.endPos = endPos;
		return this;
	}
	public Animation setRot(double rot) {
		this.startRot = rot;
		this.endRot = rot;
		return this;
	}
	public Animation setStartRot(double startRot) {
		this.startRot = startRot;
		return this;
	}
	public Animation setEndRot(double endRot) {
		this.endRot = endRot;
		return this;
	}
	
	public Animation setSize(int width, int height) {
		this.startWidth = width;
		this.endWidth = width;
		this.startHeight = height;
		this.endHeight = height;
		return this;
	}
	public Animation setWidth(int width) {
		this.startWidth = width;
		this.endWidth = width;
		return this;
	}
	public Animation setHeight(int height) {
		this.startHeight = height;
		this.endHeight = height;
		return this;
	}
	public Animation setStartSize(int startWidth, int startHeight) {
		this.startWidth = startWidth;
		this.startHeight = startHeight;
		return this;
	}
	public Animation setStartWidth(int startWidth) {
		this.startWidth = startWidth;
		return this;
	}
	public Animation setStartHeight(int startHeight) {
		this.startHeight = startHeight;
		return this;
	}
	public Animation setEndSize(int endWidth, int endHeight) {
		this.endWidth = endWidth;
		this.endHeight = endHeight;
		return this;
	}
	public Animation setEndWidth(int endWidth) {
		this.endWidth = endWidth;
		return this;
	}
	public Animation setEndHeight(int endHeight) {
		this.endHeight = endHeight;
		return this;
	}
	
	public Animation setDuration(long durationMillis) {
		this.duration = durationMillis;
		return this;
	}
	
	public Animation setOnEnd(Runnable onEnd) {
		this.onEnd = onEnd;
		return this;
	}
	public Animation setRemoveOnEnd(boolean removeOnEnd) {
		this.removeOnEnd = removeOnEnd;
		return this;
	}
	
	/** Task to be run at the end of an Animation. 
	 * Automatically runs the onEnd Runnable. 
	 * @see Runnable
	 */
	public void onEnd() {
		if (onEnd != null)
			onEnd.run();
	}
	
	public boolean hasStarted() {
		return timer.getStartTimeMillis() != 0;
	}
	
	/** Start the Animation
	 * @return Animation
	 */
	public Animation startAnimation() {
		timer.start();
		return this;
	}
	
	/** @return Whether the animation has completed its full duration
	 */
	public boolean isDone() {
		return timer.getElapsedTimeMillis() >= duration;
	}
	/** @return Whether the animation can be removed from the AnimationManager, removeOnEnd must be flagged
	 * @see AnimationManager
	 */
	public boolean canRemove() {
		return isDone() && ranOnEnd && removeOnEnd;
	}
	
	/** Updates the animation, mainly checking if the onEnd task needs to be run
	 */
	public void updateAnimation() {
		if (isDone() && !ranOnEnd) {
			onEnd();
			ranOnEnd = true;
		}
	}
	
	public Animation reset() {
		timer = new GTimer();
		return this;
	}
	
	public GTimer getGTimer() {
		return timer;
	}
	
	/** Find the exact linear 0 to 1 transformation multiplier 
	 * @return double
	 */
	public double getLinearStep() {
		return Math.min(1, 1.0 * timer.getElapsedTimeMillis() / duration);
	}
	
	/** Find the exact smooth 0 to 1 transformation multiplier 
	 * @return double
	 */
	public double getSmoothStep() {
		double x = getLinearStep();
		return x * x * (3 - 2 * x);
	}
	
	/** Uses the assigned transformations to draw the provided image, can be overriden
	 * @param gr Graphics2D object, is copied upon method call before animation translations
	 */
	public void drawAnim(Graphics2D gr) {
		Graphics2D g = (Graphics2D) gr.create();
		double mul = getSmoothStep();
		Point2D pos = GUIUtils.addPoints(startPos, GUIUtils.mulPoint(GUIUtils.subtractPoints(endPos, startPos), mul));
		double rot = (endRot - startRot) * mul + startRot;
		int width = (int) Math.round((endWidth - startWidth) * mul + startWidth);
		int height = (int) Math.round((endHeight - startHeight) * mul + startHeight);
		g.translate(pos.getX(), pos.getY());
		g.rotate(rot);
		g.drawImage(img, -width / 2, -height / 2, width, height, null);
	}
}
