public class Ray {

	public static int numOfRays = ProjectedWalls.projectionPlane / ProjectedWalls.widthOfWall;
	public double angle = 0;
	public double distance = 0;
	public double wallHitX = 0;
	public double wallHitY = 0;
	
	public boolean isHorz = false;

	public Ray(double angle) {
		this.angle = angle;
	}

	public void setAngle(double a) {
		a = a % (Math.PI * 2);
		if (a < 0) {
			a += (Math.PI * 2);
		}
		this.angle = a;
	}

	public void tick() {

	}

	public void cast(Map m, Player p) {
		double xIntercept = 0, yIntercept = 0;
		double xStep = 0, yStep = 0;
		double pX = p.x + (p.size / 2);
		double pY = p.y + (p.size / 2);
		double wallHitX1, wallHitY1 = 0;
		double distance1 = 0;
		if (Math.sin(angle) < 0) {
			yIntercept = Math.floor(pY / m.size) * m.size - 1;
			yStep = -1 * m.size;
			xIntercept = pX + (yIntercept - pY + 1) / Math.tan(angle);
		}
		if (Math.sin(angle) > 0) {
			yIntercept = Math.floor(pY / m.size) * m.size + m.size;
			yStep = m.size;
			xIntercept = pX + (yIntercept - pY ) / Math.tan(angle);
		}
		xStep = m.size / Math.tan(angle);
		if (Math.cos(angle) < 0 && xStep > 0) {
			xStep *= -1;
		}
		if (Math.cos(angle) > 0 && xStep < 0) {
			xStep *= -1;
		}
		wallHitX1 = xIntercept;
		wallHitY1 = yIntercept;
		while (wallHitX1 > 0 && wallHitX1 < m.size * m.width && wallHitY1 > 0 && wallHitY1 < m.size * m.height) {
			if (m.map[(int) (wallHitX1 / m.size) + (int) (wallHitY1 / m.size) * m.width] == 1) {
				distance1 = Math.sqrt((pX - wallHitX1) * (pX - wallHitX1) + (pY - wallHitY1) * (pY - wallHitY1));
				
				break;
			} else {
				wallHitX1 += xStep;
				wallHitY1 += yStep;
			}
		}
		xIntercept = 0;
		yIntercept = 0;
		xStep = 0;
		yStep = 0;
		double wallHitX2, wallHitY2 = 0;
		double distance2 = 0;
		if (Math.cos(angle) < 0) {
			xIntercept = Math.floor(pX / m.size) * m.size - 1;
			xStep = -1 * m.size;
			yIntercept = pY + (xIntercept - pX + 1) * Math.tan(angle);
		}
		if (Math.cos(angle) > 0) {
			xIntercept = Math.floor(pX / m.size) * m.size + m.size;
			xStep = m.size;
			yIntercept = pY + (xIntercept - pX ) * Math.tan(angle);
		}
		yStep = m.size * Math.tan(angle);
		if (Math.sin(angle) < 0 && yStep > 0) {
			yStep *= -1;
		}
		if (Math.sin(angle) > 0 && yStep < 0) {
			yStep *= -1;
		}
		wallHitX2 = xIntercept;
		wallHitY2 = yIntercept;
		while (wallHitX2 > 0 && wallHitX2 < m.size * m.width && wallHitY2 > 0 && wallHitY2 < m.size * m.height) {
			if (m.map[(int) (wallHitX2 / m.size) + (int) (wallHitY2 / m.size) * m.width] == 1) {
				distance2 = Math.sqrt((pX - wallHitX2) * (pX - wallHitX2) + (pY - wallHitY2) * (pY - wallHitY2));
				break;
			} else {
				wallHitX2 += xStep;
				wallHitY2 += yStep;
			}
		}
		//dis1 = Math.round(distance1);
		//dis2 = distance2;
		if (Math.round(distance1) == Math.round(distance2)) {
			distance = distance1;
			wallHitX = wallHitX1;
			wallHitY = wallHitX2;
			
		}
		if (distance1 < distance2) {
			isHorz = true;
			distance = distance1;
			wallHitX = wallHitX1;
			wallHitY = wallHitY1;
			//System.out.println(wallHitX + " " + wallHitY);
			
		}
		if (distance1 > distance2) {
			isHorz = false;
			distance = distance2;
			wallHitX = wallHitX2;
			wallHitY = wallHitY2;
		}
		if (distance2 == 0) {
			distance = distance1;
			wallHitX = wallHitX1;
			wallHitY = wallHitY1;
			s = "dddd";
		}
		if (distance1 == 0) {
			distance = distance2;
			wallHitX = wallHitX2;
			wallHitY = wallHitY2;
			s = "ererer";
		}
		
		//wallHitX = wallHitX1;
		//wallHitY = wallHitY1;
		//distance = distance1;
	}
	String s = "ree";
	double dis1, dis2;
	public static void castAllRays(Map m, Player p, Ray[] r) {
		for (int i = 0; i < r.length; i++) {
			r[i] = null;
		}
		double angle = p.angle - p.fov / 2;
		for (int i = 0; i < Ray.numOfRays; i++) {
			r[i] = new Ray(angle);
			r[i].cast(m, p);
			angle += p.fov / Ray.numOfRays;
		}
		//System.out.println(r[1].wallHitY);
		//System.out.println(r[1].dis1 + " " + r[1].dis2);
	}

}
