package org.keen.syntex.test;

import org.junit.Test;
import org.keen.syntex.SyntexError;
import org.keen.syntex.Token;
import org.keen.syntex.TokenIterator;

public class TokenIteratorTest {

	@Test(expected=SyntexError.class)
	public void test_iter() throws SyntexError{
		String code = "a , b , cc , 8 11 ;";
		TokenIterator iter = new TokenIterator(code);
		while(iter.hasNext()){
			Token next = iter.next();
			System.out.println(next.getCode() + "|" + next.getType());
		}
	}
}
