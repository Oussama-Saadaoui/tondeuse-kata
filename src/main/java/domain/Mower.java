package domain;

import enums.Orientation;

/**
 * Une tondeuse est positionnée sur une pelouse et se dirige dans une direction
 * Elle detient une liste d'ordres qui la feront se déplacer
 */
// TODO
public record Mower(Position position, Orientation orientation, String moveOrders) {

    public Mower executeOrders(Lawn lawn) {
        return null;
    }

    private Mower advance() {
        return null;
    }

    private Mower turnRight() {
        return null;
    }

    private Mower turnLeft() {
        return null;
    }
}
