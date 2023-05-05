public class Tetrominoes implements TetrominoeInterface {

    private TetrominoeTypeNames shapeType;
    private World world;
    protected Point[] points;
    protected TetrominoeRotations nowRotatedDegree;
    protected int numPoints = 4;
    public Tetrominoes(TetrominoeTypeNames shapeType, World world) {
        this.shapeType = shapeType;
        this.world = world;
        this.points = new Point[numPoints];
        this.nowRotatedDegree = TetrominoeRotations.DEGREES0;
    }

    public Tetrominoes createTetrominoeType() {
        int xSpawnPosition = 10;
        int ySpawnPosition = 3;
        return switch (shapeType) {
            case ITETROMINOE -> new ITetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
            case LTETROMINOE -> new LTetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
            case REVERSELTETROMINOE -> new ReverseLTetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
            case ZTETROMINOE -> new ZTetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
            case REVERSEZTETROMINOE -> new ReverseZTetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
            case CUBETETROMINOE -> new CubeTetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
            case TTETROMINOE -> new TTetrominoe(shapeType, xSpawnPosition, ySpawnPosition, world);
        };
    }

    @Override
    public void draw() {
        for (Point point : points) {
            this.world.set(point.x, point.y);
        }
    }

    @Override
    public boolean canMove() {
        return canMove(TetrominoeMoves.DOWN);
    }

    private boolean canMove(TetrominoeMoves moveType) {
        Point[] points = new Point[numPoints];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(this.points[i].x, this.points[i].y);
        }
        switch (moveType) {
            case LEFT:
                moveLeft(points);
                break;
            case RIGHT:
                moveRight(points);
                break;
            case DOWN:
                break;
            case ROTATE:
                rotate(points);
                break;
        }
        for (Point pt : points) {
            if (!this.world.isClear(pt.x, pt.y + 1, this)) {
                return false;
            }
        }
        return true;
    }
    private void moveLeft(Point[] points) {
        for (Point pt : points) {
            pt.x--;
        }
    }

    private void moveRight(Point[] points) {
        for (Point pt : points) {
            pt.x++;
        }
    }

    public void moveDown() {
        for (Point point : points) {
            point.y++;
        }
    }

    @Override
    public boolean isValidMove(TetrominoeMoves moveType) {
        return canMove(moveType);
    }
    @Override
    public boolean isPartOfTetrominoe(int x, int y) {
        for (Point point : points) {
            if (point.x == x && point.y == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void performMove(TetrominoeMoves moveType) {
        switch (moveType) {
            case LEFT:
                moveLeft(points);
                break;
            case RIGHT:
                moveRight(points);
                break;
            case DOWN:
                break;
            case ROTATE:
                rotate(points);
                int newRotatedDegree = this.nowRotatedDegree.ordinal();
                newRotatedDegree--;
                if (newRotatedDegree < 0) {
                    newRotatedDegree = 3;
                }
                TetrominoeRotations[] tetrominoeRoatations = TetrominoeRotations.values();
                this.nowRotatedDegree = tetrominoeRoatations[newRotatedDegree];
                break;
        }
    }

    @Override
    public void clear() {
        for (Point point : points) {
            this.world.clear(point.x, point.y);
        }
    }

    protected void rotate(Point[] points) {

    }
}
