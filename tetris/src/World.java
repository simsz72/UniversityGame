import java.util.Random;
import java.util.Scanner;

public class World {
    private static int[][] fillMap = new int[20][20];
    private Tetrominoes activeTetrominoe;
    public void createWorld() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == 0 || j == 19 || i == 19 || j == 0) {
                    fillMap[i][j] = 1;
                } else {
                    fillMap[i][j] = 0;
                }
            }
        }
    }
    private World(){

    }

    private static World worldInstance = null;

    public static World getInstance(){
        if(worldInstance == null){
            worldInstance = new World();
        }
        return worldInstance;
    }
    public void addNewTetrominoe() {
        Random rand = new Random();
        int TetrominoeTypes = 7;
        int shapeType = rand.nextInt(TetrominoeTypes);
        TetrominoeTypeNames[] tetrominoeTypeNames = TetrominoeTypeNames.values();
        TetrominoeTypeNames tetrominoeTypeNames1 = tetrominoeTypeNames[shapeType];
        Tetrominoes tetrominoes = new Tetrominoes(tetrominoeTypeNames1, this);
        activeTetrominoe = tetrominoes.createTetrominoeType();
        activeTetrominoe.draw();
    }

    public void set(int x, int y) {
        if (x >= 0 && x < 20 && y >= 0 && y < 20) {
            fillMap[x][y] = 2;
        }
    }

    public void update() {
        removeFullLines();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (this.isWall(i, j))
                    System.out.print("#");
                else if (this.isTetrominoe(i, j))
                    System.out.print("*");
                else if (isEmpty(i,j))
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean isWall(int x, int y) {
        return fillMap[y][x] == 1;
    }

    public boolean isTetrominoe(int x, int y) {return fillMap[y][x] == 2;}

    public boolean isEmpty(int x, int y) {return fillMap[y][x] == 0;}

    public boolean isClear(int i, int j, Tetrominoes tetrominoes) {
        if (i < 1 || i > 18 || j < 1 || j > 18) {
            return false;
        }
        return fillMap[i][j] == 0 || tetrominoes.isPartOfTetrominoe(i, j);
    }

    public void moveTetrominoe(TetrominoeMoves type) {
        if (activeTetrominoe.isValidMove(type)) {
            activeTetrominoe.clear();
            activeTetrominoe.performMove(type);
            activeTetrominoe.moveDown();
            activeTetrominoe.draw();
        }
    }

    public void startGame() {
        Renderer renderer = new Renderer();
        createWorld();
        renderer.renderGame(this);
        addNewTetrominoe();
        update();
        while (activeTetrominoe.canMove()) {
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter move (a - move left, d - move right, w - rotate, s - down): ");
                String input = scanner.nextLine();
                switch (input) {
                    case "a" -> moveTetrominoe(TetrominoeMoves.LEFT);
                    case "d" -> moveTetrominoe(TetrominoeMoves.RIGHT);
                    case "s" -> moveTetrominoe(TetrominoeMoves.DOWN);
                    case "w" -> moveTetrominoe(TetrominoeMoves.ROTATE);
                }
                update();
            } while (activeTetrominoe.canMove());
            addNewTetrominoe();
            update();
        }
        gameOver();
    }

    private void gameOver() {
        System.out.println("You lose!");
        System.exit(0);
    }

    public void clear(int x, int y) {
        if (x >= 0 && x < 20 && y >= 0 && y < 20) {
            fillMap[x][y] = 0;
        }
    }

    private void removeFullLines() {
        for (int i = 18; i >= 1; i--) {
            boolean lineIsFull = true;
            for (int j = 1; j < 19; j++) {
                if (!isTetrominoe(i,j)) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                for (int k = 1; k < 19; k++) {
                    fillMap[k][i] = 0;
                    }
                for(int q = i-1; q >= 1; q--){
                    for(int w = 1; w < 19; w++){
                        if(isTetrominoe(q, w)){
                            fillMap[w][q+1] = 2;
                            fillMap[w][q] = 0;
                        }
                    }
                }
                }
            }
        }
    }
