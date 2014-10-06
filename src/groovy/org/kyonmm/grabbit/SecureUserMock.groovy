package org.kyonmm.grabbit

class SecureUserMock {

    static SecureUser mock( id ) {

        def instance = new SecureUser(
            username:'A',
            password:'A',
            email:'A',
            accountExpired:true,
            accountLocked:true,
            enabled:true,
            passwordExpired:true,
        )

        instance.username = new String( ( 65 + id ) as Character )
        instance

    }

}