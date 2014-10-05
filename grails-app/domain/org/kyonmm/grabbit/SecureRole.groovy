package org.kyonmm.grabbit

class SecureRole {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
