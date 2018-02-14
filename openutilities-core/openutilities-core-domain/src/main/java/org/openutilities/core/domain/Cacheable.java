package org.openutilities.core.domain;

/**
 * Interface defining methods required to produce clones that are cacheable: there are no ext. libraries dependencies.
 * @param <T>
 */
public interface Cacheable<T>
{
    T cacheableClone();
}
