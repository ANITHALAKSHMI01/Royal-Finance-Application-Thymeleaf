package com.chainsys.royalfinance.exception;
public class EmailException  extends Exception
{
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage()
	{
		return "Email should contain @ and .";
	}
}
