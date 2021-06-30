import java.awt.Color;
import java.awt.Graphics2D;

public class Renderer {

	Graphics2D g;

	public void giveRendererGraphics(Graphics2D g) {
		this.g = g;
	}

	public void renderMap(Map map) {
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				g.setColor(Color.BLACK);
				if (map.map[x + y * map.width] == 1) {
					g.setColor(Color.BLUE);
				}
				g.fillRect(x * map.size, y * map.size, map.size, map.size);
			}
		}
	}

	public void renderPlayer(Player p) {
		g.setColor(Color.YELLOW);
		g.setColor(Color.RED);
		g.fillOval((int) p.x, (int) p.y, p.size, p.size);
		g.drawLine((int) p.x + (p.size / 2), (int) p.y + (p.size / 2),
				(int) p.x + (p.size / 2) + (int) (10 * Math.cos(p.angle)),
				(int) p.y + (p.size / 2) + (int) (10 * Math.sin(p.angle)));
		g.drawLine((int) p.x + (p.size / 2), (int) p.y + (p.size / 2),
				(int) p.x + (p.size / 2) + (int) (10 * Math.cos(p.angle)),
				(int) p.y + (p.size / 2) + (int) (10 * Math.sin(p.angle)));
	}

	public void renderRay(Map m, Player p, Ray r) {
		g.setColor(new Color(255, 255, 0));
		g.drawLine((int) p.x + (p.size / 2), (int) p.y + (p.size / 2), (int) (r.wallHitX), (int) (r.wallHitY));
	}

	public void renderProjectedWalls(ProjectedWalls pw, Ray[] r, Player p) {
		g.setColor(Color.BLACK);
		g.fillRect(320, 0, 320, 320);
		for (int i = 0; i < Ray.numOfRays; i++) {
			double colorOfWall = 35 / r[i].distance;
			if (colorOfWall > 1) {
				colorOfWall = 1;
			}
			g.setColor(new Color(0f, 0f, (float) (colorOfWall)));
			g.fillRect(i * ProjectedWalls.widthOfWall + 320, 160 - (int) pw.calculateHeight(r[i], p) / 2,
					ProjectedWalls.widthOfWall, (int) pw.calculateHeight(r[i], p));
		}
		g.setColor(Color.WHITE);
		g.fillRect(320, 0, 1, 320);
	}

}
