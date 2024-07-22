package com.chainsys.royalfinance.model;
public class Account 
{
	private long accountNo;
	private String pan;
	private String ifsc;
	private String userId;
	private int totalBalance;
	public long getAccountNo() 
	{
		return accountNo;
	}
	public void setAccountNo(long accountNo) 
	{
		this.accountNo = accountNo;
	}
	public String getPan() 
	{
		return pan;
	}
	public void setPan(String pan) 
	{
		this.pan = pan;
	}
	public String getIfsc() 
	{
		return ifsc;
	}
	public void setIfsc(String ifsc) 
	{
		this.ifsc = ifsc;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public int getTotalBalance()
	{
		return totalBalance;
	}
	public void setTotalBalance(int totalBalance)
	{
		this.totalBalance = totalBalance;
	}
	@Override
	public String toString() 
	{
		return "Account [accountNo=" + accountNo + ", pan=" + pan + ", ifsc=" + ifsc + ", userId=" + userId
				+ ", totalBalance=" + totalBalance + "]";
	}
}
