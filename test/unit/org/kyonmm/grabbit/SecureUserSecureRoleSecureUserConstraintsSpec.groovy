package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserSecureRole)
class SecureUserSecureRoleSecureUserConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( SecureUserSecureRole, [ new SecureUserSecureRole() ] )
    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new SecureUserSecureRole( secureUser:secureUser )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'secureUser' ] != null
            instance.errors[ 'secureUser' ] == 'nullable'
        where:
            secureUser = null

    }

}