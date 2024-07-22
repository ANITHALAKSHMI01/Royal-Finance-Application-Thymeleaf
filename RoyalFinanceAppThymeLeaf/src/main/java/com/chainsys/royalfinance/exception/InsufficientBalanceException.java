package com.chainsys.royalfinance.exception;
public class InsufficientBalanceException extends Exception
{
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage()
	{
		return "You have zero balance.";
	}
}
