package org.openutilities.rm.am.repository;

import org.openutilities.rm.am.domain.UsagePoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Usage point repository
 */
@Repository
public interface UsagePointRepository extends CrudRepository<UsagePoint, Long>
{
    UsagePoint findByCode(final String code);
}
