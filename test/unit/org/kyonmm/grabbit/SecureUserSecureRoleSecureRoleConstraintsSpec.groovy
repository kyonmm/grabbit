package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserSecureRole)
class SecureUserSecureRoleSecureRoleConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( SecureUserSecureRole, [ new SecureUserSecureRole() ] )
    }

    def "test 'validator' constraint"() {

        when:
            def instance = new SecureUserSecureRole( secureRole:secureRole )
            def result = instance.validate()
        then:
            throw new IllegalStateException(
                "'validator' constraint found. Please implement it by hand" )
            result == false
            instance.errors[ 'secureRole' ] != null
            instance.errors[ 'secureRole' ] == 'validator'
        where:
            secureRole = 'FIX ME'

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new SecureUserSecureRole( secureRole:secureRole )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'secureRole' ] != null
            instance.errors[ 'secureRole' ] == 'nullable'
        where:
            secureRole = null

    }

}