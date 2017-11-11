package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Resource;
import org.openutilities.rm.am.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Resource service.
 */
@Service
public class ResourceService
{
    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * Search a resource given its unique code.
     * @param code is the unique code
     * @return the resource
     */
    public Resource getResource(final String code)
    {
        return resourceRepository.findByCode(code);
    }
}
