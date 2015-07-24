package org.keen.syntex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TokenIterator implements Iterator<Token>{

	private Iterator<Token> iterator;
	public TokenIterator(String code) throws SyntexError {
		String[] split = code.split(" ");
		List<Token> tokens = new ArrayList<Token>();
		for(String item : split){
			if(item.equals(",")){
				tokens.add(new Token(TokenType.comma, item));
			}else if(item.equals(";")){
				tokens.add(new Token(TokenType.semicolon, item));
			}else if(item.matches("[A-Za-z]*")){
				tokens.add(new Token(TokenType.symbol, item));
			}else if(item.matches("[0-9]*")){
				tokens.add(new Token(TokenType.number, item));
			}else{
				throw new SyntexError("无法识别：" + item);
			}
		}
		iterator = tokens.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Token next() {
		return iterator.next();
	}
	
}
