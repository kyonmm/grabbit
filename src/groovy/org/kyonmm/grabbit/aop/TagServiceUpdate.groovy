package org.kyonmm.grabbit.aop

import org.kyonmm.grabbit.Tag

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class TagServiceUpdate {

    @Pointcut(
        value='execution(void org.kyonmm.grabbit.TagService.update(..)) && bean(tagService) && args(tag)',
        argNames='tag')
    public void update( Tag tag ) {}

    @Before('update(tag)')
    void before( Tag tag ) {
        log.info( "Begins request: ${tag}" )
    }

    @AfterReturning(
        pointcut='update(org.kyonmm.grabbit.Tag)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='update(org.kyonmm.grabbit.Tag)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}