package com.chainsys.royalfinance.validation;
import java.util.Random;
import org.springframework.stereotype.Repository;
import com.chainsys.royalfinance.exception.AccountNoException;
import com.chainsys.royalfinance.exception.NumberException;
import com.chainsys.royalfinance.exception.EmailException;
import com.chainsys.royalfinance.exception.NameException;
import com.chainsys.royalfinance.exception.PANException;
import com.chainsys.royalfinance.exception.PasswordExcepion;
import com.chainsys.royalfinance.exception.PhoneNoException;
import com.chainsys.royalfinance.exception.PincodeException;
@Repository
public class Validation
{
	static Random random=new Random();
	public boolean checkName(String name) throws NameException
	{
		String regex="^[A-Za-z\\s]*$";
		if(name.matches(regex))
		{
			return false;
		}
		else
		{
			throw new NameException();
		}
	}
	public boolean checkEmail(String email) throws EmailException
	{
		String regex="^[a-z0-9_\\-\\/.]+@[a-z]+\\.[a-z]{2,}$";
		if(email.matches(regex))
		{
			return false;
		}
		else
		{
			throw new EmailException();
		}
	}
	public boolean checkPhoneNo(String phoneNo) throws PhoneNoException
	{
		String regex="^[6789]\\d{9}$";
		if(phoneNo.matches(regex))
		{
			return false;
		}
		else
		{
			throw new PhoneNoException();
		}
	}
	public boolean checkPassword(String password) throws PasswordExcepion
	{
		String regex="(?=.*[a-z])(?=.*[A-Z])(?=.*//d)(?=.*[@!#$%^&*]).{6}$";
		if(password.matches(regex))
		{
			return false;
		}
		else
		{
			throw new PasswordExcepion();
		}
	}
	public boolean checkAmount(String salary,String amount,String tenure) throws NumberException
	{
		String regex="\\d";
		if(salary.matches(regex) && amount.matches(regex) && tenure.matches(regex))
		{
			return false;
		}
		else
		{
			throw new NumberException();
		}
	}
	public boolean checkSalary(int salary,int loanAmount,int tenure)
	{
		int maxLoan=salary*10;
		int maxTenure=calculateMaxTenure(loanAmount);
		if(salary>=8000 && loanAmount<=maxLoan && tenure<=maxTenure)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public int calculateMaxTenure(int loanAmount)
	{
		 if (loanAmount <= 50000) 
		 {
		      return 12; 
		 } 
		 else if (loanAmount <= 100000)
		 {
		     return 24; 
		 } 
		 else 
		 {
		     return 36;
		 }
	}
	public long generateAccountNo()
	{
		long accountNo=random.nextLong(10000000000000l);
		return accountNo;
	}
	public String generatePAN()
	{
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) 
        {
            char c = (char) (random.nextInt(26) + 'A');
            sb.append(c);
        }
        String fourDigit=String.valueOf(random.nextInt(10000));
        sb.append(fourDigit);
        char lastChar = (char) (random.nextInt(26) + 'A');
        sb.append(lastChar);
		return sb.toString();
	}
	public String generateIFSC()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) 
		{
            char randomChar = (char)('A' + random.nextInt(26)); 
            sb.append(randomChar);
        }
		sb.append(0);
		String sixDigit=String.valueOf(random.nextInt(1000000));
		System.out.println(sixDigit);
		sb.append(sixDigit);
		System.out.println(sb);
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	public boolean checkPincode(String pincode) throws PincodeException
	{
		String regex="^\\d{6}$";
		if(pincode.matches(regex))
		{
			return false;
		}
		else
		{
			throw new PincodeException();
		}
	}
	public boolean checkAccountNo(String accountNo) throws AccountNoException
	{
		String regex="^\\d{13}$";
		if(accountNo.matches(regex))
		{
			return false;
		}
		else
		{
			throw new AccountNoException();
		}
	}
	public boolean checkPAN(String pan) throws PANException
	{
		String regex="[A-Z]{5}\\d{4}[A-Z]";
		if(pan.matches(regex))
		{
			return false;
		}
		else
		{
			throw new PANException();
		}
	}
	
}
