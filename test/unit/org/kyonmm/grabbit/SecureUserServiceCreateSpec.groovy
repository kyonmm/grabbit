package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserService)
@Mock(SecureUser)
class SecureUserServiceCreateSpec extends Specification {

    def "test ok"() {

        when:
            def instance = SecureUserMock.mock( 0 )
            service.create( instance )
        then:
            SecureUser.count() == 1

    }

    def "test SecureUser null"() {

        when:
            def instance = null
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureUser' is null"

    }

    def "test SecureUser invalid"() {

        when:
            def instance = SecureUserMock.mock( 0 )
            instance.username = username
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureUser' is invalid"
        where:
            username = null

    }

}