package org.kyonmm.grabbit

class SecureRoleMock {

    static SecureRole mock( id ) {

        def instance = new SecureRole(
            authority:'A',
        )

        instance.authority = new String( ( 65 + id ) as Character )
        instance

    }

}