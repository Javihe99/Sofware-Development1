////////////////////////////////////////////////////////////////////
// checkstyle:
// Checks Java source code for adherence to a set of rules.
// Copyright (C) 2020 Jiajia Ye Jiawang He
////////////////////////////////////////////////////////////////////
package Transport4Future.TokenManagement;

public class TokenManagementException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;

	public TokenManagementException(String message) {

		this.message = message;
	}

	public String Getmessage() {

		return this.message;
	}
}
