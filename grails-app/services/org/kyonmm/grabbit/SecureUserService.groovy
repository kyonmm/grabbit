package org.kyonmm.grabbit

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class SecureUserService {

    Map list(Map params) {

        processParams(params)
        def criteria = new DetachedCriteria(SecureUser).build {}
        [items: criteria.list(params), total: criteria.count()]

    }

    void create(SecureUser secureUser) {
        save(secureUser)
    }

    void update(SecureUser secureUser) {
        save(secureUser)
    }

    SecureUser get(Long id) {

        if (id == null) throw new IllegalArgumentException(
                "Parameter 'id' is null")
        SecureUser.findById(id)

    }

    void delete(SecureUser secureUser) {

        if (secureUser == null) throw new IllegalArgumentException(
                "Parameter 'secureUser' is null")
        secureUser.delete()

    }

    private void processParams(params) {

        params.max = ListUtils.parseMax(params.max)
        params.offset = ListUtils.parseOffset(params.offset)
        params.order = ListUtils.parseOrder(params.order)
        def fields = ['username', 'password', 'email', 'accountExpired', 'accountLocked', 'enabled', 'passwordExpired', 'id']
        params.sort = ListUtils.parseSort(params.sort, fields)

    }

    private void save(SecureUser secureUser) {

        if (!secureUser) throw new IllegalArgumentException(
                "Parameter 'secureUser' is null")
        try {
            secureUser.save(failOnError: true)
        } catch (ValidationException) {
            throw new IllegalArgumentException(
                    "Parameter 'secureUser' is invalid")
        }

    }

}