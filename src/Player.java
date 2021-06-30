import java.awt.event.KeyEvent;

public class Player {

	public double x = 100;
	public double y = 100;
	public int size = 8;
	public double speed = 1;
	public double currentSpeedX = speed;
	public double currentSpeedY = speed;
	public int direction = 0;
	public double speedOfRotation = Math.PI / 50;
	public double angle = Math.PI / 2 * 3;
	public double fov = Math.PI / 3;
	public int angleDeg = (int) Math.toDegrees(angle);
	public double speedOfRotationDeg = (int) Math.toDegrees(speedOfRotation);

	public Map map;

	public Player(Map map) {
		this.map = map;
	}

	public int setAngle(int a) {
		a = a % 360;

		if (a < 0) {
			a += (360);
		}
		return a;
	}

	public void checkHit() {
		// Hit point 8
		if (map.map[((int) (x - 1) / map.size) + (int) (y / map.size) * map.width] == 1) {
			if (Math.cos(angle) < 0) {
				if (direction == 1) {
					currentSpeedX = 0;
				}
				if (direction == -1) {
					currentSpeedX = speed;
				}
			}
			if (Math.cos(angle) > 0) {
				if (direction == 1) {
					currentSpeedX = speed;
				}
				if (direction == -1) {
					currentSpeedX = 0;
				}
			}
		}
		// Hit point 7
		if (map.map[((int) (x - 1) / map.size) + (int) ((y + size - 1) / map.size) * map.width] == 1) {
			if (Math.cos(angle) < 0) {
				if (direction == 1) {
					currentSpeedX = 0;
				}
				if (direction == -1) {
					currentSpeedX = speed;
				}
			}
			if (Math.cos(angle) > 0) {
				if (direction == 1) {
					currentSpeedX = speed;
				}
				if (direction == -1) {
					currentSpeedX = 0;
				}
			}
		}
		// Hit point 3
		if (map.map[((int) (x + size) / map.size) + (int) (y / map.size) * map.width] == 1) {
			if (Math.cos(angle) < 0) {
				if (direction == 1) {
					currentSpeedX = speed;
				}
				if (direction == -1) {
					currentSpeedX = 0;
				}
			}
			if (Math.cos(angle) > 0) {
				if (direction == 1) {
					currentSpeedX = 0;
				}
				if (direction == -1) {
					currentSpeedX = speed;
				}
			}
		}
		// Hit point 4
		if (map.map[((int) (x + size) / map.size) + (int) ((y + size - 1) / map.size) * map.width] == 1) {
			if (Math.cos(angle) < 0) {
				if (direction == 1) {
					currentSpeedX = speed;
				}
				if (direction == -1) {
					currentSpeedX = 0;
				}
			}
			if (Math.cos(angle) > 0) {
				if (direction == 1) {
					currentSpeedX = 0;
				}
				if (direction == -1) {
					currentSpeedX = speed;
				}
			}
		}
		// Hit point 1
		if (map.map[((int) (x) / map.size) + (int) ((y - 1) / map.size) * map.width] == 1) {
			if (Math.sin(angle) < 0) {
				if (direction == 1) {
					currentSpeedY = 0;
				}
				if (direction == -1) {
					currentSpeedY = speed;
				}
			}
			if (Math.sin(angle) > 0) {
				if (direction == 1) {
					currentSpeedY = speed;
				}
				if (direction == -1) {
					currentSpeedY = 0;
				}
			}
		}
		// Hit point 2
		if (map.map[((int) (x + size - 1) / map.size) + (int) ((y - 1) / map.size) * map.width] == 1) {
			if (Math.sin(angle) < 0) {
				if (direction == 1) {
					currentSpeedY = 0;
				}
				if (direction == -1) {
					currentSpeedY = speed;
				}
			}
			if (Math.sin(angle) > 0) {
				if (direction == 1) {
					currentSpeedY = speed;
				}
				if (direction == -1) {
					currentSpeedY = 0;
				}
			}
		}
		// Hit point 6
		if (map.map[((int) (x) / map.size) + (int) ((y + size) / map.size) * map.width] == 1) {

			if (Math.sin(angle) < 0) {
				if (direction == 1) {
					currentSpeedY = speed;
				}
				if (direction == -1) {
					currentSpeedY = 0;
				}
			}
			if (Math.sin(angle) > 0) {
				if (direction == 1) {
					currentSpeedY = 0;
				}
				if (direction == -1) {
					currentSpeedY = speed;
				}
			}
		}
		// Hit point 5
		if (map.map[((int) (x + size - 1) / map.size) + (int) ((y + size) / map.size) * map.width] == 1) {

			if (Math.sin(angle) < 0) {
				if (direction == 1) {
					currentSpeedY = speed;
				}
				if (direction == -1) {
					currentSpeedY = 0;
				}
			}
			if (Math.sin(angle) > 0) {
				if (direction == 1) {
					currentSpeedY = 0;
				}
				if (direction == -1) {
					currentSpeedY = speed;
				}
			}
		}
	}

	public void tick() {
		if (Keyboard.keys[KeyEvent.VK_A]) {
			angleDeg -= speedOfRotationDeg;
			angleDeg = setAngle(angleDeg);
			angle = Math.toRadians(angleDeg);
		}
		if (Keyboard.keys[KeyEvent.VK_D]) {
			angleDeg += speedOfRotationDeg;
			angleDeg = setAngle(angleDeg);
			angle = Math.toRadians(angleDeg);
		}
		direction = 0;
		if (Keyboard.keys[KeyEvent.VK_W]) {
			direction++;
		}
		if (Keyboard.keys[KeyEvent.VK_S]) {
			direction--;
		}
		currentSpeedX = speed;
		currentSpeedY = speed;
		checkHit();
		x += direction * currentSpeedX * Math.cos(angle);
		y += direction * currentSpeedY * Math.sin(angle);

	}

}
