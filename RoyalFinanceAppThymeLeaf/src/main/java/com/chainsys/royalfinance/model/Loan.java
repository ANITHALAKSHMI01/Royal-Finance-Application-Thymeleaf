package com.chainsys.royalfinance.model;
public class Loan 
{
	private int loanId;
	private String borrowerId;
	private double loanAmount;
	private String date;
	private int interest;
	private int tenure;
	private int remainingTenure;
	private double emi;
	private double interestAmount;
	private double principalAmount;
	private double balanceLoanAmount;
	private long accountNo;
	private String ifsc;
	private int status;
	private String dueDate;
	private String paymentStatus;
	public Loan(String borrowerId,double loanAmount,String date,int interest,int tenure,int remainingTenure,double emi,double interestAmount,double principalAmount,double balanceLoanAmount,String paymentStatus)
	{
		this.borrowerId=borrowerId;
		this.loanAmount=loanAmount;
		this.date=date;
		this.interest=interest;
		this.tenure=tenure;
		this.remainingTenure=remainingTenure;
		this.emi=emi;
		this.interestAmount=interestAmount;
		this.principalAmount=principalAmount;
		this.balanceLoanAmount=balanceLoanAmount;
		this.paymentStatus=paymentStatus;
	}
	public Loan()
	{
		
	}
	public int getLoanId() 
	{
		return loanId;
	}
	public void setLoanId(int loanId) 
	{
		this.loanId = loanId;
	}
	public String getBorrowerId() 
	{
		return borrowerId;
	}
	public void setBorrowerId(String borrowerId) 
	{
		this.borrowerId = borrowerId;
	}
	public double getLoanAmount() 
	{
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) 
	{
		this.loanAmount = loanAmount;
	}
	public String getDate() 
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public int getInterest() 
	{
		return interest;
	}
	public void setInterest(int interest) 
	{
		this.interest = interest;
	}
	public int getTenure() 
	{
		return tenure;
	}
	public void setTenure(int tenure) 
	{
		this.tenure = tenure;
	}
	public int getRemainingTenure() 
	{
		return remainingTenure;
	}
	public void setRemainingTenure(int remainingTenure)
	{
		this.remainingTenure = remainingTenure;
	}
	public double getEmi() 
	{
		return emi;
	}
	public void setEmi(double emi) 
	{
		this.emi = emi;
	}
	public double getInterestAmount() 
	{
		return interestAmount;
	}
	public void setInterestAmount(double interestAmount)
	{
		this.interestAmount = interestAmount;
	}
	public double getPrincipalAmount() 
	{
		return principalAmount;
	}
	public void setPrincipalAmount(double principalAmount) 
	{
		this.principalAmount = principalAmount;
	}
	public double getBalanceLoanAmount() 
	{
		return balanceLoanAmount;
	}
	public void setBalanceLoanAmount(double balanceLoanAmount) 
	{
		this.balanceLoanAmount = balanceLoanAmount;
	}
	public long getAccountNo() 
	{
		return accountNo;
	}
	public void setAccountNo(long accountNo) 
	{
		this.accountNo = accountNo;
	}
	public String getIfsc() 
	{
		return ifsc;
	}
	public void setIfsc(String ifsc) 
	{
		this.ifsc = ifsc;
	}
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status) 
	{
		this.status = status;
	}
	public String getDueDate() 
	{
		return dueDate;
	}
	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}
	public String getPaymentStatus() 
	{
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) 
	{
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() 
	{
		return "Loan [loanId=" + loanId + ", borrowerId=" + borrowerId + ", loanAmount=" + loanAmount + ", date=" + date
				+ ", interest=" + interest + ", tenure=" + tenure + ", remainingTenure=" + remainingTenure + ", emi="
				+ emi + ", interestAmount=" + interestAmount + ", principalAmount=" + principalAmount
				+ ", balanceLoanAmount=" + balanceLoanAmount + ", accountNo=" + accountNo + ", ifsc=" + ifsc
				+ ", status=" + status + ", dueDate=" + dueDate + ", paymentStatus=" + paymentStatus + "]";
	}
}
