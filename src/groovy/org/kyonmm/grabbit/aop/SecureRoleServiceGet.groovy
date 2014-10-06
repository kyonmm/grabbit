package org.kyonmm.grabbit.aop

import org.kyonmm.grabbit.SecureRole

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class SecureRoleServiceGet {

    @Pointcut(
        value='execution(org.kyonmm.grabbit.SecureRole org.kyonmm.grabbit.SecureRoleService.get(..)) && bean(secureRoleService) && args(id)',
        argNames='id')
    public void getMethod( Long id ) {}

    @Before('getMethod(id)')
    void before( Long id ) {
        log.info( "Begins request:${id}" )
    }

    @AfterReturning(
        pointcut='getMethod(Long)',
        returning='secureRole')
    void afterReturning( SecureRole secureRole ) {
        log.info( "End of request: ${secureRole}" )
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