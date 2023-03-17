package domain;

import enums.Orientation;

/**
 * Une tondeuse est positionnée sur une pelouse et se dirige dans une direction
 * Elle detient une liste d'ordres qui la feront se déplacer
 */
public record Mower(Position position, Orientation orientation, String moveOrders) {

    /**
     * Execute les ordres que la tondeuse a
     * A -> Avancer
     * G -> Tourner à gauche
     * D -> Tourner à droite
     *
     * @param lawn Pelouse sur laquelle tondre
     * @return nouvelle instance de la tondeuse qui contient une nouvelle position et orientation (si ordres donnés)
     */
    public Mower executeOrders(Lawn lawn) {
        var currentPos = position;
        var currentOrientation = orientation;
        for (char order : this.moveOrders.toCharArray()) {
            switch (order) {
                case 'A' -> {
                    var futurePosition = computeNewPosition(currentPos, currentOrientation);
                    if (lawn.isInBounds(futurePosition)) {
                        currentPos = futurePosition;
                    }
                }
                case 'D' -> currentOrientation = currentOrientation.nextClockwise();
                case 'G' -> currentOrientation = currentOrientation.previousClockwise();
                default -> throw new UnsupportedOperationException("Order "+order+" not supported");
            }
        }
        // On vide les ordres vu qu'ils ont déjà été effectués
        return new Mower(currentPos, currentOrientation, "");
    }

    /**
     * Permet à la tondeuse d'avancer dans sa direction prédéfinie
     */
    private Position computeNewPosition(Position position, Orientation orientation) {
        return switch (orientation) {
            case NORTH -> new Position(position.x(), position.y()+1);
            case EAST -> new Position(position.x()+1, position.y());
            case SOUTH -> new Position(position.x(), position.y()-1);
            case WEST -> new Position(position.x()-1, position.y());
        };
    }
}
