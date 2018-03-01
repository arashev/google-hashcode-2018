package tech.abcde.hashcode;

import tech.abcde.hashcode.model.Position;

import static java.lang.Math.abs;

public final class AppUtils {

    private AppUtils() {
    }

    public static long distance(Position pos1, Position pos2) {
        int xDelta = abs(pos2.x - pos1.x);
        int yDelta = abs(pos2.y - pos1.y);
        return xDelta + yDelta;
    }
}
