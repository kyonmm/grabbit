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
class TestCaseServiceUpdate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.TestCaseService.update(..)) && bean(testCaseService) && args(testCase)',
        argNames='testCase')
    public void update( TestCase testCase ) {}

    @Before('update(testCase)')
    void before( TestCase testCase ) {
        log.info( "Begins request: ${testCase}" )
    }

    @AfterReturning(
        pointcut='update(org.kyonmm.grabbit.TestCase)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='update(org.kyonmm.grabbit.TestCase)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}