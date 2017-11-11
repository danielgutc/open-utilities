package org.openutilities.rm.am.repository;

import org.openutilities.core.domain.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Resource repository.
 *
 * TODO Implement a repository that uses queries in files
 * TODO Will a non reactive jdbc driver throw an exception here or fallback to "blocking world" transparently?
 * TODO Double think the benefit of returning Optional
 */
@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long>
{
    /**
     * Return an optional resource by code.
     * @param code is the resource unique code
     * @return the resource
     */
    Resource findByCode(final String code);
}
