package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserService)
@Mock(SecureUser)
class SecureUserServiceGetSpec extends Specification {

    def setup() {
        SecureUserMock.mock( 0 ).save( failOnError:true )
    }

    def " test ok"() {

        when:
            def result = service.get( id )
        then:
            result != null
        where:
            id = 1

    }

    void "test Id null"() {

        when:
            service.get( id )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'id' is null"
        where:
            id = null

    }

    def " test not found"() {

        when:
            def result = service.get( id )
        then:
            result == null
        where:
            id = 2

    }

}