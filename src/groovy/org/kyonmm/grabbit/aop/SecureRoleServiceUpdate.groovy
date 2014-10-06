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
class SecureRoleServiceUpdate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.SecureRoleService.update(..)) && bean(secureRoleService) && args(secureRole)',
        argNames='secureRole')
    public void update( SecureRole secureRole ) {}

    @Before('update(secureRole)')
    void before( SecureRole secureRole ) {
        log.info( "Begins request: ${secureRole}" )
    }

    @AfterReturning(
        pointcut='update(org.kyonmm.grabbit.SecureRole)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='update(org.kyonmm.grabbit.SecureRole)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}