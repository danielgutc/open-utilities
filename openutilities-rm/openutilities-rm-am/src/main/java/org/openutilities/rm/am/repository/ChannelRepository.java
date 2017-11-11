package org.openutilities.rm.am.repository;

import org.openutilities.core.domain.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends CrudRepository<Channel, Long>
{
}
