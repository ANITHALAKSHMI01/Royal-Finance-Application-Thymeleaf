package com.chainsys.royalfinance.exception;
public class NameException extends Exception
{
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage()
	{
		return "Name should be alphabet.";
	}
}
