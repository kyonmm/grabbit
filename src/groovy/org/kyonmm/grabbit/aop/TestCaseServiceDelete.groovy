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
class TestCaseServiceDelete {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.TestCaseService.delete(..)) && bean(testCaseService) && args(testCase)',
        argNames='testCase')
    public void delete( TestCase testCase ) {}

    @Before('delete(testCase)')
    void before( TestCase testCase ) {
        log.info( "Begins request:${testCase}" )
    }

    @AfterReturning(
        pointcut='delete(org.kyonmm.grabbit.TestCase)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='delete(org.kyonmm.grabbit.TestCase)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}