package domain;

public record Lawn(int xMax, int yMax) {
    private static final int xMin = 0;
    private static final int yMin = 0;

    public boolean isInBounds(Position position) {
        if (position == null) {
            return false;
        }
        return (position.x() >= xMin && position.x() <= xMax) && (position.y() >= yMin && position.y() <= yMax);
    }
}
