package dhcoder.libgdx.memory;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * TODO: HEADER COMMENT HERE.
 */
public final class LibgdxUtils {
    public static void resetSprite(final Sprite sprite) {
        sprite.setTexture(null);
        sprite.setScale(1f, 1f);
        sprite.setRotation(0f);
        sprite.setSize(0f, 0f);
        sprite.setPosition(0f, 0f);
        sprite.setOrigin(0f, 0f);
        sprite.setColor(1f, 1f, 1f, 1f);

    }
}
