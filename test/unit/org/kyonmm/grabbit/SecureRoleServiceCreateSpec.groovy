package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureRoleService)
@Mock(SecureRole)
class SecureRoleServiceCreateSpec extends Specification {

    def "test ok"() {

        when:
            def instance = SecureRoleMock.mock( 0 )
            service.create( instance )
        then:
            SecureRole.count() == 1

    }

    def "test SecureRole null"() {

        when:
            def instance = null
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureRole' is null"

    }

    def "test SecureRole invalid"() {

        when:
            def instance = SecureRoleMock.mock( 0 )
            instance.authority = authority
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureRole' is invalid"
        where:
            authority = null

    }

}