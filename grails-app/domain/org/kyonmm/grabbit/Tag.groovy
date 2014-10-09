package org.kyonmm.grabbit

class Tag {

    String name
    String description
    static constraints = {
        name maxSize: 250, unique: true
        description maxSize: 1000
    }
    static searchable = true
}
