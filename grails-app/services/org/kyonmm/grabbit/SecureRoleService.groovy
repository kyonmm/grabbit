package org.kyonmm.grabbit

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class SecureRoleService {

    Map list( Map params ) {

        processParams( params )
        def criteria = new DetachedCriteria( SecureRole ).build {}
        [ items:criteria.list( params ), total:criteria.count() ]

    }

    void create( SecureRole secureRole ) {
        save( secureRole )
    }

    void update( SecureRole secureRole ) {
        save( secureRole )
    }

    SecureRole get( Long id ) {

        if ( id == null ) throw new IllegalArgumentException(
            "Parameter 'id' is null" )
        SecureRole.findById( id )

    }

    void delete( SecureRole secureRole ) {

        if ( secureRole == null ) throw new IllegalArgumentException(
            "Parameter 'secureRole' is null" )
        secureRole.delete()

    }

    private void processParams( params ) {

        params.max = ListUtils.parseMax( params.max )
        params.offset = ListUtils.parseOffset( params.offset )
        params.order = ListUtils.parseOrder( params.order )
        def fields = [ 'authority', 'id' ]
        params.sort = ListUtils.parseSort( params.sort, fields )

    }

    private void save( SecureRole secureRole ) {

        if ( !secureRole ) throw new IllegalArgumentException(
            "Parameter 'secureRole' is null" )
        try {
            secureRole.save( failOnError:true )
        } catch ( ValidationException ) {
            throw new IllegalArgumentException(
                "Parameter 'secureRole' is invalid" )
        }

    }

}