package Transport4Future.TokenManagement;

public class TokenManagementException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;

	public TokenManagementException(String message) {

		this.message = message;
	}

	public String getMessage() {
		System.out.println("--------------------holaEx-------------------------------------");
		return this.message;
	}
}
