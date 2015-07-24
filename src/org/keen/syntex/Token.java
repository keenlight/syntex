package org.keen.syntex;

public class Token{
	private TokenType type;
	private String code;
	
	public Token(TokenType type, String code) {
		this.type = type;
		this.code = code;
	}

	public TokenType getType() {
		return type;
	}

	public String getCode() {
		return code;
	}
}