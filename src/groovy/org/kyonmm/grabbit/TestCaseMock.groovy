package org.kyonmm.grabbit

class TestCaseMock {

    static TestCase mock( id ) {

        def instance = new TestCase(
            name:'A',
            scenario:'A',
            tags:null,
        )
        instance

    }

}