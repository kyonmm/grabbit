package org.kyonmm.grabbit.aop

import org.kyonmm.grabbit.TestCase

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class TestCaseServiceCreate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.TestCaseService.create(..)) && bean(testCaseService) && args(testCase)',
        argNames='testCase')
    public void create( TestCase testCase ) {}

    @Before('create(testCase)')
    void before( TestCase testCase ) {
        log.info( "Begins request: ${testCase}" )
    }

    @AfterReturning(
        pointcut='create(org.kyonmm.grabbit.TestCase)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='create(org.kyonmm.grabbit.TestCase)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}