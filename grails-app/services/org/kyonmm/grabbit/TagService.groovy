package org.kyonmm.grabbit

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class TagService {

    Map list(Map params) {

        processParams(params)
        def criteria = new DetachedCriteria(Tag).build {}
        [items: criteria.list(params), total: criteria.count()]

    }

    void create(Tag tag) {
        save(tag)
    }

    void update(Tag tag) {
        save(tag)
    }

    Tag get(Long id) {

        if (id == null) throw new IllegalArgumentException(
                "Parameter 'id' is null")
        Tag.findById(id)

    }

    void delete(Tag tag) {

        if (tag == null) throw new IllegalArgumentException(
                "Parameter 'tag' is null")
        tag.delete()

    }

    private void processParams(params) {

        params.max = ListUtils.parseMax(params.max)
        params.offset = ListUtils.parseOffset(params.offset)
        params.order = ListUtils.parseOrder(params.order)
        def fields = ['description', 'name', 'id']
        params.sort = ListUtils.parseSort(params.sort, fields)

    }

    private void save(Tag tag) {

        if (!tag) throw new IllegalArgumentException(
                "Parameter 'tag' is null")
        try {
            tag.save(failOnError: true)
        } catch (ValidationException) {
            throw new IllegalArgumentException(
                    "Parameter 'tag' is invalid")
        }

    }

}