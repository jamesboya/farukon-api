package com.farukon.resource.controller;

import com.farukon.resource.model.Resource;
import com.farukon.resource.model.ResourceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {
    static Logger log = Logger.getLogger(ResourceController.class);

    @Autowired
    private ResourceRepository resourceRepository;

    @RequestMapping(value="resources", method = RequestMethod.POST)
    public void addResource(@RequestParam("url") String url) {
        resourceRepository.insert(new Resource(url));
    }

    @RequestMapping(value = "resources/{id}", produces = "application/json")
    @ResponseBody
    public Resource getByResourceId(@PathVariable("id") String id) {
        return resourceRepository.findById(id);
    }

    @RequestMapping("hello")
    public String hello() {
        return "Hello!!";
    }
}
