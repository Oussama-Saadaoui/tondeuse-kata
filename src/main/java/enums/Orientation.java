package enums;


public enum Orientation {
    NORTH, EAST, SOUTH, WEST;

    private static final Orientation[] orientations = values();

    /**
     * Renvoie le prochain point cardinal en tournant de 90°
     * N -> E -> S -> W -> N...
     */
    public Orientation nextClockwise() {
        return orientations[(this.ordinal() + 1) % orientations.length];
    }

    /**
     * Renvoie le prochain point cardinal en tournant de -90°
     * N -> W -> S -> E -> N...
     */
    public Orientation previousClockwise() {
        // Permet de récupérer la valeur précédente de la liste des valeurs possibles de l'enum
        // On ajoute + orientations.length afin d'éviter un modulo sur un nombre négatif
        return orientations[(this.ordinal() - 1 + orientations.length) % orientations.length];
    }
}
