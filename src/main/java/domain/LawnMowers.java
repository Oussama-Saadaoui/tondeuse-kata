package domain;

import java.util.ArrayList;
import java.util.List;

public record LawnMowers(Lawn lawn, List<Mower> mowers) {

    public LawnMowers executeOrders() {
        List<Mower> executedMowers = new ArrayList<>();
        for (Mower mower: mowers) {
            executedMowers.add(mower.executeOrders(lawn));
        }
        return new LawnMowers(lawn, executedMowers);
    }
}
