package dhcoder.libgdx.physics;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Default implementation of the {@link CollisionHandler} interface.
 */
public abstract class AbstractCollisionHandler implements CollisionHandler {
    @Override
    public void onCollided(final Body bodyA, final Body bodyB) {}

    @Override
    public void onOverlapping(final Body bodyA, final Body bodyB) {}

    @Override
    public void onSeparated(final Body bodyA, final Body bodyB) {}
}
