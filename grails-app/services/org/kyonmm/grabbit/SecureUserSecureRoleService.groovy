package org.kyonmm.grabbit

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class SecureUserSecureRoleService {

    Map list(Map params) {

        processParams(params)
        def criteria = new DetachedCriteria(SecureUserSecureRole).build {}
        [items: criteria.list(params), total: criteria.count()]

    }

    void create(SecureUserSecureRole secureUserSecureRole) {
        save(secureUserSecureRole)
    }

    void update(SecureUserSecureRole secureUserSecureRole) {
        save(secureUserSecureRole)
    }

    SecureUserSecureRole get(Long id) {

        if (id == null) throw new IllegalArgumentException(
                "Parameter 'id' is null")
        SecureUserSecureRole.findById(id)

    }

    void delete(SecureUserSecureRole secureUserSecureRole) {

        if (secureUserSecureRole == null) throw new IllegalArgumentException(
                "Parameter 'secureUserSecureRole' is null")
        secureUserSecureRole.delete()

    }

    private void processParams(params) {

        params.max = ListUtils.parseMax(params.max)
        params.offset = ListUtils.parseOffset(params.offset)
        params.order = ListUtils.parseOrder(params.order)
        def fields = ['id']
        params.sort = ListUtils.parseSort(params.sort, fields)

    }

    private void save(SecureUserSecureRole secureUserSecureRole) {

        if (!secureUserSecureRole) throw new IllegalArgumentException(
                "Parameter 'secureUserSecureRole' is null")
        try {
            secureUserSecureRole.save(failOnError: true)
        } catch (ValidationException) {
            throw new IllegalArgumentException(
                    "Parameter 'secureUserSecureRole' is invalid")
        }

    }

}