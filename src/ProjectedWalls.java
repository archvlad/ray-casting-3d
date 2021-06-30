public class ProjectedWalls {

	public static int projectionPlane = 320;
	public static int widthOfWall = 1;

	public ProjectedWalls() {

	}

	public double calculateHeight(Ray ray, Player p) {
		double b = 160 / Math.tan(Math.toRadians(30));
		double correctD = ray.distance * Math.cos(ray.angle - p.angle);
		double height = ((b * 32) / correctD);
		//System.out.print(correctD + " ");
		return height;
	}
}
