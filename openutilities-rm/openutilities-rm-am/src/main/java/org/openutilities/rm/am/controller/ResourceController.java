package org.openutilities.rm.am.controller;

import org.openutilities.rm.am.domain.Resource;
import org.openutilities.rm.am.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController
{
    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    private ResourceService resourceService;


    /**
     * Get a resource given its code.
     * TODO set error codes, for instance, NOT_FOUND.
     *
     * @param code is the resource unique code
     * @return the resource
     */
    @GetMapping(value = "/resource/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Resource getResourceById(@PathVariable String code)
    {
        logger.debug("Requested resource code '%s'", code);

        return resourceService.getResource(code);
    }
}
