package org.openutilities.rm.am.controller;

import org.openutilities.rm.am.service.CacheLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheController
{
    @Autowired
    private CacheLoaderService cacheLoaderService;

    @GetMapping(value = "/cache") // TODO easier to invoke
    //@PutMapping(value = "/cache")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void loadCaches()
    {
        cacheLoaderService.initCache();
    }
}
