package frame.animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import frame.MainFrame;

public class AnimationManager {	
	
	private static List<Animation> anims = new ArrayList<>();
	
	public static void addAnimations(Animation... anims) {
		for (Animation anim : anims)
			AnimationManager.anims.add(anim);
	}
	
	public static void clearAnimations() {
		anims.clear();
	}
	
	public static void removeAnimations(Animation... anims) {
		for (Animation anim : anims)
			AnimationManager.anims.remove(anim);
	}
	
	public static List<Animation> getAnimations() {
		return anims;
	}
	
	public static void drawAllAnimations(Graphics2D g) {
		for (int i = 0; i < anims.size(); i++)
			anims.get(i).drawAnim(g);
	}
	
	public static void updateAnimations() {
		for (int i = 0; i < anims.size(); i++) 
			anims.get(i).updateAnimation();
	}
	
	
	public static Animation getCircleTransition(final Color color, long durationMillis) {
		return new Animation(durationMillis) {
			public void drawAnim(Graphics2D gr) {
				final float radius = (float) (getSmoothStep() * (MainFrame.FRAME_WIDTH + MainFrame.FRAME_HEIGHT) / 2);
				if (radius == 0)
					return;
				final RadialGradientPaint p = new RadialGradientPaint(
						new Point2D.Double(MainFrame.FRAME_WIDTH / 2, MainFrame.FRAME_HEIGHT / 2), 
						radius, 
						new float[] {0f, .8f, 1.0f}, 
						new Color[] {color, color, new Color(0, 0, 0, 0)});
				gr.setPaint(p);
				gr.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
			}
		};
	}
	
	public static Animation getFadeInTransition(final Color fadeColor, long durationMillis) {
		return new Animation(durationMillis) {
			public void drawAnim(Graphics2D gr) {
				int alpha = (int) (255 * (1 - getSmoothStep()));
				gr.setColor(new Color(fadeColor.getRed(), fadeColor.getBlue(), fadeColor.getGreen(), alpha));
				gr.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
			}
		};
	}
}
