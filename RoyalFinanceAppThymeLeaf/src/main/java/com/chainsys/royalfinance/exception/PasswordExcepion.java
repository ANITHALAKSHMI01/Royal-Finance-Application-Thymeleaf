package com.chainsys.royalfinance.exception;
public class PasswordExcepion extends Exception
{
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage()
	{
		return "Password should contain atleast one UpperCase,LowerCase,Number and Special Character ,Password must be 6 characters.";
	}
}
