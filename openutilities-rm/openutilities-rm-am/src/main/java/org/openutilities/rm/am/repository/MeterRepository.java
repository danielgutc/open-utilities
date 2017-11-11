package org.openutilities.rm.am.repository;

import org.openutilities.core.domain.Meter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends CrudRepository<Meter, Long>
{
    Meter findByCode(final String code);
}
