package org.kyonmm.grabbit.aop

import org.kyonmm.grabbit.SecureUserSecureRole

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class SecureUserSecureRoleServiceCreate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.SecureUserSecureRoleService.create(..)) && bean(secureUserSecureRoleService) && args(secureUserSecureRole)',
        argNames='secureUserSecureRole')
    public void create( SecureUserSecureRole secureUserSecureRole ) {}

    @Before('create(secureUserSecureRole)')
    void before( SecureUserSecureRole secureUserSecureRole ) {
        log.info( "Begins request: ${secureUserSecureRole}" )
    }

    @AfterReturning(
        pointcut='create(org.kyonmm.grabbit.SecureUserSecureRole)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='create(org.kyonmm.grabbit.SecureUserSecureRole)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}