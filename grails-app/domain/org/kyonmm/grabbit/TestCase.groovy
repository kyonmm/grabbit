package org.kyonmm.grabbit

class TestCase {

    String name
    String scenario
    static hasMany = [tags:Tag]
    static constraints = {
        name maxSize: 250
        scenario maxSize: 1000
    }
    static mapping = {
        tags lazy: false
    }

    static searchable = {
        tags component:true
    }
}
