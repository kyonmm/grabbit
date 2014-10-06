package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureRoleController)
class SecureRoleControllerListSpec extends Specification {

    def setup() {
        views[ '/secureRole/_list.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockSecureRoleService()
            request.method = 'GET'
            def model = controller.list()
        control.verify()

        then:
            response.text == 'OK'
            response.status == 200

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'POST'
            controller.list()
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${items && total}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockSecureRoleService() {

        def control = mockFor( SecureRoleService )
        control.demand.list( 1 ) { Map params ->
            [ items:[ new SecureRole() ], total:1 ] }
        controller.secureRoleService = control.createMock()
        control

    }

}