package org.kyonmm.grabbit

class Tag {

    String name
    String description
    static constraints = {
        name maxSize: 250
        description maxSize: 1000
    }
}
