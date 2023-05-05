public class TTetrominoe extends Tetrominoes {

    public TTetrominoe(TetrominoeTypeNames type, int x, int y, World world) {
        super(type, world);
        int alignToTop = 1;
        this.points[0] = new Point(x-1, y-alignToTop);
        this.points[1] = new Point(x, y-alignToTop);
        this.points[2] = new Point(x+1, y-alignToTop);
        this.points[3] = new Point(x, y-1-alignToTop);

    }
    //i desine x++, i kaire x--
    //i apacia y++, i virsu y--
    @Override
    protected void rotate(Point[] points) {
        switch (nowRotatedDegree) {
            case DEGREES0 -> {
                points[0].x++;
                points[0].y--;
                points[3].y += 2;
            }
            case DEGREES90 -> {
                points[2].x++;
                points[2].y++;
                points[3].y -= 2;
            }
            case DEGREES180 -> {
                points[2].x--;
                points[2].y--;
            }
            case DEGREES270 -> {
                points[0].x--;
                points[0].y++;
            }
        }
    }
}
