package domain;

import enums.Orientation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LawnMowersTest {

    @Test
    void executeOrders() {
        var lawn = new Lawn(4,4);
        var sharedPos = new Position(1,1);

        var mowers = List.of(
            new Mower(sharedPos, Orientation.NORTH, "AAA"),
            new Mower(sharedPos, Orientation.EAST, "AA"),
            new Mower(sharedPos, Orientation.SOUTH, "A"),
            new Mower(sharedPos, Orientation.WEST, "")
        );

        var lawnMowers = new LawnMowers(lawn, mowers);

        var resultLawnMowers = lawnMowers.executeOrders().mowers();

        var expectedMowers = List.of(
            new Mower(new Position(1, 4), Orientation.NORTH, ""),
            new Mower(new Position(3, 1), Orientation.EAST, ""),
            new Mower(new Position(1, 0), Orientation.SOUTH, ""),
            new Mower(sharedPos, Orientation.WEST, "")
        );

        assertEquals(expectedMowers, resultLawnMowers);
    }
}