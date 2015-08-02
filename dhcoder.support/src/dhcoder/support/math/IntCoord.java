package dhcoder.support.math;

import dhcoder.support.memory.Poolable;

import static dhcoder.support.text.StringUtils.format;

/**
 * Simple class that represents an integer (x,y) coordinate.
 */
public final class IntCoord implements Poolable {

    private int x;
    private int y;

    public IntCoord() {}

    public IntCoord(final int x, final int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public IntCoord setX(final int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public IntCoord setY(final int y) {
        this.y = y;
        return this;
    }

    public IntCoord set(final int x, final int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public IntCoord setFrom(final IntCoord rhs) {
        this.x = rhs.x;
        this.y = rhs.y;
        return this;
    }

    @Override
    public void reset() {
        set(0, 0);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        IntCoord intCoord = (IntCoord)o;

        if (x != intCoord.x) { return false; }
        if (y != intCoord.y) { return false; }

        return true;
    }

    @Override
    public String toString() {
        return format("({0}x{1})", x, y);
    }
}
