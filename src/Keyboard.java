import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {

	public static boolean[] keys = new boolean[256];

	public Keyboard(GameEngine game) {
		game.canvas.addKeyListener(this);
		game.canvas.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void tick() {

	}

}
