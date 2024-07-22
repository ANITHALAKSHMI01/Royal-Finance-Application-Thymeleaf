package com.chainsys.royalfinance.controller;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.chainsys.royalfinance.dao.UserDAO;
import com.chainsys.royalfinance.exception.NumberException;
import com.chainsys.royalfinance.exception.InsufficientBalanceException;
import com.chainsys.royalfinance.model.Account;
import com.chainsys.royalfinance.model.Borrower;
import com.chainsys.royalfinance.model.Loan;
import com.chainsys.royalfinance.model.Payment;
import com.chainsys.royalfinance.model.User;
import com.chainsys.royalfinance.validation.Validation;
import jakarta.servlet.http.HttpSession;
//@RestController
@Controller
public class BorrowerController 
{
	@Autowired
	UserDAO userDAO;
	@Autowired
	Validation validation;
	@GetMapping("/borrowerhomepage")
	public String adminHomePage()
	{
		return "borrowerHomePage";
	}
	@PostMapping("/applyloan")
	public ModelAndView applyLoan(@RequestParam("id") String id,@RequestParam("salary") int salary,@RequestParam("amount") int loanAmount,@RequestParam("repayment") int tenure,@RequestParam("accountNo") long accountNo,@RequestParam("pan") String pan,@RequestParam("paySlip") MultipartFile paySlip,@RequestParam("panImage") MultipartFile panImage,@RequestParam("govId") MultipartFile govId,Model model,RedirectAttributes redirectAttributes) throws IOException, NumberException
	{
		final String borrowerHomePage="borrowerHomePage";
		ModelAndView modelAndView = new ModelAndView();
		if(Boolean.FALSE.equals(validation.checkSalary(salary,loanAmount,tenure)))
		{
			if(paySlip!= null && panImage!=null && govId!=null|| Boolean.FALSE.equals(validation.checkAmount(String.valueOf(salary),String.valueOf(loanAmount),String.valueOf(tenure))))
			{
				byte[] slip = paySlip.getBytes();
				byte[] panProof = panImage.getBytes();
				byte[] govIdProof=govId.getBytes();
				Borrower borrower=new Borrower(id,salary,loanAmount,tenure,slip,panProof,govIdProof,"Unapproved");
				userDAO.addBorrower(borrower);
				userDAO.updateUserActive(id);
				modelAndView.addObject("message","successfullApplication");
				modelAndView.addObject("type", "success");
				modelAndView.setViewName(borrowerHomePage);
			}
			else
			{
				modelAndView.addObject("message", "unSuccessfullApplication");
	            modelAndView.addObject("type", "danger");
	            modelAndView.setViewName(borrowerHomePage);
			}
		}
		else
		{
			modelAndView.addObject("message", "unSuccessfullApplication");
            modelAndView.addObject("type", "danger");
            modelAndView.setViewName(borrowerHomePage);
		}
		  return modelAndView;
	}
	@GetMapping("/borrowerhomePage")
	public String borrowerHomePage()
	{
		return "borrowerhomePage";
	}
	@GetMapping("/appliedloandetail")
	public ModelAndView appliedLoanDetail(Model model,HttpSession session)
	{
		final String borrowerHomePage="borrowerHomePage";
		final String loanDetail="appliedLoanDetails";
		ModelAndView modelAndView = new ModelAndView();
		String id=(String) session.getAttribute("id");
		List<Borrower> borrowers=userDAO.getBorrowerDetail(id);
		if(borrowers!=null && !borrowers.isEmpty())
		{
			byte[] paySlipImage=borrowers.get(0).getPaySlip();
			String paySlip = Base64.getEncoder().encodeToString(paySlipImage);
			String paySlip1 = "data:image/jpeg;base64," + paySlip;
			byte[] panImage=borrowers.get(0).getPanProof();
			String pan = Base64.getEncoder().encodeToString(panImage);
			String pan1 = "data:image/jpeg;base64," + pan;
			byte[] viewGovernmentId=borrowers.get(0).getGovernmentId();
			String governmentId = Base64.getEncoder().encodeToString(viewGovernmentId);
			String governmentId1 = "data:image/jpeg;base64," + governmentId;
			model.addAttribute("governmentId",governmentId1);
			model.addAttribute("pan",pan1);
			model.addAttribute("paySlip",paySlip1);
			model.addAttribute("borrowers",borrowers);
			modelAndView.addObject("message","viewDetail");
			modelAndView.addObject("type", "info");
			modelAndView.setViewName(loanDetail);
		}
		else
		{
			modelAndView.addObject("message","noDetail");
			modelAndView.addObject("type", "info");
			modelAndView.setViewName(borrowerHomePage);
		}
		return modelAndView;
	}
	@GetMapping("/allowborrower")
	public ModelAndView allowBorrower(HttpSession session)
	{
		final String borrowerHomePage="borrowerHomePage";
		final String loanApplication="loanApplication";
		ModelAndView modelAndView = new ModelAndView();
		String id=(String) session.getAttribute("id");
		List<User> user=userDAO.getUserDetail(id);
		if(user!=null)
		{
			User existingUser=user.get(0);
			if(existingUser.getActive()==0)
			{
				modelAndView.addObject("message","existingUser");
				modelAndView.addObject("type", "info");
				modelAndView.setViewName(loanApplication);
			}
			else
			{
				modelAndView.addObject("message","alreadyApplied");
				modelAndView.addObject("type", "info");
				modelAndView.setViewName(borrowerHomePage);
			}
		}
		else
		{
			modelAndView.addObject("message","newUser");
			modelAndView.addObject("type", "info");
			modelAndView.setViewName(loanApplication);
		}
		return modelAndView;
	}
	@GetMapping("/getreceipt")
	public ModelAndView getReceipt(Model model,HttpSession session)
	{
		final String borrowerHomePage="borrowerHomePage";
		final String receipt="loanReceipt";
		ModelAndView modelAndView = new ModelAndView();
		String borrowerId=(String) session.getAttribute("id");
		List<Loan> loanDetail=userDAO.getApprovedLoan(borrowerId);
		if(!loanDetail.isEmpty())
		{
			model.addAttribute("loan",loanDetail);
			modelAndView.addObject("message","availableReceipt");
			modelAndView.addObject("type", "info");
			modelAndView.setViewName(receipt);
		}
		else
		{
			modelAndView.addObject("message","noReceipt");
			modelAndView.addObject("type", "info");
			modelAndView.setViewName(borrowerHomePage);
		}
		return modelAndView;
	}
	@GetMapping("/preclosure")
	public String preClosure(HttpSession session,Model model)
	{
		int preclosureCharge=0;
		Loan loans=null;
		String id=(String) session.getAttribute("id");
		List<Loan> loanDetail=userDAO.getEMI(id,"Unpaid");
		List<Account> borrowerAccount=userDAO.getUserAccountDetail(id);
		List<Loan> list=new ArrayList<>();
		if(!loanDetail.isEmpty())
			loans=loanDetail.get(0);
		if(loans!=null)
		{
			Loan loan=new Loan();
			loan.setBorrowerId(id);
			double outstandingPrincipal=loans.getBalanceLoanAmount();
			if(outstandingPrincipal <= 50000)
				preclosureCharge+=200;
		    else if (outstandingPrincipal <= 100000) 
		    	preclosureCharge+=400;
		    else 
		    	preclosureCharge+=600;
			loan.setLoanId(loans.getLoanId());
			loan.setBalanceLoanAmount(outstandingPrincipal+preclosureCharge);
			loan.setAccountNo(borrowerAccount.get(0).getAccountNo());
			list.add(loan);
			model.addAttribute("list",list);
		}
		return "loanPreclosure";
	}
//	@GetMapping("/payemi")
//	public String payEMI(HttpSession session,Model model)
//	{
//		Loan loans=null;
//		String id=(String) session.getAttribute("id");
//		List<Loan> loanDetail=userDAO.getEMI(id,"Unpaid");
//		List<Loan> list=new ArrayList<>();
//		if(!loanDetail.isEmpty())
//		{
//			loans=loanDetail.get(0);
//		}
//		if(loans!=null)
//		{
////			int emi=loans.getLoanAmount()/loans.getTenure();
//			Loan loan=new Loan();
////			loan.setLoanId(loans.getLoanId());
////			loan.setBorrowerId(id);
////			loan.setDueDate(loans.getDueDate());
////			loan.setEmi(emi);
////			loan.setAccountNo(loans.getAccountNo());
//			list.add(loan);
//			model.addAttribute("list",list);
//		}
//		return "payEMI";
//	}
	
	@PostMapping("/updatepayment")
	public String updatePayment(@RequestParam("id") String borrowerId,@RequestParam("loanId") int loanId,@RequestParam("amount") double amount,@RequestParam("account") long accountNo,@RequestParam("status") int value) throws InsufficientBalanceException
	{
		
		LocalDate dateToday = LocalDate.now(); 
		String dateString =dateToday.toString();
		double balance=0;
		long adminAccountNo=6754321890765l;
		List<Account> borrowerAccount=userDAO.getUserAccountDetail(borrowerId);
		List<Account> adminAccount=userDAO.getUserAccountDetail("Ani65");
		double adminTotalBalance=adminAccount.get(0).getTotalBalance();
		double borrowerTotalBalance=borrowerAccount.get(0).getTotalBalance();
		if(borrowerTotalBalance>0)
			balance=borrowerTotalBalance-amount;
		else
			throw new InsufficientBalanceException();
		double creditAmount=adminTotalBalance+amount;
		userDAO.updateBalance(adminAccountNo, creditAmount);
		userDAO.updateBalance(accountNo, balance);
		userDAO.updatePaymentStatus("Paid",value,loanId);
		Payment payment=new Payment(borrowerId,dateString,accountNo,adminAccountNo,amount);
		userDAO.addPaymentHistory(payment);
		return "borrowerHomePage";
	}
}
