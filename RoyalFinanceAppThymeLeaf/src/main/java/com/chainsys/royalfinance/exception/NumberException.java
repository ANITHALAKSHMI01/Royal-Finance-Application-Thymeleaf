package com.chainsys.royalfinance.exception;
public class NumberException extends Exception
{
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage()
	{
		return "Amount or tenure should be numeric and greater than zero.";
	}
}
