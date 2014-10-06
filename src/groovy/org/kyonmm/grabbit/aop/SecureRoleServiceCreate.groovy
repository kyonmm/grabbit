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
class SecureRoleServiceCreate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.SecureRoleService.create(..)) && bean(secureRoleService) && args(secureRole)',
        argNames='secureRole')
    public void create( SecureRole secureRole ) {}

    @Before('create(secureRole)')
    void before( SecureRole secureRole ) {
        log.info( "Begins request: ${secureRole}" )
    }

    @AfterReturning(
        pointcut='create(org.kyonmm.grabbit.SecureRole)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='create(org.kyonmm.grabbit.SecureRole)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}