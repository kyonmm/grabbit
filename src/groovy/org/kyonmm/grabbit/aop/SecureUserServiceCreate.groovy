package org.kyonmm.grabbit.aop

import org.kyonmm.grabbit.SecureUser

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class SecureUserServiceCreate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.SecureUserService.create(..)) && bean(secureUserService) && args(secureUser)',
        argNames='secureUser')
    public void create( SecureUser secureUser ) {}

    @Before('create(secureUser)')
    void before( SecureUser secureUser ) {
        log.info( "Begins request: ${secureUser}" )
    }

    @AfterReturning(
        pointcut='create(org.kyonmm.grabbit.SecureUser)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='create(org.kyonmm.grabbit.SecureUser)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}