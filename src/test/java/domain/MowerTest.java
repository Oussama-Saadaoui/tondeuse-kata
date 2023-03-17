package domain;

import enums.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerTest {

    private final Lawn lawn = new Lawn(5,5);

    @Test
    void executeOrders() {
        var mower = new Mower(new Position(1,1), Orientation.EAST, "AAGAAAG");

        var expectedMower = new Mower(new Position(3,4), Orientation.WEST, "");
        var resultMower = mower.executeOrders(lawn);

        assertEquals(expectedMower, resultMower);
    }

    @Test
    void executeOrdersInFrontOfWall() {
        var mower = new Mower(new Position(3,5), Orientation.NORTH, "AAAAA");

        var expectedMower = new Mower(mower.position(), mower.orientation(), "");
        var resultMower = mower.executeOrders(lawn);

        assertEquals(expectedMower, resultMower);
    }

    @Test
    void executeNoOrders() {
        var mower = new Mower(new Position(3,5), Orientation.NORTH, "");

        var expectedMower = mower;
        var resultMower = mower.executeOrders(lawn);

        assertEquals(expectedMower, resultMower);
    }
}