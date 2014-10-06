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
class SecureUserSecureRoleServiceGet {

    @Pointcut(
        value='execution(org.kyonmm.grabbit.SecureUserSecureRole org.kyonmm.grabbit.SecureUserSecureRoleService.get(..)) && bean(secureUserSecureRoleService) && args(id)',
        argNames='id')
    public void getMethod( Long id ) {}

    @Before('getMethod(id)')
    void before( Long id ) {
        log.info( "Begins request:${id}" )
    }

    @AfterReturning(
        pointcut='getMethod(Long)',
        returning='secureUserSecureRole')
    void afterReturning( SecureUserSecureRole secureUserSecureRole ) {
        log.info( "End of request: ${secureUserSecureRole}" )
    }

    @AfterThrowing(
        pointcut='getMethod(Long)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}