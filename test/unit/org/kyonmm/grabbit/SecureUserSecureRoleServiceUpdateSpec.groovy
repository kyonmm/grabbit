package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserSecureRoleService)
@Mock(SecureUserSecureRole)
class SecureUserSecureRoleServiceUpdateSpec extends Specification {

    def "test ok"() {

        when:
            def instance = SecureUserSecureRoleMock.mock( 0 )
            service.update( instance )
        then:
            SecureUserSecureRole.count() == 1

    }

    def "test SecureUserSecureRole null"() {

        when:
            def instance = null
            service.update( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureUserSecureRole' is null"

    }

    def "test SecureUserSecureRole invalid"() {

        when:
            def instance = SecureUserSecureRoleMock.mock( 0 )
            instance.secureRole = secureRole
            service.update( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureUserSecureRole' is invalid"
        where:
            secureRole = null
    }

}