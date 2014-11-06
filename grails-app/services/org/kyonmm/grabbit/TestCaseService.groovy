package org.kyonmm.grabbit

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class TestCaseService {

    Map list(Map params) {

        processParams(params)
        def criteria = new DetachedCriteria(TestCase).build {}
        [items: criteria.list(params), total: criteria.count()]

    }

    void create(TestCase testCase) {
        save(testCase)
    }

    void update(TestCase testCase) {
        save(testCase)
    }

    TestCase get(Long id) {

        if (id == null) throw new IllegalArgumentException(
                "Parameter 'id' is null")
        TestCase.findById(id)

    }

    void delete(TestCase testCase) {

        if (testCase == null) throw new IllegalArgumentException(
                "Parameter 'testCase' is null")
        testCase.delete()

    }

    private void processParams(params) {

        params.max = ListUtils.parseMax(params.max)
        params.offset = ListUtils.parseOffset(params.offset)
        params.order = ListUtils.parseOrder(params.order)
        def fields = ['name', 'scenario', 'id']
        params.sort = ListUtils.parseSort(params.sort, fields)

    }

    private void save(TestCase testCase) {

        if (!testCase) throw new IllegalArgumentException(
                "Parameter 'testCase' is null")
        try {
            testCase.save(failOnError: true)
        } catch (ValidationException) {
            throw new IllegalArgumentException(
                    "Parameter 'testCase' is invalid")
        }

    }

}