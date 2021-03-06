package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Resource;
import org.openutilities.rm.am.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Return all resources
     * @return
     */
    public List<Resource> getResources()
    {
        List<Resource> resources = new ArrayList<>();
        resourceRepository.findAll().forEach(r -> resources.add(r));

        return resources;
    }
}
