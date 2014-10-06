package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureRoleService)
@Mock(SecureRole)
class SecureRoleServiceDeleteSpec extends Specification {

    def setup() {
        SecureRoleMock.mock( 0 ).save( failOnError:true )
    }

    def " test ok"() {

        when:
            def instance = service.get( id )
            service.delete( instance )
        then:
            SecureRole.count() == 0
        where:
            id = 1

    }

    void "test SecureRole null"() {

        when:
            service.delete( secureRole )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'secureRole' is null"
        where:
            secureRole = null

    }

    def " test invalid"() {

        when:
            def instance = new SecureRole()
            service.delete( instance )
        then:
            SecureRole.exists( instance.id ) == false

    }

}