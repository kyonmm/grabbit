package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(SecureUserController)
@Mock(SecureUser)
class SecureUserControllerSaveSpec extends Specification {

    def setup() {
        views[ '/secureUser/_form.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockSecureUserService()
            request.method = 'POST'
            setUpParams()
            controller.save()
            control.verify()
        then:
            flash.formMessage == 'default.created.message'
            response.redirectedUrl == "/secureUser/edit/1"
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockSecureUserService( false )
            request.method = 'POST'
            setUpParams()
            params.username = null
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
        '<g:if test="${secureUserInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockSecureUserService( save = true ) {

        def control = mockFor( SecureUserService )
        control.demand.create( 1 ) { SecureUser instance ->
            if ( save ) {
                instance.id = 1
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
        }
        controller.secureUserService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = SecureUserMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}