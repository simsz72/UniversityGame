public enum TetrominoeRotations {
    DEGREES0, DEGREES90, DEGREES180, DEGREES270;

    public TetrominoeRotations getType(int i) {
        return values()[i];
    }

}