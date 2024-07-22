package com.chainsys.royalfinance.controller;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chainsys.royalfinance.dao.UserDAO;
import com.chainsys.royalfinance.exception.EmailException;
import com.chainsys.royalfinance.exception.NameException;
import com.chainsys.royalfinance.exception.PasswordExcepion;
import com.chainsys.royalfinance.exception.PhoneNoException;
import com.chainsys.royalfinance.model.Account;
import com.chainsys.royalfinance.model.Payment;
import com.chainsys.royalfinance.model.User;
import com.chainsys.royalfinance.validation.Validation;
import jakarta.servlet.http.HttpSession;
//@RestController
@Controller
public class UserController
{
	@Autowired
	UserDAO userDAO;
	@Autowired
	Validation validation;
	
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	@GetMapping("/register")
	public String registrationForm(Model model)
	{
		List<String> locations = Arrays.asList("Tenkasi", "Tirunelveli", "Madurai", "Trichy", "Coimbatore", " Chennai");
		 model.addAttribute("locations", locations);
		return "userRegistration";
	}
	@PostMapping("/register")
	public ModelAndView register(@RequestParam("name") String name,@RequestParam("dateOfBirth") String dateOfBirth,@RequestParam("phoneNo") Long phoneNo,@RequestParam("emailId") String emailId,@RequestParam("password") String password,@RequestParam("location") String location,Model model) throws NameException, EmailException, PhoneNoException, PasswordExcepion
	{
		final String login = "login";
		ModelAndView modelAndView = new ModelAndView();
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		String encryptedPassword=bcrypt.encode(password);
		String phoneNumber=phoneNo.toString();
		String id=name.substring(0,3)+phoneNumber.substring(4,8);
		User user=new User(id,name,"Borrower",dateOfBirth,phoneNo,emailId,encryptedPassword,location);
		if(!userDAO.getEmail(emailId))
		{
			if(Boolean.FALSE.equals(validation.checkName(name)) || Boolean.FALSE.equals(validation.checkEmail(emailId)) || Boolean.FALSE.equals(validation.checkPhoneNo(phoneNumber)) || Boolean.FALSE.equals(validation.checkPassword(password)))
			{
				userDAO.saveUser(user);
				userDAO.addAccount(validation.generateAccountNo(),validation.generatePAN(),validation.generateIFSC(),2500,id);
				modelAndView.addObject("message","registerSuccessfull");
				modelAndView.addObject("type", "success");
				modelAndView.setViewName(login);
			}
			else
			{
				modelAndView.addObject("message", "registerUnsuccessfull");
	            modelAndView.addObject("type", "danger");
	            modelAndView.setViewName(login);
			}
		}
		else
		{
			modelAndView.addObject("message", "registerUnsuccessfull");
            modelAndView.addObject("type", "danger");
            modelAndView.setViewName(login);
		}
		return modelAndView;
	}
	@GetMapping("/login")
    public String login() 
	{
        return "login";
    }
	@PostMapping("/login")
	public ModelAndView login(@RequestParam("emailId") String emailId,@RequestParam("password") String password,HttpSession session,Model model) throws EmailException, PasswordExcepion
	{
		final String login = "login";
		final String adminHomePage="adminHomePage";
		final String borrowerHomePage="borrowerHomePage";
		ModelAndView modelAndView = new ModelAndView();
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		User existingUser=null;
		List<User> userDetail=userDAO.checkUserDetails(emailId);
		if(userDetail!=null || Boolean.FALSE.equals(validation.checkEmail(emailId)) || Boolean.FALSE.equals(validation.checkPassword(password)))
		{
			try
			{
				existingUser= userDetail.get(0);
			}
			catch(IndexOutOfBoundsException e)
			{
				modelAndView.addObject("message", "loginUnsuccessfull");
	            modelAndView.addObject("type", "danger");
	            modelAndView.setViewName(login);
			}
			if(password.equals("Ad101@") && emailId.equals("anitha@admin.com"))
			{
				session.setAttribute("emailId",emailId);
				session.setAttribute("id",userDAO.getId(session));
				modelAndView.addObject("message","loginSuccessfull");
				modelAndView.addObject("type", "success");
				modelAndView.setViewName(adminHomePage);
			}
			else if(emailId.equals(existingUser.getEmail()) && bcrypt.matches(password,existingUser.getPassword()))
			{
				session.setAttribute("emailId",emailId);
				session.setAttribute("id",userDAO.getId(session));
				List<Account> accountDetail=userDAO.getUserAccountDetail(userDAO.getId(session));
				Account userAccount=accountDetail.get(0);
				session.setAttribute("accountNo", userAccount.getAccountNo());
				session.setAttribute("pan", userAccount.getPan());
				modelAndView.addObject("message","loginSuccessfull");
				modelAndView.addObject("type", "success");
				modelAndView.setViewName(borrowerHomePage);
			}
			else
			{
				modelAndView.addObject("message", "loginUnsuccessfull");
	            modelAndView.addObject("type", "danger");
	            modelAndView.setViewName(login);
			}
		}
		else
		{
			modelAndView.addObject("message", "loginUnsuccessfull");
            modelAndView.addObject("type", "danger");
            modelAndView.setViewName(login);
		}
		return modelAndView;
	}
	@GetMapping("/userdetail")
	public String getUserById(HttpSession session,Model model)
	{
		String id=(String) session.getAttribute("id");
		if(id.equals("Ani65"))
		{
			List<User> userDetail=userDAO.getUserDetail(id).stream().collect(Collectors.toList());
			model.addAttribute("userDetail",userDetail);
			return "adminProfile";
		}
		else
		{
			List<User> userDetail=userDAO.getUserDetail(id).stream().collect(Collectors.toList());
			model.addAttribute("userDetail",userDetail);
			return "borrowerProfile";
		}
	}
	@GetMapping("/updateuserdetails")
	public String updateUserDetail(Model model)
	{
		List<String> locations = Arrays.asList("Tenkasi", "Tirunelveli", "Madurai", "Trichy", "Coimbatore", " Chennai");
		model.addAttribute("locations", locations);
		return "updateUser";
	}
	@PostMapping("/updateuserdetails")
	public String updateUserDetail(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("phoneNo") Long phoneNo,@RequestParam("location") String location,Model model)
	{
		userDAO.updateUser(id,phoneNo,location);
		List<User> userDetail=userDAO.getUserDetail(id).stream().collect(Collectors.toList());
		model.addAttribute("userDetail",userDetail);
		if(id.equals("Ani65"))
		{
			return "adminProfile";
		}
		else
		{
			return "borrowerProfile";
		}
	}
	@GetMapping("/removeuser")
	public String removeUser(@RequestParam("deleteId") String id,Model model)
	{
		userDAO.removeUser(id);
		String role="Borrower";
		List<User> users=userDAO.getAllUsers(role);
		model.addAttribute("users",users);
		return "registeredUsers";
	}
	@GetMapping("/searchuser")
	public String searchUser(@RequestParam("searchData") String search,Model model)
	{
		List<User> users=userDAO.searchUser(search).stream().filter(user->user.getId().contains(search) || user.getName().contains(search) || user.getEmail().contains(search) || String.valueOf(user.getPhoneNo()).contains(search)).collect(Collectors.toList());
		model.addAttribute("users",users);
		return "registeredUsers";
	}
	@GetMapping("/paymenthistory")
	public String paymentHistory(HttpSession session,Model model)
	{
		String id=(String) session.getAttribute("id");
		List<Payment> payment=userDAO.getPaymentHistory(id);
		model.addAttribute("payment",payment);
		if(id.equals("Ani65"))
		{
			return "paymentHistory1";
		}
		else
		{
			return "paymentHistory";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "home";
	}
}
