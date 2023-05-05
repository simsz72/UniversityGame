public class ReverseZTetrominoe extends Tetrominoes {

    public ReverseZTetrominoe(TetrominoeTypeNames type, int x, int y, World world) {
        super(type, world);
        this.points[0] = new Point(x, y);
        this.points[1] = new Point(x, y-1);
        this.points[2] = new Point(x - 1, y-1);
        this.points[3] = new Point(x - 1, y-2);
    }

    //i desine x++, i kaire x--
    //i apacia y++, i virsu y--
    @Override
    protected void rotate(Point[] points) {
        switch (nowRotatedDegree) {
            case DEGREES0, DEGREES180 -> {
                points[0].x--;
                points[0].y--;
                points[2].x++;
                points[2].y--;
                points[3].x += 2;
            }
            case DEGREES90, DEGREES270 -> {
                points[0].x++;
                points[0].y++;
                points[2].x--;
                points[2].y++;
                points[3].x -= 2;
            }
        }
    }
}
