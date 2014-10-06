package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserSecureRoleService)
@Mock(SecureUserSecureRole)
class SecureUserSecureRoleServiceDeleteSpec extends Specification {

    def setup() {
        SecureUserSecureRoleMock.mock( 0 ).save( failOnError:true )
    }

    def " test ok"() {

        when:
            def instance = service.get( id )
            service.delete( instance )
        then:
            SecureUserSecureRole.count() == 0
        where:
            id = 1

    }

    void "test SecureUserSecureRole null"() {

        when:
            service.delete( secureUserSecureRole )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureUserSecureRole' is null"
        where:
            secureUserSecureRole = null

    }

    def " test invalid"() {

        when:
            def instance = new SecureUserSecureRole()
            service.delete( instance )
        then:
            SecureUserSecureRole.exists( instance.id ) == false

    }

}