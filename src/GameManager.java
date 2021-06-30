import java.awt.Graphics2D;

public class GameManager {

	Map map = new Map();
	Player p = new Player(map);
	Ray[] ray = new Ray[Ray.numOfRays];
	ProjectedWalls pw = new ProjectedWalls();

	public void tick() {
		p.tick();

		Ray.castAllRays(map, p, ray);
		
	}

	public void render(Graphics2D g, Renderer r) {
		r.giveRendererGraphics(g);
		r.renderMap(map);
		boolean empty = true;
		for (Ray rayz : ray) {
		  if (rayz != null) {
		    empty = false;
		    break;
		  }
		}
		if(!empty) {
			r.renderProjectedWalls(pw, ray, p);
			for (int i = 0; i < ray.length; i++) {
				r.renderRay(map, p, ray[i]);
			}
		}
		//System.out.println(empty);
		r.renderPlayer(p);
	}

	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.createWindow();
		game.start();
	}

}
