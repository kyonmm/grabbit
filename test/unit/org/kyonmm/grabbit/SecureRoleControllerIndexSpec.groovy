package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureRoleController)
class SecureRoleControllerIndexSpec extends Specification {

    def "test ok"() {

        when:
            request.method = 'GET'
            controller.index()
        then:
            response.redirectedUrl == '/secureRole/content'
            response.status == 302

    }

    def "test ok with params"() {

        when:
            request.method = 'GET'
            params.name = 'value'
            controller.index()
        then:
            response.redirectedUrl == '/secureRole/content?name=value'
            response.status == 302

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'POST'
            controller.index()
        then:
            response.status == 405

    }

}