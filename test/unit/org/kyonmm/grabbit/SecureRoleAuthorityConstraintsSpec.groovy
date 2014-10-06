package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureRole)
class SecureRoleAuthorityConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( SecureRole, [ new SecureRole( authority:'A' ) ] )
    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-10474' )
    def "test 'blank' constraint"() {

        when:
            def instance = new SecureRole( authority:authority )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'authority' ] != null
            instance.errors[ 'authority' ] == 'blank'
        where:
            authority = ''

    }

    def "test 'unique' constraint"() {

        when:
            def instance = new SecureRole( authority:authority )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'authority' ] != null
            instance.errors[ 'authority' ] == 'unique'
        where:
            authority = 'A'

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new SecureRole( authority:authority )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'authority' ] != null
            instance.errors[ 'authority' ] == 'nullable'
        where:
            authority = null

    }

}