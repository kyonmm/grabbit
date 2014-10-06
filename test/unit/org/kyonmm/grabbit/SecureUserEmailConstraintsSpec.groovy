package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUser)
class SecureUserEmailConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( SecureUser, [ new SecureUser() ] )
    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-10474' )
    def "test 'blank' constraint"() {

        when:
            def instance = new SecureUser( email:email )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'email' ] != null
            instance.errors[ 'email' ] == 'blank'
        where:
            email = ''

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new SecureUser( email:email )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'email' ] != null
            instance.errors[ 'email' ] == 'nullable'
        where:
            email = null

    }

}