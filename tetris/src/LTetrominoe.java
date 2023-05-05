public class LTetrominoe extends Tetrominoes {

    public LTetrominoe(TetrominoeTypeNames type, int x, int y, World world) {
        super(type, world);
        int alignToTop = 2;
        for (int i = 0; i < numPoints - 1; i++) {
            this.points[i] = new Point(x, y-alignToTop+i);
        }
        this.points[3] = new Point(x+1, y-alignToTop+2);
    }
    //i desine x++, i kaire x--
    //i apacia y++, i virsu y--
    @Override
    protected void rotate(Point[] points) {
        switch (nowRotatedDegree) {
            case DEGREES0 -> {
                points[0].x++;
                points[0].y++;
                points[2].x--;
                points[2].y--;
                points[3].x -= 2;
            }
            case DEGREES90 -> {
                points[0].x++;
                points[0].y--;
                points[2].x--;
                points[2].y++;
                points[3].y += 2;
            }
            case DEGREES180 -> {
                points[0].x--;
                points[0].y--;
                points[2].x++;
                points[2].y++;
                points[3].x += 2;
            }
            case DEGREES270 -> {
                points[0].x--;
                points[0].y++;
                points[2].x++;
                points[2].y--;
                points[3].y -= 2;
            }
        }
    }
}
