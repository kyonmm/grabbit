package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserService)
@Mock(SecureUser)
class SecureUserServiceDeleteSpec extends Specification {

    def setup() {
        SecureUserMock.mock( 0 ).save( failOnError:true )
    }

    def " test ok"() {

        when:
            def instance = service.get( id )
            service.delete( instance )
        then:
            SecureUser.count() == 0
        where:
            id = 1

    }

    void "test SecureUser null"() {

        when:
            service.delete( secureUser )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureUser' is null"
        where:
            secureUser = null

    }

    def " test invalid"() {

        when:
            def instance = new SecureUser()
            service.delete( instance )
        then:
            SecureUser.exists( instance.id ) == false

    }

}