package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUser)
class SecureUserPasswordConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( SecureUser, [ new SecureUser() ] )
    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-10474' )
    def "test 'blank' constraint"() {

        when:
            def instance = new SecureUser( password:password )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'password' ] != null
            instance.errors[ 'password' ] == 'blank'
        where:
            password = ''

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new SecureUser( password:password )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'password' ] != null
            instance.errors[ 'password' ] == 'nullable'
        where:
            password = null

    }

}