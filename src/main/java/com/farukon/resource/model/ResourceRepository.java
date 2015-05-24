package com.farukon.resource.model;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ResourceRepository extends MongoRepository<Resource, String> {
    public Resource findById(String id);
    public Resource findByUrl(String url);
    public List<Resource> findBy(TextCriteria textCriteria, Pageable pageable);
}
