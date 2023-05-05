public class CubeTetrominoe extends Tetrominoes {

    public CubeTetrominoe(TetrominoeTypeNames type, int x, int y, World world) {
        super(type, world);
        int alignToTop = 1;
        this.points[0] = new Point(x, y-alignToTop);
        this.points[1] = new Point(x+1, y-alignToTop);
        this.points[2] = new Point(x, y-1-alignToTop);
        this.points[3] = new Point(x+1, y-1-alignToTop);
    }
    @Override
    protected void rotate(Point[] pts) {
        }
    }
