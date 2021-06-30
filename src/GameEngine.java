import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameEngine {

	public JFrame frame;
	public Canvas canvas;
	public BufferStrategy bs;
	public Graphics g;

	public static int width = 640;
	public static int height = 320;
	public static int scale = 1;
	public String title = "Ray Casting 3D Java";

	public GameManager gameManager;
	public Renderer renderer;
	public Keyboard keyboard;

	public GameEngine() {
		gameManager = new GameManager();
		renderer = new Renderer();
	}

	public void createWindow() {
		frame = new JFrame();
		canvas = new Canvas();
		Dimension size = new Dimension(width * scale, height * scale);
		canvas.setPreferredSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(canvas);
		frame.pack();
		frame.setTitle(title);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		keyboard = new Keyboard(this);
	}

	public void start() {
		long lastTime = System.nanoTime();
		long nowTime = 0;
		int deltaTime = 0;
		float timePerTick = 1000000000 / 60;
		int ticksCounter = 0;
		int framesCounter = 0;
		int stopwatch = 0;
		while (true) {
			nowTime = System.nanoTime();
			deltaTime += nowTime - lastTime;
			stopwatch += nowTime - lastTime;
			lastTime = nowTime;
			while (deltaTime >= timePerTick) {
				tick();
				deltaTime -= timePerTick;
				ticksCounter++;
			}
			render();
			framesCounter++;
			if (stopwatch >= 1000000000) {
				System.out.printf("FPS: %3d, UPS: %3d%n", framesCounter, ticksCounter);
				ticksCounter = 0;
				framesCounter = 0;
				stopwatch -= 1000000000;
			}
		}
	}

	public void tick() {
		gameManager.tick();
	}

	public void render() {
		bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// g2d.scale(scale, scale);
		g2d.clearRect(0, 0, width, height);
		gameManager.render(g2d, renderer);
		g.dispose();
		bs.show();
	}

}
