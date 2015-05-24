package com.farukon.resource.controller;

import com.farukon.resource.model.Resource;
import com.farukon.resource.model.ResourceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {
    static Logger log = Logger.getLogger(ResourceController.class);

    @Autowired
    private ResourceRepository resourceRepository;

    @RequestMapping(value="resources", method = RequestMethod.POST)
    public void addResource(@RequestParam("url") String url) {
        Resource resource = resourceRepository.findByUrl(url);

        if (resource == null) {
            resource = new Resource(url);
            resource.setTitle(url);
            resource.setSummary(url);
            resourceRepository.insert(resource);
        } else {
            resource.setTitle(url);
            resource.setSummary(url);
            resourceRepository.save(resource);
        }
    }

    @RequestMapping(value = "resources/{id}", produces = "application/json")
    @ResponseBody
    public Resource getByResourceId(@PathVariable("id") String id) {
        return resourceRepository.findById(id);
    }

    @RequestMapping(value = "resources", method = RequestMethod.GET)
    public List<Resource> findResources(@RequestParam("kw") String kw) {
        return resourceRepository.findBy(TextCriteria.forDefaultLanguage().matching(kw), null);
    }

    @RequestMapping("hello")
    public String hello() {
        return "Hello!!";
    }
}
