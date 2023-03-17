package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LawnTest {

    private final Lawn lawn = new Lawn(4,4);

    @ParameterizedTest
    @CsvSource({"2, 3","0, 0","4, 4"})
    void isInBounds(int x, int y) {
        var posInBounds = new Position(x,y);
        assertTrue(lawn.isInBounds(posInBounds));
    }

    @ParameterizedTest
    @CsvSource({"5, 2","-1, -2","2, 6"})
    void isNotInBounds(int x, int y) {
        var posNotInBounds = new Position(x,y);
        assertFalse(lawn.isInBounds(posNotInBounds));
    }

    @Test
    void positionNull() {
        assertFalse(lawn.isInBounds(null));
    }
}