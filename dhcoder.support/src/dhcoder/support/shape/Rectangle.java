package dhcoder.support.shape;

import static dhcoder.support.text.StringUtils.format;

/**
 * Class that represents a circle
 */
public final class Rectangle implements Shape {

    private float halfWidth;
    private float halfHeight;

    private Rectangle() {
        this(0f, 0f);
    }

    public Rectangle(final float halfWidth, final float halfHeight) {
        setHalfSize(halfWidth, halfHeight);
    }

    @Override
    public boolean containsPoint(final float x, final float y) {
        return Math.abs(x) < halfWidth && Math.abs(y) < halfHeight;
    }

    public float getLeft(final float xOrigin) { return xOrigin - halfWidth; }

    public float getBottom(final float yOrigin) { return yOrigin - halfHeight; }

    public float getRight(final float xOrigin) { return xOrigin + halfWidth; }

    public float getTop(final float yOrigin) { return yOrigin + halfHeight; }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public Rectangle setHalfSize(final float halfWidth, final float halfHeight) {
        if (halfWidth < 0f || halfHeight < 0f) {
            throw new IllegalArgumentException(
                format("Can't create rectangle with < 0 half-size ({0},{1})", halfWidth, halfHeight));
        }

        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
        return this;
    }

    @Override
    public String toString() {
        return format("w/2={0},h/2={1}", halfWidth, halfHeight);
    }
}