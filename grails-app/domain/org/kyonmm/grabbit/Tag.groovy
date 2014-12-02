package org.kyonmm.grabbit

class Tag {

    String name
    String description
    Date dateCreated
    Date lastUpdated
    static constraints = {
        name maxSize: 250, unique: true
        description maxSize: 1000
    }
}
