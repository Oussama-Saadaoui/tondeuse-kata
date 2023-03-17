package enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {

    @ParameterizedTest
    @CsvSource({"NORTH, EAST", "EAST, SOUTH", "SOUTH, WEST", "WEST, NORTH"})
    void nextClockwise(Orientation input, Orientation expected) {
        var result = input.nextClockwise();
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"NORTH, WEST", "WEST, SOUTH", "SOUTH, EAST", "EAST, NORTH"})
    void previousClockwise(Orientation input, Orientation expected) {
        var result = input.previousClockwise();
        assertEquals(expected, result);
    }
}