public interface TetrominoeInterface {
    void draw();
    boolean canMove();
    boolean isValidMove(TetrominoeMoves moveType);
    boolean isPartOfTetrominoe(int i, int j);
    void performMove(TetrominoeMoves moveType);
    void clear();
}
