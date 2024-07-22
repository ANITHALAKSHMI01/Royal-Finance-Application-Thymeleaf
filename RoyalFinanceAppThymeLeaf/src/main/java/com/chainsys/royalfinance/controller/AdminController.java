package com.chainsys.royalfinance.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chainsys.royalfinance.dao.UserDAO;
import com.chainsys.royalfinance.exception.InsufficientBalanceException;
import com.chainsys.royalfinance.model.Account;
import com.chainsys.royalfinance.model.Borrower;
import com.chainsys.royalfinance.model.Loan;
import com.chainsys.royalfinance.model.Payment;
import com.chainsys.royalfinance.model.User;

import jakarta.servlet.http.HttpSession;
//@RestController
@Controller
public class AdminController 
{
	@Autowired
	UserDAO userDAO;
	@GetMapping("/adminhomepage")
	public String adminHomePage()
	{
		return "adminHomePage";
	}
	@GetMapping("/listallusers")
	public String listAllUsers(Model model)
	{
		String role="Borrower";
		List<User> users=userDAO.getAllUsers(role);
		model.addAttribute("users",users);
		return "registeredUsers";
	}
	@GetMapping("/listallborrowers")
	public String listAllBorrowers(Model model)
	{
		List<Borrower> borrowers=userDAO.getAllBorrowers();
		model.addAttribute("borrowers",borrowers);
		return "borrowers";
	}
	@PostMapping("/viewpayslip")
	public String viewPaySlip(@RequestParam("viewId") String id,Model model)
	{
		List<Borrower> borrowers=userDAO.getBorrowerDetail(id);
		byte[] paySlipImage=borrowers.get(0).getPaySlip();
		String paySlip = Base64.getEncoder().encodeToString(paySlipImage);
		String image = "data:image/jpeg;base64," + paySlip;
		model.addAttribute("paySlip",image);
		return "viewPaySlip";
	}
	@PostMapping("/viewpanproof")
	public String viewPanProof(@RequestParam("viewId") String id,Model model)
	{
		List<Borrower> borrowers=userDAO.getBorrowerDetail(id);
		byte[] panImage=borrowers.get(0).getPanProof();
		String pan = Base64.getEncoder().encodeToString(panImage);
		String image = "data:image/jpeg;base64," + pan;
		model.addAttribute("pan",image);
		return "viewPAN";
	}
	@PostMapping("/viewgovernmentid")
	public String viewGovernmentId(@RequestParam("viewId") String id,Model model)
	{
		List<Borrower> borrowers=userDAO.getBorrowerDetail(id);
		byte[] viewGovernmentId=borrowers.get(0).getGovernmentId();
		String governmentId = Base64.getEncoder().encodeToString(viewGovernmentId);
		String image = "data:image/jpeg;base64," + governmentId;
		model.addAttribute("governmentId",image);
		return "viewGovernmentId";
	}
	@GetMapping("/searchborrower")
	public String searchBorrower(@RequestParam("searchData") String searchData,Model model)
	{
		List<Borrower> borrowers=userDAO.searchBorrower(searchData).stream().filter(borrower->String.valueOf(borrower.getApplicationId()).contains(searchData) || borrower.getBorrowerId().contains(searchData) || borrower.getStatus().equalsIgnoreCase(searchData)).collect(Collectors.toList());
		model.addAttribute("borrowers",borrowers);
		return "borrowers";
	}
	@GetMapping("/updatestatus")
	public String updateStatus(@RequestParam("id") int applicationId,@RequestParam("approval") String approval,Model model)
	{
		userDAO.updateLoanStatus(applicationId, approval);
		List<Borrower> borrowers=userDAO.getAllBorrowers();
		model.addAttribute("borrowers",borrowers);
		return "borrowers";
	}
	@PostMapping("/issueloan")
	public String issueLoan(@RequestParam("id") String borrowerId,@RequestParam("applicationId") int applicationId,@RequestParam("amount") double loanAmount,@RequestParam("tenure") int tenure,Model model)
	{
		Borrower borrower1=null;
		LocalDate dateToday = LocalDate.now(); 
		String dateString =dateToday.toString();
		int annualInterest=12;
		double monthlyInterest = 0.01;
		double emi = loanAmount * monthlyInterest * Math.pow(1 + monthlyInterest, tenure)/ (Math.pow(1 + monthlyInterest, tenure) - 1);
		double interestAmount=loanAmount * monthlyInterest;
		double principalRepayment=emi-interestAmount;
		String status="Unpaid";
		Loan loan=new Loan(borrowerId,loanAmount,dateString,annualInterest,tenure,tenure,emi,interestAmount,principalRepayment,loanAmount,status);
		List<Borrower> borrower=userDAO.getBorrowerDetail(borrowerId);
		if(!borrower.isEmpty())
		{
			borrower1=borrower.get(0);
		}
		if(borrower1.getStatus().equals("Approved"))
		{
			userDAO.issueLoan(loan);
			List<Loan> loanDetail=userDAO.getApprovedLoan(borrowerId);
			List<Account> accountDetail=userDAO.getUserAccountDetail(borrowerId);
			Account userAccount=accountDetail.get(0);
			model.addAttribute("accountNo",userAccount.getAccountNo());
			model.addAttribute("ifsc",userAccount.getIfsc());
			model.addAttribute("loan",loanDetail);
			return "payLoan";
		}
		else
		{
			List<Borrower> borrowers=userDAO.getAllBorrowers();
			model.addAttribute("borrowers",borrowers);
			return "borrowers";
		}
	}
	@GetMapping("/getallloans")
	public String getAllLoans(Model model)
	{
		List<Loan> loans=userDAO.getAllLoans();
		model.addAttribute("loans",loans);
		return "loanDetails";
	}
	@GetMapping("/searchloandetail")
	public String searchLoanDetail(@RequestParam("searchData") String searchData,Model model)
	{
		List<Loan> loans=userDAO.searchLoan(searchData).stream().filter(loan->loan.getBorrowerId().contains(searchData) || String.valueOf(loan.getLoanId()).equals(searchData) || loan.getPaymentStatus().equalsIgnoreCase(searchData)).collect(Collectors.toList());
		model.addAttribute("loans",loans);
		return "loanDetails";
	}
	@PostMapping("/payloan")
	public String payLoan(@RequestParam("id") String borrowerId,@RequestParam("account") long accountNo,@RequestParam("amount") double amount,Model model) throws InsufficientBalanceException
	{
		LocalDate dateToday = LocalDate.now(); 
		String dateString =dateToday.toString();
		double balance=0;
		long adminAccountNo=6754321890765l;
		String adminId="Ani65";
		userDAO.updateBillStatus(borrowerId);
		List<Account> borrowerAccount=userDAO.getUserAccountDetail(borrowerId);
		List<Account> adminAccount=userDAO.getUserAccountDetail(adminId);
		double adminTotalBalance=adminAccount.get(0).getTotalBalance();
		double borrowerTotalBalance=borrowerAccount.get(0).getTotalBalance();
		if(adminTotalBalance>0)
		{
			balance=adminTotalBalance-amount;
		}
		else
		{
			throw new InsufficientBalanceException();
		}
		double creditAmount=borrowerTotalBalance+amount;
		userDAO.updateBalance(adminAccountNo, balance);
		userDAO.updateBalance(accountNo, creditAmount);
		Payment payment=new Payment(adminId,dateString,adminAccountNo,accountNo,amount);
		userDAO.addPaymentHistory(payment);
		List<Borrower> borrowers=userDAO.getAllBorrowers();
		model.addAttribute("borrowers",borrowers);
		return "borrowers";
	}
//	@GetMapping("/updateemi")
//	public String updateEMI(@RequestParam("loanId") int loanId,@RequestParam("id") String borrowerId,Model model)
//	{
//		Loan loans=null;
//		List<Loan> loanDetail=userDAO.getLoanById(borrowerId);
//		List<Loan> list=new ArrayList<>();
//		if(!loanDetail.isEmpty())
//		{
//			loans=loanDetail.get(0);
//		}
//		int emi=loans.getLoanAmount()/loans.getTenure();
//		Loan loan=new Loan();
//		loan.setLoanId(loanId);
//		loan.setBorrowerId(borrowerId);
//		loan.setDate(loans.getDate());
//		loan.setEmi(emi);
//		list.add(loan);
//		model.addAttribute("list",list);
//		return "updateEMI.jsp";
//	}
//	@PostMapping("/updateduedate")
//	public String updateDueDate(@RequestParam("loanId") int loanId,@RequestParam("id") String borrowerId,@RequestParam("dueDate") String dueDate,Model model)
//	{
//		String paymentStatus="Unpaid";
//		userDAO.updateEMI(dueDate, paymentStatus, loanId);
//		List<Loan> loans=userDAO.getAllLoans();
//		model.addAttribute("loans",loans);
//		return "loanDetails.jsp";
//	}
}
