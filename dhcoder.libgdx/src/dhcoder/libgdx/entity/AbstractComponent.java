package dhcoder.libgdx.entity;

import dhcoder.support.time.Duration;

/**
 * Abstract {@link Component} which provides default implementations for all methods, so you can only override the ones
 * you care about.
 */
public abstract class AbstractComponent implements Component {

    @Override
    public void initialize(final Entity owner) {}

    @Override
    public void update(final Duration elapsedTime) {}

    @Override
    public void reset() {}
}
