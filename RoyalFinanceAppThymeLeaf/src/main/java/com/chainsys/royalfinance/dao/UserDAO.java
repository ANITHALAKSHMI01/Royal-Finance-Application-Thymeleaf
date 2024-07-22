package com.chainsys.royalfinance.dao;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.chainsys.royalfinance.model.Account;
import com.chainsys.royalfinance.model.Borrower;
import com.chainsys.royalfinance.model.Loan;
import com.chainsys.royalfinance.model.Payment;
import com.chainsys.royalfinance.model.User;

import jakarta.servlet.http.HttpSession;
@Repository
public interface UserDAO 
{
	public void saveUser(User user);
	public void addAccount(long accountNo,String pan,String ifsc,int amount,String id);
	public List<Account> getUserAccountDetail(String id);
	public List<User> getAllUsers(String role);
	public void removeUser(String id);
	public String getId(HttpSession session);
	public List<User> getUserDetail(String id);
	public void updateUser(String id,long phoneNo,String location);
	public boolean getEmail(String email);
	public List<User> checkUserDetails(String email);
	public List<User> searchUser(String searchData);
	public void addBorrower(Borrower borrower);
	public List<Borrower> getAllBorrowers();
	public List<Borrower> getBorrowerDetail(String id);
	public void updateUserActive(String id);
	public List<Borrower> searchBorrower(String searchData);
	public void updateLoanStatus(int applicationId,String status);
	public void issueLoan(Loan loan);
	public List<Loan> getApprovedLoan(String id);
	public List<Loan> getAllLoans();
	public List<Loan> searchLoan(String searchData);
	public void updateBillStatus(String id);
	public void updateBalance(long accountNo,double amount);
	public List<Loan> getLoanById(String id);
	public List<Loan> getEMI(String id,String paymentStatus);
	public void updatePaymentStatus(String paymentStatus,int loanId);
	public void updatePenalty(int penalty,int loanId);
	public int totalBorrowers();
	public int totalApprovedBorrowers();
	public int calculateProfit();
	public int calculateTotalLoan();
	public void addPaymentHistory(Payment payment);
	public List<Payment> getPaymentHistory(String id);
}
