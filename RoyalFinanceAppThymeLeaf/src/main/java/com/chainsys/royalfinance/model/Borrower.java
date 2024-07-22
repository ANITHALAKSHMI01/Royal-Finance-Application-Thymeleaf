package com.chainsys.royalfinance.model;
import java.util.Arrays;
public class Borrower 
{
	private int applicationId;
	private String borrowerId;
	private int salary;
	private int loanAmount;
	private int tenure;
	private long accountNo;
	private String pan;
	private byte[] paySlip;
	private byte[] panProof;
	private byte[] governmentId;
	private String status;
	public Borrower(String borrowerId,int salary,int loanAmount,int tenure,byte[] paySlip, byte[] panProof,byte[] governmentId,String status)
	{
		this.borrowerId=borrowerId;
		this.salary=salary;
		this.loanAmount=loanAmount;
		this.tenure=tenure;
		this.paySlip=paySlip;
		this.panProof=panProof;
		this.governmentId=governmentId;
		this.status=status;
	}
	public Borrower()
	{
		
	}
	public int getApplicationId() 
	{
		return applicationId;
	}
	public void setApplicationId(int applicationId)
	{
		this.applicationId = applicationId;
	}
	public String getBorrowerId() 
	{
		return borrowerId;
	}
	public void setBorrowerId(String borrowerId) 
	{
		this.borrowerId = borrowerId;
	}
	public int getSalary() 
	{
		return salary;
	}
	public void setSalary(int salary) 
	{
		this.salary = salary;
	}
	public int getLoanAmount()
	{
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) 
	{
		this.loanAmount = loanAmount;
	}
	public int getTenure() 
	{
		return tenure;
	}
	public void setTenure(int tenure)
	{
		this.tenure = tenure;
	}
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
	public byte[] getPaySlip() 
	{
		return paySlip;
	}
	public void setPaySlip(byte[] paySlip) 
	{
		this.paySlip = paySlip;
	}
	public byte[] getPanProof() 
	{
		return panProof;
	}
	public void setPanProof(byte[] panProof) 
	{
		this.panProof = panProof;
	}
	public byte[] getGovernmentId() 
	{
		return governmentId;
	}
	public void setGovernmentId(byte[] governmentId) 
	{
		this.governmentId = governmentId;
	}
	public String getStatus() 
	
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	@Override
	public String toString() 
	{
		return "Borrower [applicationId=" + applicationId + ", borrowerId=" + borrowerId + ", salary=" + salary
				+ ", loanAmount=" + loanAmount + ", tenure=" + tenure + ", accountNo=" + accountNo + ", pan=" + pan
				+ ", paySlip=" + Arrays.toString(paySlip) + ", panProof=" + Arrays.toString(panProof)
				+ ", governmentId=" + Arrays.toString(governmentId) + ", status=" + status +"]";
	}	
}
