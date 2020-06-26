package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import frame.animation.AnimationManager;
import frame.components.CButtonManager;
import frame.screen.ScreenManager;
import game.utils.FileUtils;
import input.Input;

/**
 * Represents the MainFrame window. 
 * Implements an ActionListener method that is called every time step
 * Implements a WindowListener to run events on Window Close
 * @author Shanth
 */
public class MainFrame extends JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 4721462583866967680L;

	private static final String FRAME_TITLE = "YEE";
	
	private static MainFrame fr;
	
	/** Input object to interact with user inputs
	 * @see Input
	 */
	private static Input input = new Input();
	
	/** Swing Timer to repeat ActionListener task every time step
	 * @see Timer
	 */
	private final Timer timer = new Timer(16, this);
	
	public static final int FRAME_WIDTH = 3200, FRAME_HEIGHT = 1800;
	private static BufferedImage frame = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	
	/** Screen Manager for this MainFrame. Allows for switching between Screens. 
	 * @see ScreenManager
	 */
	private ScreenManager scrm = new ScreenManager();
	
	/** JPanel Content Pane with altered paintComponent to display our own frames
	 * @see JPanel
	 */
	private JPanel contentPane = new JPanel() {
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics gr) {
			super.paintComponent(gr);
			Graphics2D g = (Graphics2D) gr;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			drawFrame(g, super.getWidth(), super.getHeight());
		}
	};
	
	public MainFrame() {
		super(FRAME_TITLE);
		setMinimumSize(new Dimension(1280, 720));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		contentPane.setBackground(Color.black);
		setContentPane(contentPane);
		
		contentPane.addKeyListener(input);
		contentPane.addMouseListener(input);
		contentPane.addMouseMotionListener(input);
		contentPane.setFocusable(true);
		contentPane.requestFocus();
		
		timer.start();
		setVisible(true);
		fr = this;
		
		addWindowListener(this);
		loadSettings();
	}
	
	/** Load any settings previously saved to settings.ini in the AppData directory
	 */
	private void loadSettings() {
		File settingsFile = new File(FileUtils.getAppDataPath().getAbsolutePath() + "/settings.ini");
		if (!settingsFile.isFile())
			return;
		try (Scanner in = new Scanner(settingsFile)) {
			String windowOrFullscreen = in.nextLine().trim().split(": *")[1];
			if (windowOrFullscreen.equals("fullscreen"))
				setFullScreen();
			else if (windowOrFullscreen.equals("windowed"))
				setWindowed();
		} catch (Exception e) {}
	}
	/** Save window settings to settings.ini in the AppData directory. Runs on Window Closing Event. 
	 */
	private void saveSettings() {
		File settingsFile = new File(FileUtils.getAppDataPath().getAbsolutePath() + "/settings.ini");
		try (PrintWriter pw = new PrintWriter(settingsFile)) {
			pw.println("window-mode: " + (isFullScreen ? "fullscreen" : "windowed"));
		} catch (Exception e) {}
	}
	
	public static MainFrame getMainFrame() {
		return fr;
	}
	
	private static boolean isFullScreen;
	public static boolean isFullScreen() {
		return isFullScreen;
	}
	public static void setFullScreen() {
		fr.dispose();
		fr.setUndecorated(true);
		fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
		isFullScreen = true;
	}
	public static void setWindowed() {
		fr.dispose();
		fr.setUndecorated(false);
		fr.setVisible(true);
		isFullScreen = false;
	}
	
	public void drawFrame(Graphics2D gr, int width, int height) {
		scrm.drawScreen();
		int frWidth = frame.getWidth(), frHeight = frame.getHeight();
		if (frWidth * height >= width * frHeight) {
			int newHeight = width * frHeight / frWidth;
			gr.drawImage(frame, 0, height / 2 - newHeight / 2, width, newHeight, null);
		} else {
			int newWidth = height * frWidth / frHeight;
			gr.drawImage(frame, width / 2 - newWidth / 2, 0, newWidth, height, null);
		}
	}
	
	/** @return A Graphics2D object for the BufferedImage frame
	 */
	public static Graphics2D getFrameGraphics() {
		Graphics2D g = frame.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		return g;
	}
	
	/** ActionListener task called every time step
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		scrm.getCurrentScreen().updateScreen();
		CButtonManager.updateButtonPresses();
		AnimationManager.updateAnimations();
		if (fr != null)
			fr.repaint();
	}
	
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		scrm.getCurrentScreen().onScreenClose();
		saveSettings();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
