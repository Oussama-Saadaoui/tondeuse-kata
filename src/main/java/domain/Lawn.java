package domain;

public record Lawn(int xMax, int yMax) {
    private static final int xMin = 0;
    private static final int yMin = 0;

    // TODO
    public boolean isInBounds(Position position) {
        return false;
    }
}
