package cl.puc.dcc.iic2113.persistencia;

import java.util.Stack;

class TokenMemory {
	
	Stack<Token> tokenMemory;
	
	public TokenMemory()
	{
		tokenMemory = new Stack<Token>();
	}
	
	public void AppendToken(Token t)
	{
		tokenMemory.add(t);
	}
	
	public Token getPreviousToken()
	{
		return tokenMemory.pop();
	}

}
