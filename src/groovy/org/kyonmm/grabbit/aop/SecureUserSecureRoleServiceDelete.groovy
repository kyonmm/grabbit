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
class SecureUserSecureRoleServiceDelete {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.SecureUserSecureRoleService.delete(..)) && bean(secureUserSecureRoleService) && args(secureUserSecureRole)',
        argNames='secureUserSecureRole')
    public void delete( SecureUserSecureRole secureUserSecureRole ) {}

    @Before('delete(secureUserSecureRole)')
    void before( SecureUserSecureRole secureUserSecureRole ) {
        log.info( "Begins request:${secureUserSecureRole}" )
    }

    @AfterReturning(
        pointcut='delete(org.kyonmm.grabbit.SecureUserSecureRole)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='delete(org.kyonmm.grabbit.SecureUserSecureRole)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}