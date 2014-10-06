package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserSecureRoleController)
@Mock(SecureUserSecureRole)
class SecureUserSecureRoleControllerSaveSpec extends Specification {

    def setup() {
        views[ '/secureUserSecureRole/_form.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockSecureUserSecureRoleService()
            request.method = 'POST'
            setUpParams()
            controller.save()
            control.verify()
        then:
            flash.formMessage == 'default.created.message'
            response.redirectedUrl == "/secureUserSecureRole/edit/1"
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockSecureUserSecureRoleService( false )
            request.method = 'POST'
            setUpParams()
            params.secureRole = null
            controller.save()
            control.verify()
        then:
            response.text == 'OK'
            response.status == 400

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'GET'
            controller.save()
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${secureUserSecureRoleInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockSecureUserSecureRoleService( save = true ) {

        def control = mockFor( SecureUserSecureRoleService )
        control.demand.create( 1 ) { SecureUserSecureRole instance ->
            if ( save ) {
                instance.id = 1
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
        }
        controller.secureUserSecureRoleService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = SecureUserSecureRoleMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}