package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureRoleController)
@Mock(SecureRole)
class SecureRoleControllerSaveSpec extends Specification {

    def setup() {
        views[ '/secureRole/_form.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockSecureRoleService()
            request.method = 'POST'
            setUpParams()
            controller.save()
            control.verify()
        then:
            flash.formMessage == 'default.created.message'
            response.redirectedUrl == "/secureRole/edit/1"
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockSecureRoleService( false )
            request.method = 'POST'
            setUpParams()
            params.authority = null
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
        '<g:if test="${secureRoleInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockSecureRoleService( save = true ) {

        def control = mockFor( SecureRoleService )
        control.demand.create( 1 ) { SecureRole instance ->
            if ( save ) {
                instance.id = 1
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
        }
        controller.secureRoleService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = SecureRoleMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}