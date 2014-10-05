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
class TestCaseServiceGet {

    @Pointcut(
        value='execution(org.kyonmm.grabbit.TestCase org.kyonmm.grabbit.TestCaseService.get(..)) && bean(testCaseService) && args(id)',
        argNames='id')
    public void getMethod( Long id ) {}

    @Before('getMethod(id)')
    void before( Long id ) {
        log.info( "Begins request:${id}" )
    }

    @AfterReturning(
        pointcut='getMethod(Long)',
        returning='testCase')
    void afterReturning( TestCase testCase ) {
        log.info( "End of request: ${testCase}" )
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