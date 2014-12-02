package org.kyonmm.grabbit

class TestCase {

    String name
    String scenario
    static hasMany = [tags: Tag]
    Date dateCreated
    Date lastUpdated
    static constraints = {
        name maxSize: 250
        scenario maxSize: 1000
    }
    static mapping = {
        tags lazy: false
    }
}
