package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUser)
class SecureUserUsernameConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( SecureUser, [ new SecureUser( username:'A' ) ] )
    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-10474' )
    def "test 'blank' constraint"() {

        when:
            def instance = new SecureUser( username:username )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'username' ] != null
            instance.errors[ 'username' ] == 'blank'
        where:
            username = ''

    }

    def "test 'unique' constraint"() {

        when:
            def instance = new SecureUser( username:username )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'username' ] != null
            instance.errors[ 'username' ] == 'unique'
        where:
            username = 'A'

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new SecureUser( username:username )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'username' ] != null
            instance.errors[ 'username' ] == 'nullable'
        where:
            username = null

    }

}